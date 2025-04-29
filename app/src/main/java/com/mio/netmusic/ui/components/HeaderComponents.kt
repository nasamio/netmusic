package com.mio.netmusic.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 顶部的 左上角返回、右上角搜索
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MioHeader(
    title: String = "",
    onBack: () -> Unit = {},
    onSearch: () -> Unit = {},
    onMore: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        IconButton(onClick = { onBack() }) {
            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { onSearch() }) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
        }
        IconButton(onClick = { onMore() }) {
            Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = null)

        }
    }
}