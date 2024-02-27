@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package app.caturday.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.caturday.FeedViewModel
import app.caturday.state.FeedItemState
import app.caturday.state.FeedScreenState
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun FeedScreen() {

    val viewModel = koinViewModel(vmClass = FeedViewModel::class)

    Box(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    ) {
        val state by viewModel.observeFeedScreenState()
        when (state) {
            is FeedScreenState.Data -> FeedPager(items = (state as FeedScreenState.Data).items) {
                viewModel.likeOrDislike(it)
            }
            FeedScreenState.Empty -> EmptyPager()
            FeedScreenState.Loading -> Loading()
        }
    }
}

@Composable
private fun FeedPager(
    items: List<FeedItemState>,
    onLikeClick: (String) -> Unit
) {
    val pagerState = rememberPagerState() {
        items.size
    }

    VerticalPager(
        state = pagerState
    ) { page ->
        PagerItem(
            itemState = items[page],
            isSelected = page == pagerState.currentPage,
            onLikeClick = onLikeClick
        )
    }
}

@Composable
private fun PagerItem(
    itemState: FeedItemState,
    isSelected: Boolean,
    onLikeClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(16.dp)),
    ) {

        FeedVideoPlayer(
            videoUrl = itemState.videoUrl,
            previewUrl = itemState.videoPreviewUrl,
            isPlayWhenReady = isSelected
        )

        RightControlsBlock(itemState, onLikeClick)

        BottomBlock(itemState)

        RightTopBlock(itemState)
    }
}

@Composable
private fun EmptyPager() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "EMPTY",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            color = Color.White
        )
    }
}

@Composable
private fun RightControlsBlock(
    itemState: FeedItemState,
    onLikeClick: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .width(80.dp)
                .padding(16.dp)
                .align(Alignment.CenterEnd)
        ) {

            UploaderAvatar(itemState.uploaderAvatarUrl)

            Spacer(modifier = Modifier.height(16.dp))

            LikeIcon(isLiked = itemState.isLikedByUser) {
                onLikeClick.invoke(itemState.videoId)
            }

            Spacer(modifier = Modifier.height(4.dp))

            LikesCount(itemState.likesCount)
        }
    }
}

@Composable
private fun UploaderAvatar(avatarUrl: String) {
//    Image(
//        painter = rememberCoilPainter(
//            request = avatarUrl,
//            fadeIn = true
//        ),
//        contentDescription = "",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .size(48.dp)
//            .clip(CircleShape)
//            .border(2.dp, Color.White, CircleShape)
//    )
}

@Composable
private fun LikeIcon(
    isLiked: Boolean,
    onClick: () -> Unit
) {
    Icon(
        imageVector = Icons.Filled.Favorite,
        tint = if (isLiked) Color.Red else Color.White,
        contentDescription = "",
        modifier = Modifier
            .size(48.dp)
            .clickable(onClick = onClick)
    )
}

@Composable
private fun LikesCount(likesCount: Int) {
    Text(
        text = likesCount.toString(),
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun BottomBlock(itemState: FeedItemState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {

            Text(
                text = itemState.uploaderNick,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = itemState.likedNicks,
                color = Color.White
            )
        }
    }
}

@Composable
private fun RightTopBlock(itemState: FeedItemState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = itemState.numberOfVideos,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(8.dp))
                .padding(8.dp)
                .background(Color.Black)
        )
    }
}


@Composable
fun FeedVideoPlayer(
    videoUrl: String,
    previewUrl: String,
    isPlayWhenReady: Boolean
) {
//    val context = LocalContext.current

//	val coroutineScope = rememberCoroutineScope()

    var playPauseState by remember { mutableStateOf(true) }

    var previewImageState by remember { mutableStateOf(true) }

    var progressState by remember { mutableStateOf(0.0f) }

    val exoPlayer = remember {


//
//		val cacheDataSourceFactory = CacheDataSourceFactory(CacheUtils.getCache(context), DefaultHttpDataSourceFactory("Catuday"))
//
//        val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(context)

//        SimpleExoPlayer.Builder(context)
//            .setMediaSourceFactory(mediaSourceFactory)
//            .build().apply {
//                playWhenReady = false
//                videoScalingMode = C.VIDEO_SCALING_MODE_DEFAULT
//                repeatMode = Player.REPEAT_MODE_ALL
//
//                addListener(object : Player.Listener {
//                    override fun onRenderedFirstFrame() {
//                        previewImageState = false
//                    }
//                })
//            }
    }

    LaunchedEffect(videoUrl) {
//        exoPlayer.setMediaItem(MediaItem.fromUri(videoUrl))
//        exoPlayer.prepare()

//		coroutineScope.launch {
//			while (exoPlayer.playWhenReady) {
//				val duration = exoPlayer.contentDuration
//				val position = exoPlayer.contentPosition
//
//				Log.d("qwerttttt", "$position $duration")
//
//				delay(100L)
//			}
//		}
    }

//    LaunchedEffect(isPlayWhenReady, playPauseState) {
//        exoPlayer.playWhenReady = isPlayWhenReady && playPauseState
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { playPauseState = !playPauseState }
    ) {
//        DisposableEffect(
//            TextureView(exoPlayer)
//        ) {
//            onDispose { exoPlayer.release() }
//        }

        PreviewImage(previewImageState, previewUrl)

        ProgressBar(progressState)

        PlayPauseIcon(playPauseState)
    }
}

//@Composable
//private fun TextureView(exoPlayer: SimpleExoPlayer) {
//    AndroidView(
//        factory = {
//            TextureView(it).apply {
//                layoutParams = FrameLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                )
//                exoPlayer.setVideoTextureView(this)
//            }
//        })
//}
//
@Composable
private fun ProgressBar(progressState: Float) {
    LinearProgressIndicator(
        progress = progressState,
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(2.dp)),
        backgroundColor = Color.Transparent,
        color = Color.Red,
    )
}

@Composable
private fun PreviewImage(
    previewImageState: Boolean,
    previewUrl: String
) {
    AnimatedVisibility(
        visible = previewImageState,
        modifier = Modifier.fillMaxSize(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
//        Image(
//            contentScale = ContentScale.Crop,
//            painter = rememberCoilPainter(request = previewUrl),
//            contentDescription = ""
//        )
    }
}

@Composable
private fun PlayPauseIcon(playPauseState: Boolean) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = !playPauseState,
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.Center),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}