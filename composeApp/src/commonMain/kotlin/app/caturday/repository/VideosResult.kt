package app.caturday.repository

import model.Video

sealed interface VideosResult {
	data object Loading : VideosResult
	data object Error : VideosResult
	data class Data(val data: List<Video>) : VideosResult
}