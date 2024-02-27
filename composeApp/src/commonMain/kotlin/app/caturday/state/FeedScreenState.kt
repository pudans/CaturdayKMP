package app.caturday.state

sealed interface FeedScreenState {
	object Loading : FeedScreenState
	object Empty : FeedScreenState
	data class Data(val items: List<FeedItemState>) : FeedScreenState
}
