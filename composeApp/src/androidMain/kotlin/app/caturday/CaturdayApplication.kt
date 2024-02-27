package app.caturday

import android.app.Application
import app.caturday.repository.FeedRepository
import app.caturday.repository.LikeRepository
import app.caturday.repository.MLVisionRepository
import app.caturday.repository.ProfileLikedRepository
import app.caturday.repository.ProfileUploadedRepository
import app.caturday.repository.UploadFileRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CaturdayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            androidContext(this@CaturdayApplication)
//            androidLogger()
//
//            modules(
//                module {
//                    factory { FeedViewModel(get(), get()) }
//
//                    single { FeedRepository() }
//                    single { LikeRepository() }
//                    single { MLVisionRepository() }
//                    single { ProfileLikedRepository() }
//                    single { ProfileUploadedRepository() }
//                    single { UploadFileRepository() }
//                }
//            )
//        }
    }
}