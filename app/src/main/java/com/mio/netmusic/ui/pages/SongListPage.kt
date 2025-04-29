package com.mio.netmusic.ui.pages

import LogUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mio.netmusic.bean.SongResponse
import com.mio.netmusic.ui.components.MioHeader
import com.mio.netmusic.ui.components.MioText
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.App.toJumpPlayList
import com.mio.netmusic.utlils.KtorHelper
import com.mio.netmusic.utlils.isOk

@Composable
fun SongListPage(
    navController: NavHostController,
) {
    val songId = toJumpPlayList?.id.toString()

    var songList by remember { mutableStateOf(listOf<SongResponse.Song>()) }

    LaunchedEffect(Unit) {
        LogUtils.d("songId: $songId")
        KtorHelper.getSongs(songId).collect {
            if (it.code?.isOk() == true) {
                songList = it.songs
            } else {
                App.toast("获取歌单失败")
            }
        }
    }

    LazyColumn {
        item {
            MioHeader(
                onBack = {
                    navController.popBackStack()
                },
                onSearch = {},
                onMore = {}
            )

            SongHeader(
                title = toJumpPlayList?.name.toString(),
                titleImageUrl = toJumpPlayList?.coverImgUrl ?: "",
                creatorName = toJumpPlayList?.creator?.nickname ?: "",
                creatorAvatarUrl = toJumpPlayList?.creator?.avatarUrl ?: "",
                playCount = (toJumpPlayList?.playCount ?: 0).toInt(),
                desc = toJumpPlayList?.description.toString()
            )
        }

        items(songList.size) {
            SongItem(songList[it])
        }
    }


}

@Composable
private fun SongHeader(
    title: String,
    titleImageUrl: String,
    creatorName: String,
    creatorAvatarUrl: String,
    playCount: Int,
    desc: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = titleImageUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            MioText(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape),
                    model = creatorAvatarUrl,
                    contentDescription = null
                )
                MioText(
                    text = " " + creatorName + " ".repeat(2) + "${playCount}次播放",
                    style = MaterialTheme.typography.titleSmall,
                    level = 2,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            MioText(
                text = desc.replace("\\n", " "),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                level = 2,

                )
        }

    }
}

@Composable
fun SongItem(song: SongResponse.Song) {
    Column(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier.size(56.dp),
                    model = song.al?.picUrl,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    MioText(
                        text = song.name.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    val subText =
                        song.ar?.joinToString(separator = "/") { it?.name.toString() } + " - " + song.al?.name

                    MioText(
                        text = subText,
                        style = MaterialTheme.typography.bodySmall,
                        level = 2,
                    )

                }
            }
        }
    }
}
