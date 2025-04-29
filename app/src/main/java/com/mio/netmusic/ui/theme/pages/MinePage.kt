package com.mio.netmusic.ui.theme.pages

import LogUtils
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.NetmusicTheme
import com.mio.netmusic.ui.theme.Settings


@Composable
fun MinePage(
    outNavController: NavHostController? = null,
    outNavController1: NavHostController? = null,
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

            Spacer(modifier = Modifier.height(20.dp))

            UserCard(
                userId = profile.userId,
                nickname = profile.nickname,
                avatarUrl = profile.avatarUrl,
            )
//            AsyncImage(
//                modifier = Modifier
//                    .size(100.dp),
//                model = profile.backgroundUrl,
//                contentDescription = null
//            )

        }

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = { Settings.isLightMode.value = !Settings.isLightMode.value }) {
            Icon(imageVector = Icons.Rounded.Build, contentDescription = null)
        }
    }
}

@Composable
@Preview
fun UserCardPreview() {
    NetmusicTheme {
        UserCard(
            userId = 123456789,
            nickname = "Mio",
            avatarUrl = "https://p1.music.126.net/UTLunzJE8ACvemvk9W3Tvg==/18548761162673417.jpg",
        )
    }
}

@Composable
fun UserCard(userId: Long?, nickname: String?, avatarUrl: String?) {
    Surface(
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                model = avatarUrl,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = nickname ?: "",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "UID:${userId?.toString()}",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
