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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.mybrickset.data.remote.dto.getadditionalimages.AdditionalImage
import com.example.mybrickset.data.remote.dto.getsets.LEGOCom
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.ui.theme.CreamBackground
import com.example.mybrickset.presentation.ui.theme.Green
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.Red
import com.example.mybrickset.presentation.ui.theme.YellowMain

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

    Surface (
        color = CreamBackground,
        modifier = if (descriptionState == false) {
            modifier
                .fillMaxWidth()
                .height(128.dp)
        } else {
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 6.dp,
                    horizontal = 12.dp
                )
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
    reviewCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(256.dp)
            .padding(
                vertical = 6.dp,
                horizontal = 12.dp
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

