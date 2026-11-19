<div align="center">

<img src="fastlane/metadata/android/en-US/images/icon.png" width="120" alt="Icono de ClipBeak" />

# ClipBeak

[English](README.md) · **Español**

**Descargador de video y audio para Android, con interfaz Material 3 Expressive.**
**ClipBeak es el nuevo nombre de Nexo**, el proyecto Android creado y mantenido por **gabrieldave**. El rediseño de la interfaz, la navegación y la adaptación a plegables son aportaciones de gabrieldave a Nexo. La aplicación original, [Seal](https://github.com/JunkFood02/Seal), es obra de JunkFood02 y sus colaboradores. Motor de descarga: [yt-dlp](https://github.com/yt-dlp/yt-dlp).

[![Licencia: GPL v3](https://img.shields.io/badge/Licencia-GPLv3-blue.svg)](LICENSE)
[![Build](https://github.com/gabrieldave/Nexo/actions/workflows/build.yml/badge.svg)](https://github.com/gabrieldave/Nexo/actions/workflows/build.yml)

<p>Capturas de la versión 1.0.0-alpha.2, publicada como Nexo.</p>

<img src="docs/screenshots/inicio.png" width="30%" alt="Inicio" />
<img src="docs/screenshots/ajustes.png" width="30%" alt="Ajustes" />
<img src="docs/screenshots/inicio-oscuro.png" width="30%" alt="Inicio en modo oscuro" />

</div>

## Índice

1. [Qué es ClipBeak](#qué-es-clipbeak)
2. [Instalar](#instalar)
3. [Compilar desde el código fuente](#compilar-desde-el-código-fuente)
4. [Firmar tus propias versiones](#firmar-tus-propias-versiones)
5. [Publicar una release con GitHub Actions](#publicar-una-release-con-github-actions)
6. [Contribuir](#contribuir)
7. [Si haces tu propio fork de ClipBeak](#si-haces-tu-propio-fork-de-clipbeak)
8. [Licencia y créditos](#licencia-y-créditos)

## Qué es ClipBeak

ClipBeak es un fork de **Seal**, la app de JunkFood02 que ofrece una interfaz gráfica para yt-dlp. Conserva todas las funciones de Seal y cambia la experiencia:

- **Inicio nuevo:** pegas un enlace y descargas desde la primera pantalla.
- **Material 3 Expressive:** movimiento con física de resorte, formas redondeadas, indicadores que cambian de forma y progreso ondulado.
- **Navegación adaptable:** barra inferior en teléfonos y riel lateral en tablets, plegables abiertos y teléfonos en horizontal.
- **Hecho para plegables:** dos paneles en pantallas anchas, nada queda partido por la bisagra y hay una distribución especial para la postura "mesa".
- **Identidad propia:** pájaro naranja con el símbolo de reproducción en el ala, tipografía Outfit y paleta índigo en la interfaz. Identificador Android de ClipBeak: `io.github.gabrieldave.clipbeak`.

<img src="docs/screenshots/plegables.png" width="70%" alt="ClipBeak en un plegable" />

Funciones heredadas de Seal: descarga de video y audio de los [sitios compatibles con yt-dlp](https://github.com/yt-dlp/yt-dlp/blob/master/supportedsites.md), metadatos y miniaturas en el audio, listas de reproducción completas, aria2c como descargador externo, subtítulos y comandos personalizados de yt-dlp con plantillas.

La lista completa de cambios respecto a Seal está en [NOTICE.md](NOTICE.md).

## Instalar

ClipBeak requiere Android 7.0 o superior. Las versiones públicas 1.0.0-alpha.1 y 1.0.0-alpha.2 llevan el nombre **Nexo**. La primera versión pública de ClipBeak se instalará junto a Nexo y Seal. Nexo conserva sus ajustes, historial y descargas. ClipBeak guardará las nuevas descargas en `Download/ClipBeak`.

**Para probar ClipBeak:** inicia sesión en GitHub, abre una [compilación correcta de la rama principal](https://github.com/gabrieldave/Nexo/actions/workflows/build.yml?query=branch%3Amain), baja a **Artifacts** y descarga `clipbeak-debug-apk`. Extrae el ZIP y abre el APK en tu Android de 64 bits ARM. Aparecerá como **ClipBeak Debug**. Tiene su propio identificador y se instala junto a la versión pública de Nexo. GitHub conserva cada archivo de prueba durante 14 días.

**Para instalar una versión pública:**

1. Entra a [Releases](https://github.com/gabrieldave/Nexo/releases).
2. Descarga el APK que corresponde a tu teléfono. Si no sabes cuál, usa **arm64-v8a**, que sirve para casi todos los teléfonos actuales. El APK **universal** funciona en todos, pero pesa más.
3. Ábrelo en el teléfono y permite "Instalar apps desconocidas" para tu navegador o gestor de archivos cuando Android lo pida.
4. Las actualizaciones llegan desde la propia app: Ajustes → Acerca de → Actualización automática.

## Compilar desde el código fuente

### Requisitos

- **JDK 21**, la versión configurada en el proyecto y en GitHub Actions.
- **Android SDK** con la plataforma 35. La forma más simple de tenerlo es instalar [Android Studio](https://developer.android.com/studio).
- Unos 10 GB libres para Gradle y el SDK.

### Pasos

```bash
# 1. Clonar
git clone https://github.com/gabrieldave/Nexo.git
cd Nexo

# 2. Indicar dónde está el SDK (Android Studio lo hace solo al abrir el proyecto)
# Si hace falta, crea local.properties con sdk.dir apuntando a tu SDK.

# 3. Compilar un APK de prueba
./gradlew assembleGenericDebug
```

El APK queda en `app/build/outputs/apk/generic/debug/`. Ese APK está firmado con una llave de depuración: sirve para probar, no para distribuir.

En Windows usa `gradlew.bat` en lugar de `./gradlew`.

### Variantes

| Variante | Comando | Para qué |
|---|---|---|
| `genericDebug` | `./gradlew assembleGenericDebug` | Probar en tu teléfono |
| `genericRelease` | `./gradlew assembleGenericRelease` | Versión optimizada (necesita llave para instalarse) |
| `fdroidRelease` | `./gradlew assembleFdroidRelease` | Compilación para F-Droid, sin actualizador integrado |

## Firmar tus propias versiones

En tu Mac o en Linux, con JDK 21 y OpenSSL, ejecuta:

```bash
scripts/setup-clipbeak-signing.sh
```

El script pide una contraseña y crea el almacén PKCS12 en `~/.config/clipbeak/release.p12` (o dentro de `XDG_CONFIG_HOME`). El certificado público de cada APK identifica al proyecto como ClipBeak. Guarda una copia privada del archivo y de su contraseña. Las siguientes actualizaciones de ClipBeak necesitan esa misma firma.

Para compilar en tu equipo, define `CLIPBEAK_KEYSTORE_PATH` con la ruta del almacén y `CLIPBEAK_STORE_PASSWORD` con la contraseña en tu entorno local. Ejecuta `./gradlew --no-configuration-cache assembleGenericRelease`. Las versiones de prueba llevan la firma de depuración de Android.

## Publicar una release con GitHub Actions

Desde una computadora con GitHub CLI y una sesión abierta para tu repositorio, ejecuta:

```bash
scripts/setup-clipbeak-signing.sh --github
```

El script crea la llave o recupera la existente. Registra dos secretos de Actions, `CLIPBEAK_KEYSTORE_BASE64` y `CLIPBEAK_STORE_PASSWORD`, y la huella pública en `CLIPBEAK_CERT_SHA256`. Si mantienes otro fork, define `CLIPBEAK_REPOSITORY=usuario/repositorio` para elegir el destino.

El [flujo Build](.github/workflows/build.yml) compila las ramas sin credenciales de publicación. Antes de publicar, revisa las [fuentes de los componentes nativos](NOTICE.md#fuentes-de-ffmpeg-y-aria2), actualiza [Version.kt](buildSrc/src/main/kotlin/Version.kt) y sube una etiqueta con esa versión precedida por `v`. El [flujo Release](.github/workflows/release.yml) verifica el paquete y la firma de los cinco APK antes de subirlos.

## Contribuir

1. Haz un fork y crea una rama: `git checkout -b mejora/descripcion-corta`.
2. Antes de un cambio grande, abre un issue para comentarlo.
3. Da formato al código con `./gradlew ktfmtFormat`.
4. Comprueba que compila con `./gradlew assembleGenericDebug`.
5. Abre un pull request con capturas de antes y después si cambias la interfaz.

**Traducciones:** los textos están en `app/src/main/res/values-*/strings.xml`. Los textos nuevos de ClipBeak están al final de `values/strings.xml` (inglés) y `values-es/strings.xml` (español). Puedes traducirlos a otros idiomas con un pull request.

**Errores de descarga:** casi siempre vienen de yt-dlp o del sitio, no de la app. Revisa primero los [issues de yt-dlp](https://github.com/yt-dlp/yt-dlp/issues).

## Si haces tu propio fork de ClipBeak

Puedes hacerlo: es software libre. La GPLv3 te pide:

- Mantener la licencia GPLv3 y los avisos de copyright de Seal y de ClipBeak.
- Indicar qué cambiaste y cuándo (por ejemplo, en un NOTICE.md como el de este repo).
- Publicar el código fuente completo de cada APK que distribuyas.

Además te pedimos, para no confundir a los usuarios:

- Usar **otro nombre y otro icono**. El nombre "Seal" está prohibido en derivados por decisión de su autor.
- Cambiar el `applicationId` en `app/build.gradle.kts` y el repositorio del actualizador en `app/src/main/java/com/junkfood/seal/util/UpdateUtil.kt`.

## Licencia y créditos

ClipBeak es software libre bajo la **GNU General Public License v3.0** ([LICENSE](LICENSE)) y se distribuye **sin ninguna garantía**. Dentro de la app, abre Ajustes → Acerca de → Licencia y aviso legal para leer el texto completo sin conexión.

> [!IMPORTANT]
> ClipBeak no está afiliado a Seal ni a su autor, ni cuenta con su respaldo.

- **[Seal](https://github.com/JunkFood02/Seal)**, de JunkFood02 y sus colaboradores, la base de este proyecto. Si ClipBeak te resulta útil, considera [apoyar al desarrollador de Seal](https://github.com/sponsors/JunkFood02).
- [Fuentes de las versiones de FFmpeg y aria2 incluidas en Android](NOTICE.md#fuentes-de-ffmpeg-y-aria2).
- [yt-dlp](https://github.com/yt-dlp/yt-dlp), [youtubedl-android](https://github.com/yausername/youtubedl-android), [FFmpeg](https://ffmpeg.org), [aria2](https://github.com/aria2/aria2) y el resto de bibliotecas que aparecen en la pantalla de Créditos de la app.
- Fuente [Outfit](https://github.com/Outfitio/Outfit-Fonts), con licencia SIL Open Font License 1.1 (`app/src/main/assets/licenses/OFL-Outfit.txt`).
- Traducciones heredadas de la comunidad de Seal en [Hosted Weblate](https://hosted.weblate.org/projects/seal/).

## Uso responsable

ClipBeak es una herramienta. Respeta los derechos de autor y los términos de servicio de cada sitio. Descarga solo contenido que tengas derecho a guardar.
