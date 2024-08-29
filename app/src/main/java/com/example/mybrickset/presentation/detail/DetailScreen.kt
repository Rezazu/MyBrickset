package com.example.mybrickset.presentation.detail

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.Services.cleanTextContent
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getadditionalimages.AdditionalImage
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getsets.UK
import com.example.mybrickset.data.remote.dto.getsets.US
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.Typography

@Composable
fun DetailScreen(
    setId: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val images = viewModel.images.collectAsState()
    val context = LocalContext.current
    viewModel.set.collectAsState(initial = Result.Loading).value.let { result ->
        when(result) {
            is Result.Error ->  {

            }
            is Result.Loading -> {
                viewModel.getSetByID(setId)
            }
            is Result.Success -> {
                DetailScreenContent(
                    set = result.data,
                    additionalImage = images.value.images,
                    context
                )
            }
        }
    }

}

@Composable
fun DetailScreenContent(
    set: Set,
    additionalImage: List<AdditionalImage>,
    context: Context,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { additionalImage.size })
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        DetailPager(
            images = additionalImage,
            context = context,
            pagerState = pagerState
        )
        HorizontalDivider(modifier = Modifier.height(1.dp))
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = set.theme)
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null
                )
            }
            Text(
                text = set.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
            Text(
                text = "Released ${set.released}"
            )
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = set.extendedData.description.cleanTextContent,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}

@Composable
fun DetailPager(
    images: List<AdditionalImage>,
    context: Context,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(context)
                    .placeholder(R.drawable.logo_brickset)
                    .data(images[0].imageURL)
                    .build(),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(screenWidth)
                    .height(screenWidth)
            )
        }
        Surface(
            color = Color.White,
            modifier = Modifier
                .padding(24.dp)
                .width(52.dp)
                .height(32.dp)
                .align(Alignment.BottomStart),
            border = BorderStroke(1.dp, Color.LightGray),
            shape = RoundedCornerShape(8.dp)

            ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = (pagerState.currentPage + 1).toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Text(
                    text = "/",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Text(
                    text = pagerState.pageCount.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview(

) {
    MyBricksetTheme {
        DetailScreenContent(
            additionalImage =
            listOf(
                AdditionalImage(
                    thumbnailURL = "https://images.brickset.com/sets/AdditionalImages/40674-1/tn_40674_alt1_jpg.jpg",
                    imageURL = "https://images.brickset.com/sets/AdditionalImages/40674-1/40674_alt1.jpg",
                )
            ),
            context = LocalContext.current,
            set = Dummy.DummySet
        )
    }
}

