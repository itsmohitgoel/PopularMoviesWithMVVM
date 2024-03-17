package com.mogo.presentation.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.mogo.presentation.R

@Composable
fun ErrorComponent(errorMessage: String?) {
    Box(modifier = Modifier.fillMaxSize(), Alignment.Center){
        Text(text = errorMessage?: stringResource(id = R.string.generic_error_message),
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
            )
    }

}