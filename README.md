<div align="center">

<img src="fastlane/metadata/android/en-US/images/icon.png" width="120" alt="ClipBeak bird icon" />

# ClipBeak

**English** · [Español](README.es.md)

**Download video and audio on Android. Paste a link, choose a format and save it to your device.**

ClipBeak is the new name of **Nexo**, an Android project created and maintained by **gabrieldave**. The interface redesign, navigation and foldable layouts are gabrieldave’s contributions to Nexo. The original app, [Seal](https://github.com/JunkFood02/Seal), was developed by JunkFood02 and the Seal contributors. Downloads are powered by [yt-dlp](https://github.com/yt-dlp/yt-dlp).

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)
[![Build](https://github.com/gabrieldave/ClipBeak/actions/workflows/build.yml/badge.svg)](https://github.com/gabrieldave/ClipBeak/actions/workflows/build.yml)

<p>Screenshots from ClipBeak 1.0.0-alpha.3.</p>

<img src="docs/screenshots/inicio.png" width="30%" alt="Home screen with a field for a video link" />
<img src="docs/screenshots/ajustes.png" width="30%" alt="Download and appearance settings" />
<img src="docs/screenshots/inicio-oscuro.png" width="30%" alt="Home screen in dark mode" />

</div>

## Contents

1. [Features](#features)
2. [Install](#install)
3. [Build from source](#build-from-source)
4. [Sign your own builds](#sign-your-own-builds)
5. [Publish with GitHub Actions](#publish-with-github-actions)
6. [Contribute](#contribute)
7. [Create a fork](#create-a-fork)
8. [License and credits](#license-and-credits)

## Features

- Paste a link on the home screen to configure a download.
- Download videos, extract audio, save playlists and embed subtitles from [sites supported by yt-dlp](https://github.com/yt-dlp/yt-dlp/blob/master/supportedsites.md). Seal also provides audio metadata and thumbnails, optional aria2c downloads and custom command templates.
- Navigate with a bottom bar on phones or a side rail on wider screens. Foldable layouts place content on either side of the hinge and adapt to tabletop mode.
- The redesigned interface uses Material 3 Expressive, Outfit type, an indigo palette and an orange bird icon. ClipBeak’s release application ID is `io.github.gabrieldave.clipbeak`.

<img src="docs/screenshots/plegables.png" width="70%" alt="Example layouts on a foldable device" />

See [NOTICE.md](NOTICE.md) for the dated list of changes from Seal.

## Install

ClipBeak requires Android 7.0 or later and follows the system language by default (English fallback). Switch manually to English or another language under Settings → Look & feel → Language. ClipBeak installs alongside Nexo and Seal. Nexo retains its settings, history and downloads. New downloads default to `Download/ClipBeak`.

The two Nexo alpha releases now provide historical source and license notices. Their signed APKs have been withdrawn. A public ClipBeak release is pending.

**To try ClipBeak:** sign in to GitHub, open a [successful build on the main branch](https://github.com/gabrieldave/ClipBeak/actions/workflows/build.yml?query=branch%3Amain) and download `clipbeak-debug-apk` under **Artifacts**. Unzip it and open the APK on an ARM64 Android device. It appears as **ClipBeak Debug** and installs alongside the Nexo release under a separate application ID. Each build artifact is kept for 14 days.

**When the first ClipBeak release is available:**

1. Open [GitHub Releases](https://github.com/gabrieldave/ClipBeak/releases).
2. Choose the APK for your device. Most recent Android phones use **arm64-v8a**. The larger **universal** APK includes every supported architecture.
3. Open the APK. If Android prompts you, grant your browser or file manager permission to install apps from that source.
4. For in-app updates, see Settings → About → Auto-update.

## Build from source

Install **JDK 21** and **Android SDK Platform 35**. Allow about 10 GB for Gradle and the SDK. [Android Studio](https://developer.android.com/studio) can install the SDK and set its location for you.

```bash
git clone https://github.com/gabrieldave/ClipBeak.git
cd Nexo
./gradlew assembleGenericDebug
```

If Gradle cannot locate the SDK, create `local.properties` at the repository root and set `sdk.dir` to the full path of your SDK. On Windows, run `gradlew.bat` in place of `./gradlew`.

| Variant | Command | Output |
| --- | --- | --- |
| `genericDebug` | `./gradlew assembleGenericDebug` | Debug-signed APKs for testing in `app/build/outputs/apk/generic/debug/` |
| `genericRelease` | `./gradlew assembleGenericRelease` | Optimized release APKs. Configure signing before installing or distributing them. |
| `fdroidRelease` | `./gradlew assembleFdroidRelease` | F-Droid build with the in-app updater disabled |

## Sign your own builds

On macOS or Linux with JDK 21 and OpenSSL, run this on your own computer:

```bash
scripts/setup-clipbeak-signing.sh
```

The script prompts for a password and creates a PKCS12 keystore at `~/.config/clipbeak/release.p12` (or under `XDG_CONFIG_HOME`). The certificate embedded in each APK identifies the project as ClipBeak. Back up the keystore and password privately. Future ClipBeak updates require the same signing key.

To build locally, set `CLIPBEAK_KEYSTORE_PATH` to the keystore path and `CLIPBEAK_STORE_PASSWORD` to its password in your local environment, then run `./gradlew --no-configuration-cache assembleGenericRelease`. Debug builds use Android’s debug signer.

## Publish with GitHub Actions

From a computer with the GitHub CLI signed in to your repository, run:

```bash
scripts/setup-clipbeak-signing.sh --github
```

The script creates or reuses the local key. It registers two Actions secrets, `CLIPBEAK_KEYSTORE_BASE64` and `CLIPBEAK_STORE_PASSWORD`, and the public certificate fingerprint in `CLIPBEAK_CERT_SHA256`. Fork maintainers must set `CLIPBEAK_REPOSITORY=owner/repo` to target their repository.

The [Build workflow](.github/workflows/build.yml) tests branches without release credentials. For a public release, review the [native component source notes](NOTICE.md#ffmpeg-and-aria2-sources), update [Version.kt](buildSrc/src/main/kotlin/Version.kt) and push a matching version tag prefixed with `v`. The [Release workflow](.github/workflows/release.yml) checks the package and signature of all five APKs before uploading them to a draft release. Publish the draft after the corresponding-source and privacy reviews pass.

## Contribute

Fork the repository, create a branch and open an issue before starting a large change. Format Kotlin with `./gradlew ktfmtFormat` and build with `./gradlew assembleGenericDebug`. Include before-and-after screenshots in pull requests that change the interface. See [CONTRIBUTING.md](CONTRIBUTING.md).

Translation files live under `app/src/main/res/values-*/strings.xml`. See the [English strings](app/src/main/res/values/strings.xml) and [Spanish strings](app/src/main/res/values-es/strings.xml) for text introduced by ClipBeak. For site-specific download failures, check the [yt-dlp issue tracker](https://github.com/yt-dlp/yt-dlp/issues).

## Create a fork

Keep the GPLv3 license and existing copyright notices, identify and date your modifications, and provide the corresponding source for the APKs you distribute.

Please choose a distinct name and icon, set your own application ID in [app/build.gradle.kts](app/build.gradle.kts), and point [the updater](app/src/main/java/com/junkfood/seal/util/UpdateUtil.kt) to your releases. The Seal project reserves the Seal name for its own downloader.

## License and credits

ClipBeak is free software under the [GNU General Public License v3.0](LICENSE), distributed without warranty. The full license is also available offline from Settings → About → License and legal notice. Third-party components retain their own notices.

- **[Seal](https://github.com/JunkFood02/Seal)** by JunkFood02 and contributors is the original Android app. ClipBeak is an independent fork without affiliation or endorsement. [Support Seal’s original developer](https://github.com/sponsors/JunkFood02).
- [yt-dlp](https://github.com/yt-dlp/yt-dlp), [youtubedl-android](https://github.com/yausername/youtubedl-android), [FFmpeg](https://ffmpeg.org) and [aria2](https://github.com/aria2/aria2) provide downloading and media processing. See the [native component source notes](NOTICE.md#ffmpeg-and-aria2-sources) and the app’s Credits screen.
- [Outfit](https://github.com/Outfitio/Outfit-Fonts) is bundled under the [SIL Open Font License 1.1](app/src/main/assets/licenses/OFL-Outfit.txt).
- Community translations originated on [Seal’s Weblate project](https://hosted.weblate.org/projects/seal/).

## Responsible use

Download content you have the right to save. Respect copyright and each site’s terms of service.
