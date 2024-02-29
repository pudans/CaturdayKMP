package app.caturday.ui

import android.view.TextureView
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(UnstableApi::class)
@Composable
actual fun VideoPlayer(
    videoUrl: String,
    isPlayWhenReady: Boolean,
    playPauseState: Boolean,
    previewImageState: MutableState<Boolean>,
    progressState: MutableState<Float>,
    modifier: Modifier
) {
    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    LaunchedEffect(videoUrl) {
        exoPlayer.setMediaItem(MediaItem.fromUri(videoUrl))
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_DEFAULT
    exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
    exoPlayer.emitProgressTo(progressState)

    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onRenderedFirstFrame() {
                previewImageState.value = false
            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    LaunchedEffect(isPlayWhenReady, playPauseState) {
        exoPlayer.playWhenReady = isPlayWhenReady && playPauseState
    }

    AndroidView(
        factory = {
            TextureView(it).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                exoPlayer.setVideoTextureView(this)
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun ExoPlayer.emitProgressTo(state: MutableState<Float>) {
    var isPlaying by remember { mutableStateOf(false) }
    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(value: Boolean) {
                isPlaying = value
            }
        }
        addListener(listener)
        onDispose {
            removeListener(listener)
        }
    }

    if (isPlaying) {
        LaunchedEffect(Unit) {
            while (true) {
                state.value = currentPosition.toFloat() / duration
                delay(1.seconds / 30)
            }
        }
    }
}