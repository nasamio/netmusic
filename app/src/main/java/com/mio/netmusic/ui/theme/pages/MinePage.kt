package com.mio.netmusic.ui.theme.pages

import LogUtils
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.Settings


@Composable
fun MinePage(
    outNavController: NavHostController,
    outNavController1: NavHostController,
) {
    val account = App.account.value ?: return
    val profile = App.profile.value ?: return

    val url = profile.avatarUrl
    LogUtils.d("url:$url")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape),
                    model = profile.avatarUrl,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = profile.nickname.toString())
                    Text(text = account.userName.toString())
                }
            }
            AsyncImage(
                modifier = Modifier
                    .size(100.dp),
                model = profile.backgroundUrl,
                contentDescription = null
            )

        }

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = { Settings.isLightMode.value = !Settings.isLightMode.value }) {
            Icon(imageVector = Icons.Rounded.Build, contentDescription = null)
        }
    }
}