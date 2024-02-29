package app.caturday.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayer(
    videoUrl: String,
    isPlayWhenReady: Boolean,
    playPauseState: Boolean,
    previewImageState: MutableState<Boolean>,
    progressState: MutableState<Float>,
    modifier: Modifier
)