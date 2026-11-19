# Notice of modifications

**Nexo** is gabrieldave’s Android project. **ClipBeak** is its new name. gabrieldave redesigned the interface on top of [Seal](https://github.com/JunkFood02/Seal), originally developed by JunkFood02 and the Seal contributors. [Español](NOTICE.es.md).

| Work | Attribution |
| --- | --- |
| Seal | Copyright © JunkFood02 and contributors. Based on [Seal 2.0.0-alpha.5, commit 7677f61](https://github.com/JunkFood02/Seal/tree/7677f61a20fda4210e84215fef9e9b51d25daa47), September 25, 2026. |
| Modifications for Nexo and ClipBeak | Copyright © 2026 gabrieldave. |
| License | [GNU General Public License version 3](LICENSE). Third-party components retain their own licenses and notices. |

The Seal project reserves its name for official builds. ClipBeak has its own name, icon and Android application ID and is independently maintained. Internal identifiers such as `com.junkfood.seal` preserve continuity with the upstream source.

## November 19, 2026

Nexo was renamed ClipBeak and received an orange bird icon for the launcher, notifications and listing. The app gained an offline copy of GPLv3 under Settings → About → License and legal notice. The release package is `io.github.gabrieldave.clipbeak`, with a separate signing identity and `Download/ClipBeak` as the default directory. New installations start in English and retain the existing language selector. The Nexo alpha APKs were withdrawn. Their source tags remain available.

## November 18, 2026

gabrieldave created Nexo from Seal. The changes included:

- An independent application ID, version, launcher and notification icons, download directory and in-app updater targeting this repository. The About screen added links to Seal, its sponsorship page and license information. The recurring sponsorship card and upstream chat links were removed.
- A Material 3 Expressive interface, rounded shapes, indigo color scheme, Outfit headings, expressive animations and progress indicators. The download action and retry label were revised.
- A home screen for pasting links, adaptive navigation for phones and tablets, fold-aware two-pane layouts and state preservation when folding or resizing. Settings and history entries became cards. The command screen gained a larger heading and a floating action button.
- Build and release workflows, a single version definition, a community README and contribution guide, updated store metadata, fork-specific screenshots and repository templates. Upstream release workflows, funding configuration, README translations, screenshots and F-Droid changelogs were removed.
- A separate notification channel for completed downloads with the system sound. Progress notifications remain silent. See [CHANGELOG.md](CHANGELOG.md) and the Git history for individual changes.

## Third-party materials

The bundled Outfit font is by The Outfit Project Authors. Its [SIL Open Font License 1.1](app/src/main/assets/licenses/OFL-Outfit.txt) accompanies the font in the app. [Seal](https://github.com/JunkFood02/Seal), [yt-dlp](https://github.com/yt-dlp/yt-dlp), [youtubedl-android](https://github.com/yausername/youtubedl-android) and other contributors are also credited in the app.

<a id="fuentes-de-ffmpeg-y-aria2"></a>

## FFmpeg and aria2 sources

Nexo 1.0.0-alpha.1 and 1.0.0-alpha.2 shipped the `ffmpeg` and `aria2c` modules from [youtubedl-android 0.17.3](https://github.com/yausername/youtubedl-android/tree/900f27ae0aadc1ef56748e6b093c42ba756cbba4). All 20 native files in the two universal APKs match that commit byte for byte. The current app uses the same modules.

| Component | Source and Android build information |
| --- | --- |
| FFmpeg 7.0.1 | [Source tag n7.0.1](https://github.com/FFmpeg/FFmpeg/tree/n7.0.1), [Android module](https://github.com/yausername/youtubedl-android/tree/900f27ae0aadc1ef56748e6b093c42ba756cbba4/ffmpeg), [binary update](https://github.com/yausername/youtubedl-android/pull/283). The binary declares GPL-3.0-or-later and records `--enable-gpl --enable-version3`, Android NDK 25.2.9519653 and a build directory named `build-ffmpeg`. |
| aria2 1.37.0 | [Source tag release-1.37.0](https://github.com/aria2/aria2/tree/release-1.37.0), [Android module](https://github.com/yausername/youtubedl-android/tree/900f27ae0aadc1ef56748e6b093c42ba756cbba4/aria2c), [binary update](https://github.com/yausername/youtubedl-android/pull/299). The program declares GPL-2.0-or-later. A [Termux recipe and patches](https://github.com/termux/termux-packages/tree/559a6e84638a6dbb1883861e8f258cde165bfd4d/packages/aria2) exist for this version. |

To verify an archived universal APK, extract its `lib/` directory and run `sha256sum -c /path/to/native-0.17.3.sha256` from the directory containing `lib/` with the [recorded checksums](docs/native-0.17.3.sha256). The integrator also provides [packaging notes](https://github.com/yausername/youtubedl-android/blob/900f27ae0aadc1ef56748e6b093c42ba756cbba4/README.md#docs).

[Source audit, issue 1](https://github.com/gabrieldave/ClipBeak/issues/1) tracks the exact FFmpeg build and codec revisions, Termux dependency revisions, patches and packaging commands for the shipped binaries. The sources, exact tags and SHA-256 checksums are documented in this file and in `docs/native-0.17.3.sha256`.
