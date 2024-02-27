package app.caturday.repository

import app.caturday.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import app.caturday.model.VideosResult

class FeedRepository(
//	firebaseDatabase: FirebaseDatabase
) {

	private val mChannelFlow = MutableStateFlow<VideosResult>(VideosResult.Loading)

	init {
//		firebaseDatabase.reference.addValueEventListener(object : ValueEventListener {
//
//			override fun onDataChange(snapshot: DataSnapshot) {
//				val result = snapshot.children.mapNotNull { it.getValue<Video>() }.sortedBy { -it.uploadTimestamp }
//				mChannelFlow.value = VideosResult.Data(result)
//			}
//
//			override fun onCancelled(error: DatabaseError) {
//				mChannelFlow.value = VideosResult.Error
//			}
//		})
		mChannelFlow.value = VideosResult.Error
	}

	fun getFeed(): Flow<VideosResult> = mChannelFlow
}