package app.caturday.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import model.Video

class FeedRepository {

	private val client = HttpClient(CIO) {

		install(ContentNegotiation) {
			json(Json {
				prettyPrint = true
				isLenient = true
				ignoreUnknownKeys = true
				explicitNulls = false
			})
		}
	}

	val feed: Flow<VideosResult> = flow {
		val response: HttpResponse = client.get("http://192.168.50.94:8080/videos")
		val result: List<Video> = response.body()
		emit(VideosResult.Data(result))
	}
}