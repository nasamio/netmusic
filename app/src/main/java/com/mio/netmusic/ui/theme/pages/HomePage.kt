package com.mio.netmusic.ui.theme.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mio.netmusic.bean.Playlist
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.utlils.KtorHelper
import com.mio.netmusic.utlils.isOk

@Composable
fun HomePage(
    innerNavController: NavHostController,
    outNavController: NavHostController,
) {
    var playlist by remember { mutableStateOf(listOf<Playlist>()) }


    LaunchedEffect(Unit) {
        KtorHelper.playlist(App.profile.value!!.userId).collect {
            if (it.code?.isOk() == true) {
                playlist = it.playlist
            } else {
                App.toast("请求歌单发生异常...")
            }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(10.dp)
    ) {
        items(playlist.size) {
            PlayListItem(playlist[it])
        }
    }
}


@Composable
fun PlayListItem(playlist: Playlist) {
    Surface(
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp)),
                model = playlist.coverImgUrl,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = playlist.name.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "歌单·${playlist.trackCount}·${playlist.creator?.nickname.toString()}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))

}
