#!/usr/bin/env bash
set -euo pipefail
version=${1:?Version required}
output=${2:?Verified output directory required}
: "${EXPECTED_SIGNER:?Expected public signing fingerprint required}"
tools=$(find "${ANDROID_HOME:?}/build-tools" -name apksigner | sort -V | tail -1)
build_dir=app/build/outputs/apk/generic/release
mkdir -p "$output"
scratch=$(mktemp -d)
trap 'rm -rf -- "$scratch"' EXIT
for arch in arm64-v8a armeabi-v7a x86 x86_64 universal; do
    apk="$build_dir/ClipBeak-$version-generic-$arch-release.apk"
    [[ -s "$apk" ]] || { echo "Missing $arch APK" >&2; exit 1; }
    "$tools" verify --print-certs-pem "$apk" > "$scratch/certs"
    count=$(grep -c '^-----BEGIN CERTIFICATE-----' "$scratch/certs" || true)
    [[ "$count" == 1 ]] || { echo "Unexpected certificate count for $arch: $count" >&2; exit 1; }
    digest=$(openssl x509 -in "$scratch/certs" -outform DER | openssl dgst -sha256 -r | cut -d ' ' -f1)
    [[ "$digest" == "$EXPECTED_SIGNER" ]] || { echo "Signing fingerprint mismatch for $arch" >&2; exit 1; }
    package=$("$(dirname "$tools")/aapt2" dump badging "$apk" | sed -n "s/^package: name='\([^']*\)'.*/\1/p")
    [[ "$package" == io.github.gabrieldave.clipbeak ]] || { echo "Unexpected package: $package" >&2; exit 1; }
    unzip -p "$apk" assets/licenses/GPL-3.0.txt | cmp -s - LICENSE
    cp -- "$apk" "$output/"
    echo "Verified $arch package, signer and GPLv3 text."
done
