package app.caturday.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import app.caturday.model.VideosResult

class ProfileLikedRepository(
//	firebaseDatabase: FirebaseDatabase,
//	firebaseAuth: FirebaseAuth
) {

	private val mChannelFlow = MutableStateFlow<VideosResult>(VideosResult.Loading)

	init {

//		firebaseDatabase.reference.addValueEventListener(object : ValueEventListener {
//
//			override fun onDataChange(snapshot: DataSnapshot) {
//				val result = snapshot
//					.children
//					.mapNotNull { it.getValue<Video>() }
//					.filter { it.likedEmails?.contains(firebaseAuth.currentUser?.email) ?: false }
//				mChannelFlow.value = VideosResult.Data(result)
//
//			}
//
//			override fun onCancelled(error: DatabaseError) {
//				mChannelFlow.value = VideosResult.Error
//			}
//
//		})
	}

	fun getLikedVideos(): Flow<VideosResult> = mChannelFlow
}