package com.llyods.retailapp.presentation.feature.common.composable

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.mogo.moviescatalogue.common.FontSize.DEFAULT
import com.mogo.moviescatalogue.common.UiConstants.MAX_LINE_SINGLE

@Composable
fun MovieTextLabel(
    title: String,
    modifier: Modifier = Modifier,
    overFlow: TextOverflow = TextOverflow.Ellipsis,
    fontSize: TextUnit = DEFAULT,
    maxLines: Int = MAX_LINE_SINGLE,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        maxLines = maxLines,
        overflow = overFlow,
        text = title,
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
