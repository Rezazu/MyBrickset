package com.example.mybrickset.presentation.detail

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybrickset.R
import com.example.mybrickset.Services
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getreviews.Review
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getsets.LEGOCom
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.component.DetailButton
import com.example.mybrickset.presentation.component.DetailDescription
import com.example.mybrickset.presentation.component.DetailPager
import com.example.mybrickset.presentation.component.DetailPrice
import com.example.mybrickset.presentation.component.DetailReview
import com.example.mybrickset.presentation.ui.theme.CreamBackground
import com.example.mybrickset.presentation.ui.theme.Green
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.Red
import com.example.mybrickset.presentation.ui.theme.WhiteBackground
import com.example.mybrickset.presentation.ui.theme.YellowMain

@Composable
fun DetailScreen(
    setId: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val images = viewModel.images.collectAsState()
    val reviews = viewModel.reviews.collectAsState()

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
                    reviews = reviews.value.reviews,
                    context
                )
            }
        }
    }

}

@Composable
fun DetailScreenContent(
    set: Set,
    additionalImage: List<Image>,
    reviews: List<Review>,
    context: Context,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { additionalImage.size })
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        DetailPager(
            images = additionalImage,
            context = context,
            pagerState = pagerState
        )
        HorizontalDivider(modifier = Modifier.height(1.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 18.dp)
        ) {
            Column {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${set.name} ${set.number}-${set.numberVariant}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                    )
                    Image(
                        painter = painterResource(id = Services.getThemeIcon(set.theme)),
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .height(32.dp)
                    )
                }
                if(set.released == true) {
                    Text(
                        text = "Available Now",
                        color = Green,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(2.dp)
                    )
                } else {
                    Text(
                        text = "Not Available",
                        color = Red,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(2.dp)
                    )

                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
        DetailPrice(
            price = set.LEGOCom
        )
        Spacer(modifier = Modifier.height(24.dp))
        DetailButton(bricksetUrl = set.bricksetURL, isFavorite = set.collection.wanted)
        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )
        DetailDescription(
            set = set,
        )
        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )
        DetailReview(
            rating = set.rating,
            reviews = reviews,
            reviewCount = set.reviewCount,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
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
                Image(
                    thumbnailURL = "https://images.brickset.com/sets/AdditionalImages/40674-1/tn_40674_alt1_jpg.jpg",
                    imageURL = "https://images.brickset.com/sets/AdditionalImages/40674-1/40674_alt1.jpg",
                )
            ),
            context = LocalContext.current,
            set = Dummy.DummySet,
            reviews = listOf(
                Dummy.DummyReview,
                Dummy.DummyReview,
            )
        )
    }
}

