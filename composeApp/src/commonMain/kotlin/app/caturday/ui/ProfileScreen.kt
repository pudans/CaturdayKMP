package app.caturday.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@ExperimentalFoundationApi
@Composable
fun ProfileScreen() {

//	val viewModel = hiltViewModel<ProfileViewModel>()

	Column(
		modifier = Modifier
			.background(color = Color.Blue)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {

//		val profileState by viewModel.observeProfileState()
//
//		Toolbar(profileState)
//
//		Spacer(modifier = Modifier.size(24.dp))
//
//		AvatarImage(profileState.avatarUrl)
//
//		Spacer(modifier = Modifier.size(16.dp))
//
//		ProfileNick(profileState.nick)
//
//		Spacer(modifier = Modifier.size(24.dp))
//
//		val pages = arrayOf("UPLOADED", "LIKED")
//		val pagerState = rememberPagerState()
//
//		Tabs(pages, pagerState)
//
//		val uploadedVideos by viewModel.observeUploadedVideosState()
//		val likedVideos by viewModel.observeLikedVideosState()
//
//		val data = arrayOf(uploadedVideos, likedVideos)
//
//		Pager(data, pagerState) {
//			viewModel.onVideoClick(it)
//		}
	}
}
//
//@Composable
//private fun Toolbar(
//	state: ProfileState
//) {
//	TopAppBar(
//		modifier = Modifier.fillMaxWidth(),
//		backgroundColor = Color.Black
//	) {
//		Box(
//			modifier = Modifier
//				.fillMaxWidth()
//				.padding(horizontal = 12.dp),
//		) {
//			Text(
//				text = state.title,
//				modifier = Modifier.fillMaxWidth(),
//				textAlign = TextAlign.Center,
//				color = Color.White
//			)
//		}
//	}
//}
//
//@Composable
//private fun AvatarImage(avatarUrl: String) {
//	Image(
//		painter = rememberCoilPainter(
//			request = avatarUrl,
//			fadeIn = true
//		),
//		contentDescription = "",
//		contentScale = ContentScale.Crop,
//		modifier = Modifier
//			.size(80.dp)
//			.clip(CircleShape)
//			.border(2.dp, Color.White, CircleShape)
//	)
//}
//
//@Composable
//private fun ProfileNick(nick: String) {
//	Text(
//		text = nick,
//		color = Color.White,
//		textAlign = TextAlign.Center,
//		modifier = Modifier.fillMaxWidth()
//	)
//}
//
//@ExperimentalPagerApi
//@Composable
//private fun Tabs(pages: Array<String>, pagerState: PagerState) {
//	val coroutineScope = rememberCoroutineScope()
//
//	TabRow(
//		backgroundColor = Color.Black,
//		contentColor = Color.White,
//		selectedTabIndex = pagerState.currentPage,
//		indicator = { tabPositions ->
//			TabRowDefaults.Indicator(
//				Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
//			)
//		}
//	) {
//		pages.forEachIndexed { index, title ->
//			Tab(
//				text = {
//					Text(
//						text = title,
//						color = Color.White
//					)
//				},
//				selected = pagerState.currentPage == index,
//				onClick = {
//					coroutineScope.launch {
//						pagerState.animateScrollToPage(index)
//					}
//				}
//			)
//		}
//	}
//}
//
//@ExperimentalFoundationApi
//@ExperimentalPagerApi
//@Composable
//private fun Pager(
//	data: Array<ProfileVideoListState>,
//	pagerState: PagerState,
//	onItemClick: (String) -> Unit
//) {
//	HorizontalPager(
//		count = data.size,
//		state = pagerState
//	) { page ->
//		when (val listState = data[page]) {
//			is ProfileVideoListState.Data -> List(listState.items, onItemClick)
//			ProfileVideoListState.Empty -> EmptyPager()
//			ProfileVideoListState.Loading -> Loading()
//		}
//	}
//}
//
//@ExperimentalFoundationApi
//@Composable
//private fun List(
//	items: List<ProfileVideoItemState>,
//	onItemClick: (String) -> Unit
//) {
//	LazyVerticalGrid(
//		cells = GridCells.Fixed(3),
//		modifier = Modifier.fillMaxSize()
//	) {
//		items(items.size) { PagerItem(items[it], onItemClick) }
//	}
//}
//
//@Composable
//private fun PagerItem(
//	item: ProfileVideoItemState,
//	onClick: (String) -> Unit
//) {
//	Box(
//		modifier = Modifier
//			.fillMaxWidth()
//			.aspectRatio(0.56f)
//			.clickable { onClick.invoke(item.id) }
//	) {
//		Image(
//			painter = rememberCoilPainter(
//				request = item.url,
//				fadeIn = true
//			),
//			contentDescription = ""
//		)
//	}
//}
//
//@Composable
//private fun EmptyPager() {
//	Box(modifier = Modifier.fillMaxSize()) {
//		Text(
//			text = "EMPTY",
//			color = Color.White,
//			textAlign = TextAlign.Center,
//			modifier = Modifier.align(Alignment.Center)
//		)
//	}
//}
//
//@Composable
//private fun Loading() {
//	Box(modifier = Modifier.fillMaxSize()) {
//		CircularProgressIndicator(
//			modifier = Modifier
//				.size(64.dp)
//				.align(Alignment.Center),
//			color = Color.White
//		)
//	}
//}