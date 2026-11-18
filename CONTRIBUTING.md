# Cómo contribuir a Nexo

¡Gracias por querer ayudar! Nexo es un fork de [Seal](https://github.com/JunkFood02/Seal) y sigue su misma idea: una interfaz simple para [yt-dlp](https://github.com/yt-dlp/yt-dlp), sin modificar su funcionamiento.

## Antes de abrir un issue

- Busca primero entre los issues existentes, también los cerrados.
- Los errores de descarga ("HTTP Error 403", "Sign in to confirm you're not a bot", etc.) casi siempre vienen de yt-dlp o del sitio. Revisa los [issues de yt-dlp](https://github.com/yt-dlp/yt-dlp/issues) antes de reportarlos aquí.
- Usa las plantillas de issue y describe cómo reproducir el problema: versión de Nexo, de Android y enlace de ejemplo.

## Peticiones de funciones

Nexo no agrega funciones que yt-dlp no soporte. La mayoría de los casos avanzados se resuelven con **comandos personalizados** (Ajustes → Comando personalizado).

## Pull requests

1. Comenta en el issue relacionado (o abre uno) contando qué vas a cambiar, así nadie duplica el trabajo.
2. Crea una rama desde `main`.
3. Formatea con `./gradlew ktfmtFormat` y comprueba que compila con `./gradlew assembleGenericDebug`.
4. Si cambias la interfaz, agrega capturas de antes y después.
5. Si tu cambio modifica código que venía de Seal, regístralo en `NOTICE.md` (lo pide la GPLv3, sección 5a).

## Licencia de tus aportes

Al enviar un pull request aceptas que tu aporte se publique bajo la **GNU General Public License v3.0**, la misma licencia del proyecto.

## Compilar

Ver la guía paso a paso en el [README](README.md#compilar-desde-el-código-fuente).
