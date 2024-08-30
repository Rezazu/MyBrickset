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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.mybrickset.presentation.ui.theme.CreamBackground
import com.example.mybrickset.presentation.ui.theme.Green
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.Red
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
                .padding(horizontal = 12.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .padding(horizontal = 8.dp)
            ) {
//                IconButton(
//                    onClick = {},
//                    modifier = Modifier
//                        .size(24.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_web),
//                        contentDescription = "",
//                        tint = MatteBlue
//                    )
//                }
            }
            Column {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = set.name,
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
        Spacer(modifier = Modifier.height(36.dp))
        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )
        DetailDescription(
            description = set.extendedData.description,
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

@Composable
fun DetailPrice(
    price: LEGOCom,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp)
    ){
        Text(
            text = "Set Prices",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .background(CreamBackground)
                .border(
                    BorderStroke(
                        1.dp,
                        Color.LightGray
                    )
                )
                .clip(RoundedCornerShape(8.dp))
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_de),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)
                )
                Text(
                    text = "${price.DE.retailPrice} kr",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_us),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)
                )
                Text(
                    text = "$ ${price.US.retailPrice} USD",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_uk),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)
                )
                Text(
                    text = "£ ${price.UK.retailPrice} GBP",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_ca),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)
                )
                Text(
                    text = " $ ${price.CA.retailPrice} CAD",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
fun DetailDescription(
    description: String?,
    modifier: Modifier = Modifier
) {
    var descriptionState by remember {
        mutableStateOf(false)
    }
    
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(CreamBackground)
    ) {
        Column(
            modifier = if (descriptionState == false) {
                modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .padding(
                        vertical = 6.dp,
                        horizontal = 12.dp
                    )
            } else {
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        vertical = 6.dp,
                        horizontal = 12.dp
                    )
            }
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    )
                Icon(
                    imageVector =
                    if (descriptionState == false) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            descriptionState = !descriptionState
                        }
                )
            }
            description?.let { description ->
                Text(
                    text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        TextButton(
            onClick = { descriptionState = !descriptionState },
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        ) {
            if (descriptionState == false) {
                Text(
                    text = "Read More",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            } else {
                Text(
                    text = "Read Less",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun DetailPager(
    images: List<Image>,
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
        ) { page ->
            AsyncImage(
                model = ImageRequest
                    .Builder(context)
                    .placeholder(R.drawable.logo_brickset)
                    .data(images[page].imageURL)
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
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
                Text(
                    text = "/",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
                Text(
                    text = pagerState.pageCount.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun DetailReview(
    rating: Double,
    reviews: List<Review>,
    reviewCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize()
    ) {
        if (reviews.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 6.dp,
                        bottom = 0.dp,
                        start = 12.dp,
                        end = 12.dp
                    )

            ) {
                Text(
                    text = "Review",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = YellowMain,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                    )
                    Text(
                        text = rating.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "${reviewCount} reviews",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Column {
                ReviewCard(review = reviews[0])
                HorizontalDivider()
                ReviewCard(review = reviews[1])
                HorizontalDivider()
            }
        } else {
            Text(
                text = "This set has no review yet",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(vertical = 6.dp)
                )
        }
    }
}

@Composable
fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp)
    ) {
        Text(
            text = review.author,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Row {
            Text(
                text = review.rating.overall.toString()
            )
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = YellowMain,
                modifier = Modifier
            )
        }
        Text(
            text = review.title,
            style = MaterialTheme.typography.titleSmall,
            )
        Text(
            text = HtmlCompat.fromHtml(review.review, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(4.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewCardPreview() {
    MyBricksetTheme {
        DetailReview(
            rating = 5.0,
            reviews = listOf(
                Dummy.DummyReview,
                Dummy.DummyReview,
                Dummy.DummyReview,
            ),
            reviewCount = 5
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

