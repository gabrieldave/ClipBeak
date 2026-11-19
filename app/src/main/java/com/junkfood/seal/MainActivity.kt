package com.junkfood.seal

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import com.junkfood.seal.App.Companion.context
import com.junkfood.seal.ui.common.LocalDarkTheme
import com.junkfood.seal.ui.common.LocalFoldState
import com.junkfood.seal.ui.common.SettingsProvider
import com.junkfood.seal.ui.common.rememberFoldState
import com.junkfood.seal.ui.page.AppEntry
import com.junkfood.seal.ui.page.downloadv2.configure.DownloadDialogViewModel
import com.junkfood.seal.ui.theme.SealTheme
import com.junkfood.seal.util.matchUrlFromSharedText
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext

class MainActivity : AppCompatActivity() {
    private val dialogViewModel: DownloadDialogViewModel by viewModel()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // ClipBeak: en Android 13+ pedimos las notificaciones al abrir la app (si falta el permiso)
        // para que la descarga avise y siga en segundo plano aunque se cierre la interfaz.
        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                REQUEST_NOTIFICATIONS,
            )
        }

        context = this.baseContext
        setContent {
            KoinContext {
                val windowSizeClass = calculateWindowSizeClass(this)
                // Nexo: la bisagra de los plegables llega a toda la interfaz.
                val foldState by rememberFoldState(this)
                SettingsProvider(windowWidthSizeClass = windowSizeClass.widthSizeClass) {
                    CompositionLocalProvider(LocalFoldState provides foldState) {
                        SealTheme(
                            darkTheme = LocalDarkTheme.current.isDarkTheme(),
                            isHighContrastModeEnabled =
                                LocalDarkTheme.current.isHighContrastModeEnabled,
                        ) {
                            AppEntry(dialogViewModel = dialogViewModel)
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val url = intent.getSharedURL()
        if (url != null) {
            dialogViewModel.postAction(DownloadDialogViewModel.Action.ShowSheet(listOf(url)))
        }
    }

    private fun Intent.getSharedURL(): String? {
        val intent = this

        return when (intent.action) {
            Intent.ACTION_VIEW -> {
                intent.dataString
            }

            Intent.ACTION_SEND -> {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let { sharedContent ->
                    intent.removeExtra(Intent.EXTRA_TEXT)
                    matchUrlFromSharedText(sharedContent).also { matchedUrl ->
                        if (sharedUrlCached != matchedUrl) {
                            sharedUrlCached = matchedUrl
                        }
                    }
                }
            }

            else -> {
                null
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val REQUEST_NOTIFICATIONS = 1001
        private var sharedUrlCached = ""
    }
}
