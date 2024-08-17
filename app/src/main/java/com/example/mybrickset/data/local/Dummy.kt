package com.example.mybrickset.data.local

import com.example.mybrickset.R
import com.example.mybrickset.data.remote.dto.getsets.AgeRange
import com.example.mybrickset.data.remote.dto.getsets.Barcode
import com.example.mybrickset.data.remote.dto.getsets.CA
import com.example.mybrickset.data.remote.dto.getsets.Collection
import com.example.mybrickset.data.remote.dto.getsets.Collections
import com.example.mybrickset.data.remote.dto.getsets.DE
import com.example.mybrickset.data.remote.dto.getsets.Dimensions
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getsets.LEGOCom
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getsets.UK
import com.example.mybrickset.data.remote.dto.getsets.US
import com.example.mybrickset.domain.model.News
import com.example.mybrickset.presentation.component.LegoItem

object Dummy {
    val DummyTheme = listOf(
        "Star Wars",
        "Ninjago",
        "City",
        "Classic",
        "Creator",
        "Disney",
        "Duplo",
        "Friends",
        "Harry Potter",
        "Ideas",
        "Marvel",
        "Minecraft",
        "Speed",
        "Technic"
    )
    val DummyNews = listOf(
        News(
            "For a long time now, lovers of LEGO® sets have wondered when Link, Princess Zelda and the world of Hyrule would appear in LEGO brick form.",
            R.drawable.news_banner_1
        ),
        News(
            "Enter a zone of complete mindfulness with the impressive new LEGO® Technic™ Ferrari Daytona SP3",
            R.drawable.news_banner_2
        ),
        News(
            "In April, we invited some critically charismatic D&D® players for an epic game night featuring the glorious LEGO® Ideas DUNGEONS & DRAGONS®: Red Dragon’s Tale set",
            R.drawable.news_banner_3
        ),
        News(
            "Action-packed LEGO® Minecraft® set, with caves to explore, skeletons to battle and endless hands-on creative play.",
            R.drawable.news_banner_4
        ),
        News(
            "One of the most iconic locations in Middle-earth™, the Dark Tower is brought to life in a detailed LEGO® model filled with secrets and surprises.",
            R.drawable.news_banner_5
        )
    )
    val DummySet =
            Set(
                setID = 32503,
                number = "75325",
                numberVariant = 1,
                name = "The Mandalorian's N-1 Starfighter",
                year = 2022,
                theme = "Star Wars",
                themeGroup = "Licensed",
                subtheme = "The Book of Boba Fett",
                category = "Normal",
                released =  true,
                pieces = 412,
                minifigs = 4,
                image = Image(
                    thumbnailURL = "https://images.brickset.com/sets/small/75325-1.jpg",
                    imageURL = "https://images.brickset.com/sets/images/75325-1.jpg"
                ),
                bricksetURL = "https://brickset.com/sets/75325-1",
                collection = Collection(
                    owned = true,
                    wanted = false,
                    qtyOwned = 1,
                    rating = 0,
                    notes = ""
                ),
                collections = Collections(
                    ownedBy = 14436,
                    wantedBy = 3399
                ),
                LEGOCom = LEGOCom(
                    US= US(
                        retailPrice = 59.99,
                        dateFirstAvailable = "2022-02-16T00:00:00Z"
                    ),
                    UK = UK(
                        retailPrice = 59.99,
                        dateFirstAvailable = "2022-02-16T00:00:00Z"
                    ),
                    CA = CA(
                        retailPrice = 79.99,
                        dateFirstAvailable = "2022-02-16T00:00:00Z"
                    ),
                    DE = DE(
                        retailPrice = 64.99,
                        dateFirstAvailable = "2022-02-17T00:00:00Z"
                    )
                ),
                rating = 3.9,
                reviewCount = 4,
                packagingType = "Box",
                availability = "Retail",
                instructionsCount = 3,
                additionalImageCount = 7,
                ageRange = AgeRange(
                    min = 9
                ),
                dimensions = Dimensions(
                    height = 26.2,
                    width = 38.2,
                    depth = 5.7
                ),
                barcode =  Barcode(
                    EAN= "5702017155517",
                    UPC = "673419357456"
                ),
                lastUpdated = "2022-05-04T16:21:28.11Z"
            )
}