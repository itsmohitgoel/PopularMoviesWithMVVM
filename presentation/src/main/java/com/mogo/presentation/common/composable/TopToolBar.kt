package com.mogo.presentation.common.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


@Composable
fun TopToolBar(
    toolbarParams: Pair<Boolean, String?>,
    navController: NavController
) {
    if (toolbarParams.first) {
        TopAppBar(
            title = { Text(text = toolbarParams.second ?: "Detail Screen",
                color = Color.White) },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, "",
                        tint = Color.White)
                }
            },
            backgroundColor = Color.Black
        )
    } else {
        TopAppBar(
            title = { Text(text = toolbarParams.second ?: "Movie App", color = Color.White) },
            backgroundColor = Color.Black
        )
    }
}

