package com.junkfood.seal.ui.common

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Rect
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import kotlinx.coroutines.flow.map

/**
 * Nexo: estado de la bisagra de un teléfono plegable, en píxeles relativos a la ventana.
 *
 * [isSeparating] es verdadero cuando la bisagra divide la pantalla en dos áreas (pantallas dobles o
 * plegable a medio abrir). En un plegable completamente abierto y plano suele ser falso: ahí basta
 * con el ancho de ventana para decidir si se usan dos paneles.
 */
@Immutable
data class FoldState(
    val bounds: Rect,
    val isVertical: Boolean,
    val isSeparating: Boolean,
    val isHalfOpened: Boolean,
) {
    /** Postura "libro": bisagra vertical que separa izquierda y derecha. */
    val isBookPosture: Boolean
        get() = isVertical && (isSeparating || isHalfOpened)

    /** Postura "mesa": plegable a medio abrir con la bisagra horizontal (como una laptop). */
    val isTabletopPosture: Boolean
        get() = !isVertical && isHalfOpened
}

/**
 * Bisagra actual, o `null` en teléfonos normales. Se puede sustituir en pruebas y vistas previas.
 */
val LocalFoldState = staticCompositionLocalOf<FoldState?> { null }

@Composable
fun rememberFoldState(activity: Activity): State<FoldState?> {
    val flow =
        remember(activity) {
            WindowInfoTracker.getOrCreate(activity).windowLayoutInfo(activity).map { info ->
                info.displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()?.let {
                    val b = it.bounds
                    FoldState(
                        bounds =
                            Rect(
                                b.left.toFloat(),
                                b.top.toFloat(),
                                b.right.toFloat(),
                                b.bottom.toFloat(),
                            ),
                        isVertical = it.orientation == FoldingFeature.Orientation.VERTICAL,
                        isSeparating = it.isSeparating,
                        isHalfOpened = it.state == FoldingFeature.State.HALF_OPENED,
                    )
                }
            }
        }
    return flow.collectAsState(initial = null)
}
