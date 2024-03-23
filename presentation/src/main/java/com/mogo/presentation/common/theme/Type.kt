package com.mogo.presentation.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mogo.presentation.common.FontSize

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.DEFAULT,
    ),
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = FontSize.HEADER_H1,
        fontWeight = FontWeight.Normal
    )
)
