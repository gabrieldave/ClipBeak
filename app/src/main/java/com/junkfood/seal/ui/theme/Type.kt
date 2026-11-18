@file:OptIn(ExperimentalTextApi::class, ExperimentalTextApi::class, ExperimentalTextApi::class)

package com.junkfood.seal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextDirection

val Typography =
    Typography().run {
        copy(
            bodyLarge = bodyLarge.applyLinebreak().applyTextDirection(),
            bodyMedium = bodyMedium.applyLinebreak().applyTextDirection(),
            bodySmall = bodySmall.applyLinebreak().applyTextDirection(),
            // Nexo: títulos enfatizados (Material 3 Expressive).
            titleLarge = titleLarge.copy(fontWeight = FontWeight.SemiBold).applyTextDirection(),
            titleMedium = titleMedium.applyTextDirection(),
            titleSmall = titleSmall.applyTextDirection(),
            headlineSmall =
                headlineSmall.copy(fontWeight = FontWeight.SemiBold).applyTextDirection(),
            headlineMedium =
                headlineMedium.copy(fontWeight = FontWeight.SemiBold).applyTextDirection(),
            headlineLarge =
                headlineLarge.copy(fontWeight = FontWeight.SemiBold).applyTextDirection(),
            displaySmall = displaySmall.copy(fontWeight = FontWeight.Medium).applyTextDirection(),
            displayMedium = displayMedium.applyTextDirection(),
            displayLarge = displayLarge.applyTextDirection(),
            labelLarge = labelLarge.applyTextDirection(),
            labelMedium = labelMedium.applyTextDirection(),
            labelSmall = labelSmall.applyTextDirection(),
        )
    }

private fun TextStyle.applyLinebreak(): TextStyle = this.copy(lineBreak = LineBreak.Paragraph)

private fun TextStyle.applyTextDirection(): TextStyle =
    this.copy(textDirection = TextDirection.Content)
