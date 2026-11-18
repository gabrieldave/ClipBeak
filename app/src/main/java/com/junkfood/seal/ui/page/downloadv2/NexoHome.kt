@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.junkfood.seal.ui.page.downloadv2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ContentPaste
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.junkfood.seal.R
import com.junkfood.seal.ui.common.FoldState
import com.junkfood.seal.ui.common.LocalFoldState
import com.junkfood.seal.util.findURLsFromString

/** Ancho a partir del cual el inicio muestra dos paneles (plegable abierto, tablet). */
private val TwoPaneMinWidth = 720.dp // ancho útil, ya descontado el riel lateral

/** Teléfono en horizontal: ancho mediano pero muy poca altura. */
private val LandscapeMinWidth = 600.dp
private val LandscapeMaxHeight = 480.dp

private val HeroPaneMaxWidth = 420.dp
private val PaneMinWidth = 280.dp

/** Cómo se distribuye el inicio según la ventana y la bisagra. */
sealed interface HomeLayout {
    /** Teléfono en vertical: el campo de enlace va dentro de la lista de descargas. */
    data object Single : HomeLayout

    /**
     * Pantalla ancha o plegable en postura libro: enlace a la izquierda, descargas a la derecha.
     */
    data class TwoPane(val heroWidth: Dp, val gap: Dp) : HomeLayout

    /** Plegable a medio abrir en postura mesa: descargas arriba, enlace abajo junto a las manos. */
    data class Tabletop(val topHeight: Dp, val gap: Dp) : HomeLayout
}

/**
 * Decide la distribución. Es una función pura para poder probarla sin dispositivo: recibe el tamaño
 * disponible, la bisagra y la posición del contenedor en la ventana.
 */
fun computeHomeLayout(
    maxWidth: Dp,
    maxHeight: Dp,
    fold: FoldState?,
    originInWindow: Offset,
    density: Float,
): HomeLayout {
    fun Float.pxToDp(): Dp = (this / density).dp

    if (fold != null && fold.isTabletopPosture) {
        val top = (fold.bounds.top - originInWindow.y).pxToDp()
        if (top > 120.dp && top < maxHeight - 120.dp) {
            return HomeLayout.Tabletop(topHeight = top, gap = fold.bounds.height.pxToDp())
        }
    }
    if (fold != null && fold.isBookPosture) {
        val left = (fold.bounds.left - originInWindow.x).pxToDp()
        if (left >= PaneMinWidth && left <= maxWidth - PaneMinWidth) {
            return HomeLayout.TwoPane(heroWidth = left, gap = fold.bounds.width.pxToDp())
        }
    }
    val wide = maxWidth >= TwoPaneMinWidth
    val landscapePhone = maxWidth >= LandscapeMinWidth && maxHeight < LandscapeMaxHeight
    if (wide || landscapePhone) {
        val hero = (maxWidth * 0.4f).coerceIn(PaneMinWidth, HeroPaneMaxWidth)
        return HomeLayout.TwoPane(heroWidth = hero, gap = 0.dp)
    }
    return HomeLayout.Single
}

/**
 * Nexo: contenedor adaptable del inicio. Mide el espacio, consulta la bisagra y coloca el campo de
 * enlace ([hero]) y la lista de descargas ([queue]) sin que el contenido quede partido por el
 * pliegue.
 */
@Composable
fun AdaptiveHomeLayout(
    modifier: Modifier = Modifier,
    single: @Composable () -> Unit,
    hero: @Composable (Modifier) -> Unit,
    queue: @Composable (Modifier) -> Unit,
    onLayoutChange: (HomeLayout) -> Unit = {},
) {
    var origin by remember { mutableStateOf(Offset.Zero) }
    val fold = LocalFoldState.current
    val density = LocalDensity.current.density
    BoxWithConstraints(
        modifier = modifier.fillMaxSize().onGloballyPositioned { origin = it.positionInWindow() }
    ) {
        val layout = computeHomeLayout(maxWidth, maxHeight, fold, origin, density)
        SideEffect { onLayoutChange(layout) }
        when (layout) {
            HomeLayout.Single -> single()
            is HomeLayout.TwoPane ->
                Row(Modifier.fillMaxSize()) {
                    hero(Modifier.width(layout.heroWidth).fillMaxHeight())
                    Spacer(Modifier.width(layout.gap))
                    queue(Modifier.weight(1f).fillMaxHeight())
                }
            is HomeLayout.Tabletop ->
                Column(Modifier.fillMaxSize()) {
                    queue(Modifier.height(layout.topHeight).fillMaxWidth())
                    Spacer(Modifier.height(layout.gap))
                    hero(Modifier.weight(1f).fillMaxWidth())
                }
        }
    }
}

/** Nexo: barra superior con la marca. Sustituye al botón de menú lateral de Seal. */
@Composable
fun NexoTopBar(modifier: Modifier = Modifier, trailing: @Composable () -> Unit = {}) {
    Row(
        modifier = modifier.fillMaxWidth().height(64.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary) {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_monochrome),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(40.dp),
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = stringResource(R.string.brand_name),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.weight(1f))
        trailing()
    }
}

/**
 * Nexo: tarjeta principal para pegar un enlace y descargar.
 *
 * @param onDownload recibe los enlaces encontrados en el texto, o `null` si el campo está vacío (en
 *   ese caso se abre el flujo normal para escribir el enlace).
 */
@Composable
fun PasteLinkCard(modifier: Modifier = Modifier, onDownload: (List<String>?) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
    var showError by rememberSaveable { mutableStateOf(false) }
    val clipboard = LocalClipboardManager.current

    fun submit() {
        if (text.isBlank()) {
            onDownload(null)
            return
        }
        val urls = findURLsFromString(text)
        if (urls.isEmpty()) {
            showError = true
        } else {
            showError = false
            onDownload(urls)
            text = ""
        }
    }

    Surface(
        modifier = modifier.widthIn(max = 640.dp),
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = stringResource(R.string.paste_link_title),
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.paste_link_desc),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
            )
            Spacer(Modifier.height(16.dp))
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    showError = false
                },
                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                placeholder = { Text(stringResource(R.string.paste_link_hint)) },
                leadingIcon = { Icon(Icons.Outlined.Link, contentDescription = null) },
                trailingIcon = {
                    if (text.isEmpty()) {
                        IconButton(
                            onClick = {
                                clipboard.getText()?.text?.let {
                                    text = it
                                    showError = false
                                }
                            }
                        ) {
                            Icon(
                                Icons.Outlined.ContentPaste,
                                contentDescription = stringResource(R.string.paste),
                            )
                        }
                    } else {
                        IconButton(onClick = { text = "" }) {
                            Icon(
                                Icons.Outlined.Close,
                                contentDescription = stringResource(R.string.clear),
                            )
                        }
                    }
                },
                singleLine = true,
                isError = showError,
                supportingText =
                    if (showError) {
                        { Text(stringResource(R.string.no_link_found)) }
                    } else null,
                shape = CircleShape,
                colors =
                    TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                        errorContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                        focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                        unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                        errorIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    ),
                keyboardOptions =
                    KeyboardOptions(keyboardType = KeyboardType.Uri, imeAction = ImeAction.Go),
                keyboardActions = KeyboardActions(onGo = { submit() }),
            )
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { submit() },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                contentPadding = ButtonDefaults.ContentPadding,
            ) {
                Icon(Icons.Outlined.FileDownload, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    stringResource(R.string.download),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

/** Panel izquierdo (o inferior en postura mesa) con la marca y la tarjeta de enlace. */
@Composable
fun HeroPane(modifier: Modifier = Modifier, onDownload: (List<String>?) -> Unit) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {
        NexoTopBar()
        Spacer(Modifier.height(8.dp))
        PasteLinkCard(modifier = Modifier.fillMaxWidth(), onDownload = onDownload)
        Spacer(Modifier.height(24.dp))
    }
}

/** El botón flotante solo aparece cuando la tarjeta de enlace ya no se ve. */
@Composable
fun AnimatedDownloadFab(visible: Boolean, onClick: () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter =
            androidx.compose.animation.scaleIn(MaterialTheme.motionScheme.fastSpatialSpec()) +
                androidx.compose.animation.fadeIn(),
        exit = androidx.compose.animation.scaleOut() + androidx.compose.animation.fadeOut(),
    ) {
        Box { FABs(downloadCallback = onClick) }
    }
}
