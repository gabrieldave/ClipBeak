#!/usr/bin/env bash
set -euo pipefail
umask 077

# Run on the maintainer's computer. The keystore stays in this private directory.
repo=${CLIPBEAK_REPOSITORY:-gabrieldave/ClipBeak}
folder=${XDG_CONFIG_HOME:-$HOME/.config}/clipbeak
export CLIPBEAK_KEYSTORE_PATH="$folder/release.p12"
scripts_dir=$(cd -- "$(dirname -- "$0")" && pwd)
publish=false
case "${1:-}" in
    --github) publish=true ;;
    '') ;;
    *) echo 'Usage: setup-clipbeak-signing.sh [--github]' >&2; exit 2 ;;
esac

if "$publish"; then
    # An API failure must stop setup. A missing local backup must not replace a published identity.
    pinned=$(gh api --paginate "repos/$repo/actions/variables" --jq \
        '.variables[] | select(.name == "CLIPBEAK_CERT_SHA256") | .value')
    if [[ -n "$pinned" && ! -f "$CLIPBEAK_KEYSTORE_PATH" ]]; then
        echo 'Restore the existing ClipBeak keystore from your backup before continuing.' >&2
        exit 1
    fi
fi

mkdir -p "$folder"
if ! IFS= read -rs -p 'ClipBeak keystore password: ' CLIPBEAK_STORE_PASSWORD; then exit 1; fi
printf '\n' >&2
export CLIPBEAK_STORE_PASSWORD
if [[ ! -f "$CLIPBEAK_KEYSTORE_PATH" ]]; then
    IFS= read -rs -p 'Confirm password: ' confirmation
    printf '\n' >&2
    [[ "$CLIPBEAK_STORE_PASSWORD" == "$confirmation" ]] || { echo 'Passwords differ.' >&2; exit 1; }
    unset confirmation
    # Link atomically so concurrent setup cannot replace a key.
    stage=$(mktemp -d "$folder/.setup.XXXXXX")
    trap 'rm -rf -- "$stage"' EXIT
    keytool -genkeypair -alias clipbeak -storetype PKCS12 -keystore "$stage/key.p12" \
        -storepass:env CLIPBEAK_STORE_PASSWORD -keyalg RSA -keysize 4096 -validity 10000 \
        -dname 'CN=ClipBeak,O=ClipBeak' -noprompt
    ln "$stage/key.p12" "$CLIPBEAK_KEYSTORE_PATH"
fi
fingerprint=$("$scripts_dir/signing-fingerprint.sh")
if "$publish"; then
    if [[ -n "$pinned" && "$pinned" != "$fingerprint" ]]; then
        echo 'Fingerprint mismatch. Restore the keystore matching the repository fingerprint.' >&2
        exit 1
    fi
    gh variable set CLIPBEAK_CERT_SHA256 --repo "$repo" --body "$fingerprint"
    printf '%s' "$CLIPBEAK_STORE_PASSWORD" | gh secret set CLIPBEAK_STORE_PASSWORD --repo "$repo"
    base64 < "$CLIPBEAK_KEYSTORE_PATH" | tr -d '\r\n' | gh secret set CLIPBEAK_KEYSTORE_BASE64 --repo "$repo"
fi
printf 'Keystore: %s\nPublic SHA-256: %s\nBack up the keystore and its password.\n' "$CLIPBEAK_KEYSTORE_PATH" "$fingerprint"
