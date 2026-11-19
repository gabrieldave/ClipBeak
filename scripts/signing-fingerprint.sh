#!/usr/bin/env bash
set -euo pipefail

# Only the public certificate is exported. Keep certificate subjects out of CI logs.
certificate=$(keytool -exportcert -rfc -alias clipbeak -storetype PKCS12 \
    -keystore "${CLIPBEAK_KEYSTORE_PATH:?}" -storepass:env CLIPBEAK_STORE_PASSWORD)
subject=$(openssl x509 -noout -subject -nameopt RFC2253 <<<"$certificate")
subject=${subject#subject=}
subject=${subject# }
case "$subject" in
    'CN=ClipBeak,O=ClipBeak'|'O=ClipBeak,CN=ClipBeak') ;;
    *) echo 'The signing certificate must identify the ClipBeak project in CN and O only.' >&2; exit 1 ;;
esac
openssl x509 -outform DER <<<"$certificate" | openssl dgst -sha256 -r | cut -d ' ' -f1
