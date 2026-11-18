@file:OptIn(ExperimentalTextApi::class, ExperimentalTextApi::class, ExperimentalTextApi::class)

package com.junkfood.seal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextDirection
import com.junkfood.seal.R

/**
 * Nexo: tipografía de marca. Outfit (SIL Open Font License 1.1, ver assets/licenses/OFL-Outfit.txt)
 * para títulos y encabezados; el cuerpo de texto sigue con la fuente del sistema para máxima
 * legibilidad. Los caracteres que Outfit no cubre (cirílico, CJK, árabe…) usan la fuente del
 * sistema automáticamente.
 */
val NexoBrandFontFamily =
    FontFamily(
        Font(R.font.outfit_regular, FontWeight.Normal),
        Font(R.font.outfit_medium, FontWeight.Medium),
        Font(R.font.outfit_semibold, FontWeight.SemiBold),
        Font(R.font.outfit_bold, FontWeight.Bold),
    )

val Typography =
    Typography().run {
        copy(
            bodyLarge = bodyLarge.applyLinebreak().applyTextDirection(),
            bodyMedium = bodyMedium.applyLinebreak().applyTextDirection(),
            bodySmall = bodySmall.applyLinebreak().applyTextDirection(),
            // Nexo: títulos enfatizados con la fuente de marca (Material 3 Expressive).
            titleLarge = titleLarge.brand(FontWeight.SemiBold).applyTextDirection(),
            titleMedium = titleMedium.brand(FontWeight.Medium).applyTextDirection(),
            titleSmall = titleSmall.applyTextDirection(),
            headlineSmall = headlineSmall.brand(FontWeight.SemiBold).applyTextDirection(),
            headlineMedium = headlineMedium.brand(FontWeight.SemiBold).applyTextDirection(),
            headlineLarge = headlineLarge.brand(FontWeight.SemiBold).applyTextDirection(),
            displaySmall = displaySmall.brand(FontWeight.Medium).applyTextDirection(),
            displayMedium = displayMedium.brand(FontWeight.Medium).applyTextDirection(),
            displayLarge = displayLarge.brand(FontWeight.Medium).applyTextDirection(),
            labelLarge = labelLarge.applyTextDirection(),
            labelMedium = labelMedium.applyTextDirection(),
            labelSmall = labelSmall.applyTextDirection(),
        )
    }

private fun TextStyle.brand(weight: FontWeight): TextStyle =
    this.copy(fontFamily = NexoBrandFontFamily, fontWeight = weight)

private fun TextStyle.applyLinebreak(): TextStyle = this.copy(lineBreak = LineBreak.Paragraph)

private fun TextStyle.applyTextDirection(): TextStyle =
    this.copy(textDirection = TextDirection.Content)
