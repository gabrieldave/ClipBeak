# Contributing to ClipBeak

ClipBeak is gabrieldave’s Android project, previously called Nexo, based on [Seal](https://github.com/JunkFood02/Seal). It provides an interface for [yt-dlp](https://github.com/yt-dlp/yt-dlp).

## Reports and feature requests

Search open and closed issues first. For a site-specific download error, check [yt-dlp’s issue tracker](https://github.com/yt-dlp/yt-dlp/issues). In a ClipBeak report, include your app version, Android version, steps to reproduce and a public example link. Review logs and screenshots for cookies, account details, private URLs and device identifiers before sharing them.

ClipBeak supports features available in yt-dlp. For advanced options, try Settings → Custom command.

## Pull requests

1. Describe the change in an existing or new issue so others can coordinate.
2. Create a branch from `main`.
3. Run `./gradlew ktfmtFormat` and `./gradlew assembleGenericDebug`. See the [build instructions](README.md#build-from-source).
4. Include screenshots for interface changes.
5. Record and date modifications to inherited Seal code in [NOTICE.md](NOTICE.md).

Contributions are distributed under the project’s [GNU General Public License v3.0](LICENSE). Keep signing keys, passwords and local configuration outside the repository.
