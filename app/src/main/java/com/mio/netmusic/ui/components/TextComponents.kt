package com.mio.netmusic.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MioText(
    text: String,
    style: TextStyle = TextStyle.Default,
    level: Int = 1, // 1.primary color 2.secondary color 3.tertiary color
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text,
        style = style,
        color = when (level) {
            1 -> MaterialTheme.colorScheme.primary
            2 -> MaterialTheme.colorScheme.secondary
            3 -> MaterialTheme.colorScheme.tertiary
            else -> MaterialTheme.colorScheme.primary
        },
        maxLines = maxLines,
        overflow = overflow
    )
}