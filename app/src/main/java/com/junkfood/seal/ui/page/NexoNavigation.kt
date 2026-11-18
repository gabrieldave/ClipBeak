@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.junkfood.seal.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material.icons.outlined.Terminal
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.junkfood.seal.R
import com.junkfood.seal.ui.common.Route

/** Nexo: destinos principales. Sustituyen al menú lateral de Seal. */
enum class TopDestination(
    val route: String,
    val selectedRoute: String,
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val icon: ImageVector,
) {
    Home(Route.HOME, Route.HOME, R.string.nav_home, Icons.Filled.Home, Icons.Outlined.Home),
    History(
        Route.DOWNLOADS,
        Route.DOWNLOADS,
        R.string.downloads_history,
        Icons.Filled.Subscriptions,
        Icons.Outlined.Subscriptions,
    ),
    Commands(
        Route.TASK_LIST,
        Route.TASK_LIST,
        R.string.nav_commands,
        Icons.Filled.Terminal,
        Icons.Outlined.Terminal,
    ),
    Settings(
        Route.SETTINGS,
        Route.SETTINGS_PAGE,
        R.string.settings,
        Icons.Filled.Settings,
        Icons.Outlined.Settings,
    ),
}

/** Barra inferior para teléfonos (Material 3 Expressive). */
@Composable
fun NexoBottomBar(
    modifier: Modifier = Modifier,
    currentTopDestination: String?,
    onNavigate: (String) -> Unit,
) {
    ShortNavigationBar(modifier = modifier) {
        TopDestination.entries.forEach { dest ->
            val selected = currentTopDestination == dest.selectedRoute
            ShortNavigationBarItem(
                selected = selected,
                onClick = { onNavigate(dest.route) },
                icon = {
                    Icon(if (selected) dest.selectedIcon else dest.icon, contentDescription = null)
                },
                label = { Text(stringResource(dest.labelRes)) },
            )
        }
    }
}

/** Riel lateral para plegables abiertos, tablets y teléfonos en horizontal. */
@Composable
fun NexoNavigationRail(
    modifier: Modifier = Modifier,
    currentTopDestination: String?,
    onNavigate: (String) -> Unit,
) {
    WideNavigationRail(
        modifier = modifier,
        header = {
            // En pantallas bajas (teléfono en horizontal) se omite el logo para que quepan los
            // destinos.
            if (LocalConfiguration.current.screenHeightDp >= 480)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary) {
                        Icon(
                            painter = painterResource(R.drawable.ic_launcher_monochrome),
                            contentDescription = stringResource(R.string.app_name),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(44.dp),
                        )
                    }
                    Spacer(Modifier.height(24.dp))
                }
        },
    ) {
        TopDestination.entries.forEach { dest ->
            val selected = currentTopDestination == dest.selectedRoute
            WideNavigationRailItem(
                selected = selected,
                onClick = { onNavigate(dest.route) },
                icon = {
                    Icon(if (selected) dest.selectedIcon else dest.icon, contentDescription = null)
                },
                label = { Text(stringResource(dest.labelRes)) },
                modifier = Modifier.padding(vertical = 2.dp),
            )
        }
    }
}
