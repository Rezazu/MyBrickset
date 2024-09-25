package com.example.mybrickset.presentation.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.R
import com.example.mybrickset.Services
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getreviews.Review
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.Screen
import com.example.mybrickset.presentation.component.DetailButton
import com.example.mybrickset.presentation.component.DetailDescription
import com.example.mybrickset.presentation.component.DetailNotes
import com.example.mybrickset.presentation.component.DetailNotesForm
import com.example.mybrickset.presentation.component.DetailPager
import com.example.mybrickset.presentation.component.DetailPrice
import com.example.mybrickset.presentation.component.DetailReview
import com.example.mybrickset.presentation.ui.theme.DarkGray
import com.example.mybrickset.presentation.ui.theme.Green
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme
import com.example.mybrickset.presentation.ui.theme.Red
import com.example.mybrickset.presentation.ui.theme.YellowMain

@Composable
fun DetailScreen(
    setId: Int,
    navController: NavHostController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val images by viewModel.images.collectAsState()
    val reviews by viewModel.reviews.collectAsState()
    val formState by viewModel.formState.collectAsState()
    val notes by viewModel.notes.collectAsState()

    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onFloatingActionButtonClicked(true)
                },
                containerColor = MatteBlue,
                modifier = Modifier
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Add Notes",
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Add Notes")
                }
            }
        },
    ) { innerPadding ->
        viewModel.set.collectAsState(initial = Result.Loading).value.let { result ->
            when(result) {
                is Result.Error ->  {

                }
                is Result.Loading -> {
                    viewModel.getSetByID(setId)
                }
                is Result.Success -> {
                    if (formState) {
                        Dialog(
                            properties =  DialogProperties( usePlatformDefaultWidth = false ),
                            onDismissRequest = { viewModel.onFloatingActionButtonClicked(false) }
                        ) {
                            DetailNotesForm(
                                notes = notes,
                                onNotesInputChanges = viewModel::onNotesInputChange,
                                onNotesSubmitted = {
                                    viewModel.setCollectionNotes(result.data.setID, notes)
                                },
                                onDismissRequest = { viewModel.onFloatingActionButtonClicked(false) }
                            )
                        }
                    }
                    DetailScreenContent(
                        set = result.data,
                        additionalImage = images.images,
                        reviews = reviews.reviews,
                        context = context,
                        notes = notes,
                        navigateToReviewScreen = {
                            navController.navigate(
                                Screen.ReviewScreen(
                                    reviews = reviews.reviews,
                                    rating = result.data.rating,
                                    reviewCount = result.data.reviewCount
                                )
                            )
                        },
                        onButtonFavoriteClicked = {
                            viewModel.setCollectionWanted(
                                setId = result.data.setID,
                                isWanted = if (result.data.collection.wanted) 0 else 1
                            )
                        },
                        onButtonOwnedClicked = {
                            viewModel.setCollectionOwned(
                                setId = result.data.setID,
                                isOwned = if (result.data.collection.owned) 0 else 1
                            )
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
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
    notes: String,
    navigateToReviewScreen:() -> Unit,
    onButtonFavoriteClicked:() -> Unit,
    onButtonOwnedClicked:() -> Unit,
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState(pageCount = { additionalImage.size })
    val scrollState = rememberScrollState()

    var isFavorited by remember {
        mutableStateOf(set.collection.wanted)
    }

    var isOwned by remember {
        mutableStateOf(set.collection.owned)
    }

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
                    IconButton(
                        onClick = {
                            onButtonFavoriteClicked()
                            isFavorited = !isFavorited
                            Toast.makeText(
                                context,
                                if (isFavorited) "Set added to wishlist" else "Set removed from wishlist",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                    ) {
                        if (isFavorited) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = Red,
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = DarkGray,
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                    }
                }
                if(set.released) {
                    Text(
                        text = "Available Now",
                        color = Green,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(horizontal = 2.dp, vertical = 8.dp)
                    )
                } else {
                    Text(
                        text = "Not Available",
                        color = Red,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(horizontal = 2.dp, vertical = 8.dp)
                    )

                }
                Image(
                    painter = painterResource(id = Services.getThemeIcon(set.theme)),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        DetailPrice(
            price = set.LEGOCom
        )
        Spacer(modifier = Modifier.height(24.dp))
        DetailButton(
            onButtonWebsiteClicked = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(set.bricksetURL)
                )
                startActivity(context, intent, null)
            },
            onButtonOwnedClicked = {
                onButtonOwnedClicked()
                isOwned = !isOwned
                Toast.makeText(
                    context,
                    if (isOwned) "Set added to collection" else "Set removed from collection",
                    Toast.LENGTH_SHORT
                ).show()
            },
            isowned = isOwned
        )
        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )
        DetailDescription(
            set = set,
        )
        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )
        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )
        if(notes.isNotEmpty()) {
            DetailNotes(
                notes = notes
            )
        }
        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )
        DetailReview(
            rating = set.rating,
            reviews = reviews,
            reviewCount = set.reviewCount,
            navigateToReviewScreen = navigateToReviewScreen,
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
            ),
            notes = "Probably Bought in January 2025, if I have the money :) and also a couple of other set and more of the other set",
            navigateToReviewScreen = {},
            onButtonFavoriteClicked = {},
            onButtonOwnedClicked = {}
        )
    }
}

