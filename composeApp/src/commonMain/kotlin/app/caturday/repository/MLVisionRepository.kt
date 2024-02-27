package app.caturday.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MLVisionRepository (
//	private val context: Context,
//	private val mFirebaseStorage: FirebaseStorage,
//	private val mFirebaseDatabase: FirebaseDatabase
) {

//	private val mImageLabeling = ImageLabeling.getClient(
//		ImageLabelerOptions.Builder()
//			.setConfidenceThreshold(0.1f)
//			.build()
//	)

//	val retriever = MediaMetadataRetriever()

//	private val mChannelFlow = MutableStateFlow<List<ImageLabel>>(emptyList())

//	suspend fun work(presentationTimeUs: Long, uri: Uri): Flow<List<ImageLabel>> = flow {
//		val retriever = MediaMetadataRetriever()
//		val bitmap = retriever.getFrameAtTime(presentationTimeUs, MediaMetadataRetriever.OPTION_CLOSEST)
//
//		val image = InputImage.fromBitmap(bitmap!!, 0)
//
//		val task = mImageLabeling.process(image)
//
//		delay(1000L)
//
//		if (task.isSuccessful) {
//			emit(task.result)
//		} else {
//			emit(emptyList<ImageLabel>())
//		}
//
//	}.flowOn(context = Dispatchers.IO)

//	fun setUri(uri: Uri) {
//		retriever.setDataSource(context, uri)
//	}

//	fun work(presentationTimeUs: Long): Flow<List<ImageLabel>> {
//////		Log.d("alskdnasljdbljb1", "$presentationTimeUs $uri")
////
////
//////		Log.d("alskdnasljdbljb2", "$retriever")
////
////		val bitmap = retriever.getFrameAtTime(presentationTimeUs, MediaMetadataRetriever.OPTION_CLOSEST)
////
//////		Log.d("alskdnasljdbljb3", "$retriever")
////
////		val image = InputImage.fromBitmap(bitmap!!, 0)
////
////		mImageLabeling.process(image)
////			.addOnCompleteListener { task ->
//////				Log.d("alskdnasljdbljb4", "addOnCompleteListener")
////				mChannelFlow.value = task.result
////			}
////			.addOnCanceledListener {
//////				Log.d("alskdnasljdbljb4", "addOnCanceledListener")
////
////			}
////			.addOnFailureListener {
//////				Log.d("alskdnasljdbljb4", "addOnFailureListener $it")
////			}
////
//////		Log.d("alskdnasljdbljb4", "${task.isSuccessful}")
//////		Log.d("alskdnasljdbljb4", "${task.exception}")
//////
//////
//////		if (task.isSuccessful) {
//////			mChannelFlow.sendBlocking(task.result)
//////		} else {
//////			mChannelFlow.sendBlocking(emptyList<ImageLabel>())
//////		}
////
////		Log.d("alskdnasljdbljb4", "-------------------")
//
//		return mChannelFlow
//	}
}