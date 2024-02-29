@file:OptIn(ExperimentalFoundationApi::class)

package app.caturday

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.caturday.repository.FeedRepository
import app.caturday.repository.LikeRepository
import app.caturday.repository.MLVisionRepository
import app.caturday.repository.ProfileLikedRepository
import app.caturday.repository.ProfileUploadedRepository
import app.caturday.repository.UploadFileRepository
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import app.caturday.ui.FeedScreen
import app.caturday.ui.ProfileScreen
import app.caturday.ui.UploadVideoScreen
import app.caturday.ui.theme.CaturdayTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
fun App() {

    KoinApplication(application = {
        modules(
            module {
                factory { FeedViewModel(get(), get()) }

                single { FeedRepository() }
                single { LikeRepository() }
                single { MLVisionRepository() }
                single { ProfileLikedRepository() }
                single { ProfileUploadedRepository() }
                single { UploadFileRepository() }
            }
        )
    }) {
        PreComposeApp {
            val navigator = rememberNavigator()
            CaturdayTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = Color.Black
                        ) {
//                    val navBackStackEntry = navigator.currentEntry
//                    val currentRoute = navBackStackEntry.first()?.route?.route

                            Screen.ITEMS.forEach { screen ->
                                val isSelected = false //currentRoute == screen.route
                                BottomNavigationItem(
                                    selectedContentColor = Color.Black,
                                    icon = { NavigationIcon(isSelected, screen) },
                                    label = { NavigationLAbel(isSelected, screen) },
                                    alwaysShowLabel = false,
                                    selected = isSelected,
                                    onClick = {
                                        if (screen != Screen.UploadVideo) {
                                            navigator.navigate(screen.route)
//                                    navController.navigate(screen.route) {
//                                        popUpTo(navController.graph.startDestinationRoute!!) {
//                                            saveState = true
//                                        }
//                                        launchSingleTop = true
//                                        restoreState = true
//                                    }
                                        } else {
//                                    startActivity(
//                                        Intent(
//                                            this@MainActivity,
//                                            UploadVideoActivity::class.java
//                                        ), intent.extras
//                                    )
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navigator = navigator,
                            navTransition = NavTransition(),
                            initialRoute = "/feed",
                        ) {
                            scene(
                                route = "/feed",
                                navTransition = NavTransition(),
                            ) {
                                FeedScreen()
                            }
                            scene(
                                route = "/upload",
                                navTransition = NavTransition(),
                            ) {
                                UploadVideoScreen {  }
                            }
                            scene(
                                route = "/profile",
                                navTransition = NavTransition(),
                            ) {
                                ProfileScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun NavigationIcon(isSelected: Boolean, screen: Screen) {
    Icon(
        imageVector = if (isSelected) screen.selectedIcon else screen.defaultIcon,
        contentDescription = "",
        tint = Color.White
    )
}

@Composable
private fun NavigationLAbel(isSelected: Boolean, screen: Screen) {
    Text(
        text = screen.title,
        color = Color.White
    )
}

@Composable
fun ProfileTransitionAnimation(content: @Composable AnimatedVisibilityScope.() -> Unit) {
    AnimatedVisibility(
        visibleState = remember { MutableTransitionState(false).apply { targetState = true } },
        enter = slideInHorizontally(initialOffsetX = { 400 }) + fadeIn(),
        exit = slideOutVertically() + fadeOut(),
        content = content,
    )
}

@Composable
fun FeedTransitionAnimation(content: @Composable AnimatedVisibilityScope.() -> Unit) {
    AnimatedVisibility(
        visibleState = remember { MutableTransitionState(false).apply { targetState = true } },
        enter = slideInHorizontally(initialOffsetX = { -400 }) + fadeIn(),
        exit = slideOutVertically() + fadeOut(),
        content = content,
    )
}