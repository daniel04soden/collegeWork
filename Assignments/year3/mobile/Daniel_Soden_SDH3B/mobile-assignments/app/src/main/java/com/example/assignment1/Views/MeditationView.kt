package com.example.assignment1.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.assignment1.Models.MeditationVideo
import com.example.assignment1.ViewModels.MeditationViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

private fun extractVideoId(youtubeUrl: String): String? {
    return when {
        "youtu.be" in youtubeUrl -> {
            youtubeUrl.substringAfterLast('/').substringBefore('?')
        }
        "youtube.com/watch" in youtubeUrl -> {
            youtubeUrl.substringAfter("v=").substringBefore('&')
        }
        else -> null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeditationScreen(navController: NavController, meditationViewModel: MeditationViewModel) {
    val uiState by meditationViewModel.meditationUiState.collectAsState()
    val randomVideo by meditationViewModel.randomMeditationVideo.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meditation") }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { Spacer(modifier = Modifier.height(8.dp)) }

                randomVideo?.let { video ->
                    item {
                        Text(
                            text = "Video of the Session",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        MeditationCard(
                            meditation = video,
                            onMeditationComplete = {
                                meditationViewModel.onMeditationComplete()
                            }
                        )
                    }
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                if (uiState.meditations.any { it.id != randomVideo?.id }) {
                    item {
                        Text(
                            text = "All Meditations",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }

                items(uiState.meditations.filter { it.id != randomVideo?.id }) { meditation ->
                    MeditationCard(
                        meditation = meditation,
                        onMeditationComplete = {
                            meditationViewModel.onMeditationComplete()
                        }
                    )
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }
            }
        }
    )
}

@Composable
fun MeditationCard(
    meditation: MeditationVideo,
    onMeditationComplete: () -> Unit
) {
    val videoId = extractVideoId(meditation.youtubeUrl)

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            if (videoId != null) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                        .clip(RoundedCornerShape(
                            topStart = 8.dp, topEnd = 8.dp
                        )),
                    factory = { context ->
                        YouTubePlayerView(context).apply {
                            addYouTubePlayerListener(object :
                                AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.cueVideo(videoId, 0f)
                                }
                                override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                                    if (state == PlayerConstants.PlayerState.ENDED) {
                                        onMeditationComplete() // Once video completed, increase streak
                                    }
                                }
                            })
                        }
                    }
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = meditation.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = meditation.description)
            }
        }
    }
}
