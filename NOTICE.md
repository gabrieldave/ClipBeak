# Aviso de obra modificada (NOTICE)

**Nexo** es una versión modificada de **Seal**.

- Proyecto original: Seal — https://github.com/JunkFood02/Seal
- Copyright del original: (C) JunkFood02 y colaboradores
- Versión de partida: commit `7677f61a20fda4210e84215fef9e9b51d25daa47` (25 de septiembre de 2026), versión 2.0.0-alpha.5
- Copyright de las modificaciones: (C) 2026 gabrieldave
- Licencia: GNU General Public License v3.0 o posterior (ver `LICENSE`). Todo el proyecto, incluidas las modificaciones, se distribuye bajo esta licencia.

Este archivo existe para cumplir la sección 5(a) de la GPLv3, que pide avisos visibles de que la obra fue modificada y la fecha de los cambios.

## Nombre y marca

El README de Seal prohíbe usar el nombre "Seal" en derivados como forks o builds no oficiales. Nexo cumple con esto: tiene nombre, icono e identificador de paquete propios, y no dice estar afiliado al proyecto Seal.

Los nombres internos del código (por ejemplo el paquete Kotlin `com.junkfood.seal`, `SealTheme` o el recurso `ic_stat_seal`) se conservan a propósito. No se muestran al usuario y facilitan incorporar futuras correcciones de Seal.

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

Cada cambio en el código se puede rastrear en el historial de git de este repositorio.

## Código fuente

El código fuente completo de cada versión publicada de Nexo está en https://github.com/gabrieldave/Nexo. La GPLv3 (sección 6) exige ofrecer el código fuente correspondiente junto con cada APK distribuido.
