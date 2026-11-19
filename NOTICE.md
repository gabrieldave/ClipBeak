# Aviso de obra modificada (NOTICE)

**Nexo** es el proyecto Android de **gabrieldave**, quien rediseñó la interfaz sobre la base de **Seal**. **ClipBeak** es el nuevo nombre de Nexo.

- Proyecto original: Seal — https://github.com/JunkFood02/Seal
- Copyright del original: (C) JunkFood02 y colaboradores
- Versión de partida: commit `7677f61a20fda4210e84215fef9e9b51d25daa47` (25 de septiembre de 2026), versión 2.0.0-alpha.5
- Copyright de las modificaciones: (C) 2026 gabrieldave
- Licencia: GNU General Public License v3.0 (GPL-3.0-only). Consulta [LICENSE](LICENSE). La licencia cubre la obra derivada. Los componentes de terceros conservan sus propios avisos.

Este archivo existe para cumplir la sección 5(a) de la GPLv3, que pide avisos visibles de que la obra fue modificada y la fecha de los cambios.

## Nombre y marca

El README de Seal prohíbe usar el nombre "Seal" en derivados como forks o builds no oficiales. ClipBeak tiene nombre, icono e identificador de paquete propios, y no dice estar afiliado al proyecto Seal.

Los nombres internos del código (por ejemplo el paquete Kotlin `com.junkfood.seal`, `SealTheme` o el recurso `ic_stat_seal`) se conservan a propósito. No se muestran al usuario y facilitan incorporar futuras correcciones de Seal.

## Identidad pública de ClipBeak (19 de noviembre de 2026)

El nombre de la app pasa de Nexo a ClipBeak. Un pájaro con el símbolo de reproducción en el ala identifica la app, las notificaciones y la ficha de distribución. Para las nuevas instalaciones, ClipBeak adopta el identificador Android `io.github.gabrieldave.clipbeak`, una firma propia y la carpeta `Download/ClipBeak`. Las versiones alfa de Nexo conservan su identidad anterior. La pantalla de licencia incorpora la GPLv3 para su lectura sin conexión.

## Modificaciones (18 de noviembre de 2026)

### Identidad
- Nombre de la app cambiado a "Nexo" en todos los idiomas (`app_name` y textos que mencionaban la app).
- Identificador de paquete: `io.github.gabrieldave.nexo` (antes `com.junkfood.seal`).
- Versión propia: 1.0.0-alpha.1 (`buildSrc/src/main/kotlin/Version.kt`).
- Nuevo icono de lanzador, icono monocromo e icono de notificación.
- Carpeta de descargas por defecto: `Download/Nexo`.
- El actualizador integrado consulta las releases de `gabrieldave/Nexo` (antes las de Seal).
- Pantalla "Acerca de": enlaces al repositorio de Nexo; se agregaron "Basado en Seal", "Apoya el proyecto original" y "Licencia y aviso legal" (avisos legales apropiados según la sección 5(d) de la GPLv3). Se quitaron los enlaces a los canales de Telegram y Matrix de Seal.
- Se quitó la tarjeta recurrente de patrocinio de la pantalla de Ajustes.
- Repositorio: README nuevo, este NOTICE y la entrada en CHANGELOG. Se eliminaron el workflow de patrocinadores, `FUNDING.yml`, las traducciones del README de Seal, las capturas y los changelogs de F-Droid de Seal. Se actualizaron las plantillas de issues y los títulos de fastlane.

### Interfaz (Material 3 Expressive)
- Tema basado en `MaterialExpressiveTheme` con `MotionScheme.expressive()` (animaciones con física de resorte).
- Escala de formas más redondeada (`ui/theme/Shape.kt`).
- Tipografía con títulos enfatizados (`ui/theme/Type.kt`).
- Color semilla por defecto: índigo Nexo (`#3B3FD1`).
- `LoadingIndicator` (forma que cambia) en vez del indicador circular al cargar la información de un video.
- Indicador de progreso circular ondulado en las descargas activas.
- Botón flotante de descarga más grande (`MediumFloatingActionButton`).
- Botón "Reintentar" traducible (antes estaba fijo en inglés).
- Se eliminó una vista previa de depuración en `ModalBottomSheetM2.kt`.

### Inicio, navegación y pantallas grandes (18 de noviembre de 2026, segunda entrega)
- Pantalla de inicio nueva (`ui/page/downloadv2/NexoHome.kt`): tarjeta para pegar un enlace con botón de pegar y botón de descargar, que abre directamente la configuración de la descarga. El botón flotante solo aparece al desplazarse.
- El menú lateral se sustituyó por navegación adaptable (`ui/page/NexoNavigation.kt`): barra inferior (`ShortNavigationBar`) en teléfonos y riel lateral (`WideNavigationRail`) en plegables abiertos, tablets y teléfonos en horizontal. Las pestañas principales ya no muestran flecha de "atrás".
- Soporte para plegables (`ui/common/Foldables.kt`, dependencia `androidx.window`): dos paneles en pantallas anchas, división en la bisagra en postura libro y descargas arriba con el campo de enlace abajo en postura mesa.
- `MainActivity` gestiona por sí misma los cambios de tamaño (`configChanges`), para que el estado no se pierda al plegar o desplegar.
- Teléfono en horizontal: dos paneles y vista de lista por defecto.
- Marca visible: color índigo de Nexo por defecto (los colores del fondo de pantalla siguen disponibles en Ajustes > Apariencia) con estilo de paleta "Vibrant".
- Tipografía de marca Outfit para títulos (ver "Componentes de terceros").
- Créditos de la app: se agregaron Seal y la fuente Outfit.

### Estilo unificado y preparación para publicar (18 de noviembre de 2026, tercera entrega)
- Ajustes y todas sus subpantallas: filas como tarjetas redondeadas con iconos en círculos tonales (`ui/component/PreferenceItems.kt`, `ui/component/SettingItem.kt`); subtítulos alineados con las tarjetas.
- Historial: elementos como tarjetas y miniaturas redondeadas (`ui/component/VideoListItem.kt`).
- Comandos: encabezado grande y botón flotante mediano (`ui/page/command/TaskListPage.kt`).
- `versionCode` calculado directamente desde `Version.kt` (antes estaba duplicado a mano).
- Workflows nuevos: `build.yml` (compila en cada push y PR, también en forks, sin secretos) y `release.yml` (firma y publica al subir una etiqueta `v*`; en forks sin llave se detiene sin fallar). Se eliminaron los workflows de Seal (`android.yml`, `android_ci.yml`, `Issue-Handler.yaml`), que dependían de sus secretos y de su tracker de issues.
- `.gitignore` excluye llaves (`*.jks`, `*.keystore`, `keystore.properties`).
- README con guía paso a paso para la comunidad, CONTRIBUTING propio y capturas de Nexo en `docs/screenshots/` y en fastlane.

### CI: Android SDK en Actions (18 de noviembre de 2026)
- `build.yml` y `release.yml`: `android-actions/setup-android` actualizado de `@v3` a `@v4`, porque la v3 intenta instalar el paquete obsoleto `tools` del SDK y el workflow Build fallaba.

### Notificación al terminar descarga (18 de noviembre de 2026)
- Canal nuevo `download_completed` con importancia por defecto y sonido del sistema; las notificaciones de progreso siguen en el canal silencioso. `NotificationUtil.finishNotification` y `finishNotificationForCustomCommands` usan ese canal.
- Versión: `1.0.0-alpha.2`.

## Componentes de terceros agregados por Nexo

- **Outfit**, de The Outfit Project Authors (https://github.com/Outfitio/Outfit-Fonts), con licencia SIL Open Font License 1.1. Archivos: `app/src/main/res/font/outfit_*.ttf`. El texto de la licencia acompaña a la app en `app/src/main/assets/licenses/OFL-Outfit.txt`. La OFL permite incluir la fuente en software con cualquier licencia, siempre que la fuente no se venda por separado.

Cada cambio en el código se puede rastrear en el historial de git de este repositorio.

## Código fuente

Cada publicación enlaza a la etiqueta del código fuente de la aplicación. El proyecto original y sus contribuciones conservan los avisos de autoría.

## Fuentes de FFmpeg y aria2

Las versiones 1.0.0-alpha.1 y 1.0.0-alpha.2 incorporan los módulos `ffmpeg` y `aria2c` de [youtubedl-android 0.17.3](https://github.com/yausername/youtubedl-android/tree/900f27ae0aadc1ef56748e6b093c42ba756cbba4). Los 20 archivos nativos de esos dos módulos coinciden byte por byte con los de los APK universales de ambas versiones.

| Componente | Fuentes del programa | Integración Android distribuida |
| --- | --- | --- |
| FFmpeg 7.0.1 | [Fuente de FFmpeg n7.0.1](https://github.com/FFmpeg/FFmpeg/tree/n7.0.1) | [Módulo Android](https://github.com/yausername/youtubedl-android/tree/900f27ae0aadc1ef56748e6b093c42ba756cbba4/ffmpeg) y [actualización de los binarios](https://github.com/yausername/youtubedl-android/pull/283). El binario declara GPL-3.0-or-later. Incorpora las opciones `--enable-gpl` y `--enable-version3`. |
| aria2 1.37.0 | [Fuente de aria2 1.37.0](https://github.com/aria2/aria2/tree/release-1.37.0) | [Módulo Android](https://github.com/yausername/youtubedl-android/tree/900f27ae0aadc1ef56748e6b093c42ba756cbba4/aria2c) y [actualización de los binarios](https://github.com/yausername/youtubedl-android/pull/299). El programa declara GPL-2.0-or-later. [Receta y parches de Termux para la versión 1.37.0](https://github.com/termux/termux-packages/tree/559a6e84638a6dbb1883861e8f258cde165bfd4d/packages/aria2). |

El integrador describe [cómo preparar paquetes Android](https://github.com/yausername/youtubedl-android/blob/900f27ae0aadc1ef56748e6b093c42ba756cbba4/README.md#docs). La revisión de estos dos paquetes sigue abierta respecto de las revisiones de sus dependencias, los parches y los scripts exactos empleados al producir los binarios publicados.

Las [20 huellas SHA-256](docs/native-0.17.3.sha256) corresponden a los archivos del commit del integrador y a los dos APK universales. Para cotejar una copia, extrae su directorio `lib/` y ejecuta `sha256sum -c /ruta/a/native-0.17.3.sha256` desde la carpeta de extracción.

FFmpeg identifica Android NDK 25.2.9519653 y un directorio de compilación llamado `build-ffmpeg`. La receta de Termux documenta una compilación de aria2 1.37.0. Para reproducir los binarios distribuidos faltan el commit del entorno de FFmpeg y sus códecs, las revisiones de los paquetes Termux y los pasos de empaquetado de ambas bibliotecas. Estas huellas sirven para identificar los binarios publicados. La procedencia completa de sus compilaciones sigue pendiente.
