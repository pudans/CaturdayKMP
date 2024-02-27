package app.caturday

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import app.caturday.model.Video
import app.caturday.model.VideosResult
import app.caturday.repository.FeedRepository
import app.caturday.repository.LikeRepository
import app.caturday.state.FeedItemState
import app.caturday.state.FeedScreenState
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class FeedViewModel(
//	private val mFirebaseAuth: FirebaseAuth,
	private val mFeedRepository: FeedRepository,
	private val mFeedLikeRepository: LikeRepository
) : ViewModel() {

	private val mFeedScreenState = mutableStateOf<FeedScreenState>(FeedScreenState.Loading)

	init {
		viewModelScope.launch {
			mFeedRepository.getFeed()
				.collect { result ->
					mFeedScreenState.value = when (result) {
						is VideosResult.Data -> {
							FeedScreenState.Data(result.data.mapIndexed { index, video ->
								generateFeedItemState(video, index, result.data)
							})
						}
						VideosResult.Error -> FeedScreenState.Empty
						VideosResult.Loading -> FeedScreenState.Loading
					}
				}
		}
	}

	private fun generateFeedItemState(video: Video, index: Int, list: List<Video>) = FeedItemState(
		videoId = video.id ?: "",
		videoUrl = video.url ?: "",
		videoPreviewUrl = video.preview?.url ?: "",
		uploaderAvatarUrl = video.uploader?.photoUrl ?: "",
		uploaderNick = "@${video.uploader?.email?.split('@')?.first()}",
		likedNicks = "Likes: ${video.likedEmails?.joinToString(", ") { "@${it.split('@').first()}" }}",
		likesCount = video.likedEmails?.size ?: 0,
		isLikedByUser = false,//video.likedEmails?.contains(mFirebaseAuth.currentUser?.email) ?: false,
		numberOfVideos = "${index + 1}/${list.size}",
	)

	fun observeFeedScreenState(): State<FeedScreenState> = mFeedScreenState

	fun likeOrDislike(videoId: String) {
		viewModelScope.launch {
			mFeedLikeRepository.likeOrDislike(videoId).collect {}
		}
	}
}