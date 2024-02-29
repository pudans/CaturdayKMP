package app.caturday

import SERVER_PORT
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import model.PreviewImage
import model.User
import model.Video

fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

private val currentUser = User(
    uid = "99",
    email = "greens.street@gmail.com",
    name = "@greens.street",
    photoUrl = "https://i.kym-cdn.com/entries/icons/original/000/013/564/doge.jpg"
)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    routing {

        staticResources("/videos", "videos")
        staticResources("/previews", "previews")

        get("/user") {
            call.respond(
                status = HttpStatusCode.OK,
                message = currentUser
            )
        }
        get("/videos") {
            call.respond(
                status = HttpStatusCode.OK,
                message = listOf(
                    Video(
                        id = "01",
                        url = "/videos/video1.mp4",
                        preview = PreviewImage(
                            id = "01-1",
                            url = "/previews/preview1.png"
                        ),
                        uploader = currentUser,
                        uploadTimestamp = System.currentTimeMillis() - 10000,
                        likedEmails = listOf()
                    ),
                    Video(
                        id = "02",
                        url = "/videos/video2.mp4",
                        preview = PreviewImage(
                            id = "02-1",
                            url = "/previews/preview2.png"
                        ),
                        uploader = currentUser,
                        uploadTimestamp = System.currentTimeMillis() - 20000,
                        likedEmails = listOf()
                    ),
                    Video(
                        id = "03",
                        url = "/videos/video3.mp4",
                        preview = PreviewImage(
                            id = "03-1",
                            url = "/previews/preview3.png"
                        ),
                        uploader = currentUser,
                        uploadTimestamp = System.currentTimeMillis() - 30000,
                        likedEmails = listOf()
                    )
                )
            )
        }
    }
}
