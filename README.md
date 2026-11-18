<div align="center">

<img src="fastlane/metadata/android/en-US/images/icon.png" width="120" alt="Icono de Nexo" />

# Nexo

**Descargador de video y audio para Android, con interfaz Material 3 Expressive.**
Basado en [Seal](https://github.com/JunkFood02/Seal) · Funciona con [yt-dlp](https://github.com/yt-dlp/yt-dlp)

[![Licencia: GPL v3](https://img.shields.io/badge/Licencia-GPLv3-blue.svg)](LICENSE)

</div>

## Qué es Nexo

Nexo es un fork de **Seal**, la app de JunkFood02 que ofrece una interfaz gráfica para yt-dlp. Nexo mantiene todas las funciones de Seal y cambia la identidad y la experiencia visual:

- Interfaz rediseñada con **Material 3 Expressive**: movimiento con física de resorte, formas más redondeadas, indicadores de carga que cambian de forma y barras de progreso onduladas.
- Paleta e icono propios.
- Puede instalarse junto a Seal sin conflicto, porque usa otro identificador de paquete (`io.github.gabrieldave.nexo`).

La lista completa de cambios respecto a Seal está en [NOTICE.md](NOTICE.md).

## Funciones (heredadas de Seal)

- Descarga video y audio de las plataformas que soporta [yt-dlp](https://github.com/yt-dlp/yt-dlp/blob/master/supportedsites.md).
- Incrusta metadatos y miniaturas en el audio extraído, con [mutagen](https://github.com/quodlibet/mutagen).
- Descarga listas de reproducción completas con un toque.
- Usa [aria2c](https://github.com/aria2/aria2) como descargador externo.
- Incrusta subtítulos en los videos.
- Ejecuta comandos personalizados de yt-dlp con plantillas.

## Descarga

Descarga el APK desde [GitHub Releases](https://github.com/gabrieldave/Nexo/releases). Para la mayoría de los teléfonos conviene la versión **arm64-v8a**.

## Compilar desde el código fuente

1. Instala Android Studio (o el SDK de Android con JDK 17 o superior).
2. Clona el repositorio: `git clone https://github.com/gabrieldave/Nexo`
3. Compila: `./gradlew assembleGenericRelease`

Para firmar las versiones de lanzamiento, crea un archivo `keystore.properties` en la raíz (no lo subas al repositorio) con `storeFile`, `storePassword`, `keyAlias` y `keyPassword`.

## Licencia

Nexo es software libre bajo la **GNU General Public License v3.0**. Puedes usarlo, estudiarlo, modificarlo y redistribuirlo según sus términos. El texto completo está en [LICENSE](LICENSE).

Este programa se distribuye **sin ninguna garantía**.

> [!IMPORTANT]
> Seal es una marca de su autor. Su README prohíbe usar el nombre "Seal" en forks y builds no oficiales. Por eso este proyecto se llama Nexo y no está afiliado a Seal ni a su autor, ni cuenta con su respaldo.

## Créditos

- **[Seal](https://github.com/JunkFood02/Seal)**, de JunkFood02 y sus colaboradores, la base de este proyecto. Si Nexo te resulta útil, considera [apoyar al desarrollador de Seal](https://github.com/sponsors/JunkFood02).
- [yt-dlp](https://github.com/yt-dlp/yt-dlp), [youtubedl-android](https://github.com/yausername/youtubedl-android), [FFmpeg](https://ffmpeg.org), [aria2](https://github.com/aria2/aria2) y el resto de bibliotecas que aparecen en la pantalla de Créditos de la app.
- Traducciones: la comunidad de Seal en [Hosted Weblate](https://hosted.weblate.org/projects/seal/).

## Uso responsable

Nexo es una herramienta. Respeta los derechos de autor y los términos de servicio de cada sitio. Descarga solo contenido que tengas derecho a guardar.

---

## English

**Nexo** is a fork of [Seal](https://github.com/JunkFood02/Seal) by JunkFood02, redesigned with Material 3 Expressive. It is licensed under the GNU GPL v3.0 and is not affiliated with or endorsed by the Seal project. See [NOTICE.md](NOTICE.md) for the list of modifications.
