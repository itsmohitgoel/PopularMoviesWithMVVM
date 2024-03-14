package com.mogo.moviescatalogue.common.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@Composable
fun TopToolBar(
    toolbarParams: Pair<Boolean, String?>,
    navController: NavController
) {
    if (toolbarParams.first) {
        TopAppBar(
            title = { Text(text = toolbarParams?.second ?: "Detail Screen") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(Icons.Rounded.ArrowBack, "")
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
    } else {
        TopAppBar(
            title = { Text(text = toolbarParams?.second ?: "Movie App") },
            backgroundColor = MaterialTheme.colors.secondary
        )
    }
}

