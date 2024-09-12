package com.example.mybrickset.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mybrickset.R
import com.example.mybrickset.presentation.ui.theme.MatteBlue
import com.example.mybrickset.presentation.ui.theme.Red


@Composable
fun DetailButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onButtonWebsiteClicked:() -> Unit,
    onButtonFavoriteClicked:() -> Unit,
) {
    val context = LocalContext.current
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Button(
            onClick = onButtonWebsiteClicked,
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MatteBlue),
            modifier = Modifier
                .width(156.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_web),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                )
                Text(
                    text = "Visit on Brickset",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
        if (isFavorite) {
            Button(
                onClick = onButtonFavoriteClicked,
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    containerColor = Red
                ),
                modifier = Modifier
                    .width(156.dp),
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = "In your Wishlist",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(2.dp)
                    )
                }
            }
        } else {
            OutlinedButton(
                onClick = onButtonFavoriteClicked,
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Red,
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(2.dp, Red),
                modifier = Modifier
                    .width(156.dp),
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = "Add to Wishlist",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(2.dp)
                    )
                }
            }
        }

    }
}