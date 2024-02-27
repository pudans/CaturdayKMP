package app.caturday.repository

class UploadFileRepository(
//	private val mFirebaseStorage: FirebaseStorage,
//	private val mFirebaseDatabase: FirebaseDatabase,
//	private val mFirebaseAuth: FirebaseAuth
) {

//	private val retriever = MediaMetadataRetriever()
//	private val mChannelFlow = MutableStateFlow<UploadVideoState>(UploadVideoState.Default)
//
//	fun doWork(uri: Uri): Flow<UploadVideoState> {
//
//		mChannelFlow.value = UploadVideoState.Started
//
//		retriever.setDataSource(mFirebaseDatabase.app.applicationContext, uri)
//		val firstFrame = retriever.getFrameAtIndex(0)
//
//		uploadFile(uri, firstFrame)
//
//		return mChannelFlow
//	}
//
//	private fun uploadFile(file: Uri, firstFrame: Bitmap?) {
//
//		val videoId = UUID.randomUUID().toString()
//
//		val listRef = mFirebaseStorage.reference.child("videos").child(videoId)
//
//		val metadata = StorageMetadata.Builder()
//			.setContentType("videos/mp4")
//			.build()
//
//		val uploadTask = listRef.putFile(file, metadata)
//
//		uploadTask.addOnProgressListener { (bytesTransferred, totalByteCount) ->
//			val progress = bytesTransferred.toFloat() / totalByteCount
//			mChannelFlow.value = UploadVideoState.Loading(progress)
//		}.addOnFailureListener {
//			Log.d("asdfggg", "Upload is failed")
//		}.addOnSuccessListener {
//			listRef.downloadUrl.addOnSuccessListener {
//				uploadPreview(videoId, it, firstFrame)
//			}
//		}
//	}
//
//
//	private fun uploadPreview(videoId: String, videoUri: Uri, firstFrame: Bitmap?) {
//		if (firstFrame != null) {
//			val baos = ByteArrayOutputStream()
//			firstFrame.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//			val data: ByteArray = baos.toByteArray()
//
//			val previewId = UUID.randomUUID().toString()
//			val listRef = mFirebaseStorage.reference.child("previews").child(previewId)
//			val uploadTask = listRef.putBytes(data)
//			uploadTask.addOnSuccessListener { taskSnapshot ->
//				listRef.downloadUrl.addOnSuccessListener { previewUri ->
//					insertData(videoId, videoUri, previewId, previewUri)
//				}
//			}
//		} else {
//			insertData(videoId, videoUri, null, null)
//		}
//
//	}
//
//	private fun insertData(
//		videoId: String, videoUri: Uri,
//		previewId: String?, previewUri: Uri?
//	) {
//		val reference = mFirebaseDatabase.reference
//
//		val newRecord = Video(
//			id = videoId,
//			url = videoUri.toString(),
//			preview = PreviewImage(
//				id = previewId,
//				url = previewUri.toString()
//			),
//			uploader = User(
//				uid = mFirebaseAuth.currentUser?.uid ?: "",
//				name = mFirebaseAuth.currentUser?.displayName ?: "",
//				email = mFirebaseAuth.currentUser?.email ?: "",
//				photoUrl = mFirebaseAuth.currentUser?.photoUrl?.toString() ?: "",
//			),
//			uploadTimestamp = System.currentTimeMillis(),
//			likedEmails = emptyList()
//		)
//		reference.child(videoId).setValue(newRecord)
//
//		mChannelFlow.value = UploadVideoState.Finished
//	}
}