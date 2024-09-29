package com.example.mybrickset.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.example.mybrickset.data.local.Dummy
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.ui.theme.CreamBackground
import com.example.mybrickset.presentation.ui.theme.MyBricksetTheme

@Composable
fun DetailDescription(
    set: Set,
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
                    style = MaterialTheme.typography.titleMedium,
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
            Column (
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 12.dp)
            ) {
                DetailDescriptionRow(
                    label = "Name",
                    value = set.name
                )
                DetailDescriptionRow(
                    label = "Number",
                    value = set.number + "-" + set.numberVariant
                )
                DetailDescriptionRow(
                    label = "Age Range",
                    value = set.ageRange.min.toString() + "+"
                )
                DetailDescriptionRow(
                    label = "Theme",
                    value = set.theme
                )
                DetailDescriptionRow(
                    label = "Sub Theme",
                    value = set.subtheme
                )
                DetailDescriptionRow(
                    label = "Theme Group",
                    value = set.themeGroup
                )
                DetailDescriptionRow(
                    label = "Year",
                    value = set.year.toString()
                )
                DetailDescriptionRow(
                    label = "Dimension",
                    value = "d: ${set.dimensions.depth} h: ${set.dimensions.height} wi: ${set.dimensions.width} we: ${set.dimensions.weight.toInt()}g"
                )
                DetailDescriptionRow(
                    label = "Packaging Type",
                    value = set.packagingType
                )
                DetailDescriptionRow(
                    label = "Pieces",
                    value = set.pieces.toString()+ " pcs"
                )
                DetailDescriptionRow(
                    label = "Minifig",
                    value = set.minifigs.toString() + " Minifigs"
                )
                DetailDescriptionRow(
                    label = "Availability",
                    value = set.availability
                )
            }
            set.extendedData.description.let { description ->
                Text(
                    text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold
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
fun DetailDescriptionRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier)
{
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ){
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.3f)
            )
        Text(
            text = ": ",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            fontWeight = FontWeight.SemiBold,

            color = Color.DarkGray
        )
    }
    
}

@Preview
@Composable
private fun DetailDescriptionPreview() {
    MyBricksetTheme {
        DetailDescription(
            set = Dummy.DummySet
        )
    }

}