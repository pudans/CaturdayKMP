package app.caturday.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class LikeRepository(
//	private val mFirebaseDatabase: FirebaseDatabase,
//	private val mFirebaseAuth: FirebaseAuth
) {

	private val mChannelFlow = MutableSharedFlow<Boolean>()

	fun likeOrDislike(videoId: String): Flow<Boolean> {

//		mFirebaseDatabase.reference.get().addOnSuccessListener { dataSnapshot ->
//			val result = dataSnapshot.children.mapNotNull { it.getValue<Video>() }
//
//			val targetVideo = result.find { it.id == videoId }?.let {
//				val dd: MutableList<String>
//				if (it.likedEmails == null) {
//					dd = ArrayList<String>()
//					dd.add(mFirebaseAuth.currentUser?.email ?: "")
//				} else {
//					dd = it.likedEmails.toMutableList()
//					if (dd.contains(mFirebaseAuth.currentUser?.email)) {
//						dd.remove(mFirebaseAuth.currentUser?.email)
//					} else {
//						dd.add(mFirebaseAuth.currentUser?.email ?: "")
//					}
//				}
//				return@let it.copy(likedEmails = dd)
//			}
//
//			mFirebaseDatabase.reference.child(videoId).setValue(targetVideo)
//		}

		return mChannelFlow
	}
}