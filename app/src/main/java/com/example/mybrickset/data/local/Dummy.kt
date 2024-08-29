package com.example.mybrickset.data.local

import com.example.mybrickset.R
import com.example.mybrickset.data.remote.dto.getsets.AgeRange
import com.example.mybrickset.data.remote.dto.getsets.Barcode
import com.example.mybrickset.data.remote.dto.getsets.CA
import com.example.mybrickset.data.remote.dto.getsets.Collection
import com.example.mybrickset.data.remote.dto.getsets.Collections
import com.example.mybrickset.data.remote.dto.getsets.DE
import com.example.mybrickset.data.remote.dto.getsets.Dimensions
import com.example.mybrickset.data.remote.dto.getsets.ExtendedData
import com.example.mybrickset.data.remote.dto.getsets.Image
import com.example.mybrickset.data.remote.dto.getsets.LEGOCom
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getsets.UK
import com.example.mybrickset.data.remote.dto.getsets.US
import com.example.mybrickset.domain.model.News
import com.example.mybrickset.presentation.component.LegoItem

object Dummy {

    val DummyCondition = listOf(
        "New",
        "MISB",
        "Used"
    )

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
                    min = 9,
                    max = 12
                ),
                dimensions = Dimensions(
                    height = 26.2,
                    width = 38.2,
                    depth = 5.7,
                    weight = 500.0
                ),
                barcode =  Barcode(
                    EAN= "5702017155517",
                    UPC = "673419357456"
                ),
                extendedData = ExtendedData(
                    description = "<p>Tatooine tales from <i>Star&nbsp;Wars</i>: The Book of Boba Fett come to life for young builders with this LEGOÂ® brick model of The Mandalorianâ€™s N-1 Starfighter (75325). It has a minifigure cockpit, passenger space for Grogu, a cargo compartment, a spring-loaded shooter and realistic details. A fun gift for kids aged 9 and up, this building toy features LEGO minifigures of The Mandalorian and Peli Motto, plus LEGO figures of Grogu (affectionately known by fans as â€˜Baby Yodaâ€™) and a BD Droid to inspire creative role play.</p><p>Digital building tools<br />Step-by-step, illustrated building instructions are included with this set. And check out the LEGO Building Instructions app, with intuitive zoom and rotate viewing tools to add another dimension to the creative experience.</p><p>Awesome building toys<br />The LEGO Group has been creating brick-built versions of iconic <i>Star&nbsp;Wars</i>â„¢ starships, vehicles, locations and characters since 1999. LEGO <i>Star&nbsp;Wars</i> has become its most successful theme, with sets to excite fans of all ages.</p><ul><li>The Mandalorianâ€™s N-1 Starfighter (75325) from <i>Star&nbsp;Wars</i>: The Book of Boba Fett â€“ Fans can relive <i>Star&nbsp;Wars</i>: The Book of Boba Fett stories on Tatooine with this authentically detailed building toy</li><li>4 <i>Star&nbsp;Wars</i>â„¢ characters â€“ LEGOÂ® minifigures of The Mandalorian, with a darksaber and a jetpack accessory element, and Peli Motto with a wrench, plus LEGO figures of Grogu and a BD Droid</li><li>Play-inspiring features â€“ The starfighter features a minifigure cockpit, passenger space for Grogu, a small cargo compartment, a spring-loaded shooter and lots of authentic bashed-up details</li><li>Fun gift idea for ages 9 and up â€“ Give this 412-piece buildable toy playset as a birthday present, Christmas gift or special reward to creative kids who are into <i>Star&nbsp;Wars</i>: The Book of Boba Fett</li><li>For play and display â€“ This buildable N-1 Starfighter model measures over 7 cm (2.5 in.) high, 42 cm (16.5 in.) long and 29 cm (11.5 in.) wide, and can be displayed between playtimes</li><li>App-assisted building â€“ Find instructions in the box and on the LEGOÂ® Building Instructions app, which features digital viewing tools to add to the fun, creative experience</li><li>Building toys for all ages â€“ Discover LEGOÂ® <i>Star&nbsp;Wars</i>â„¢ sets for kids and adult fans to recreate iconic scenes, make up their own stories or simply display the buildable models</li><li>Premium quality â€“ LEGOÂ® bricks and pieces meet stringent quality standards, ensuring that they connect simply and securely</li><li>Safety assurance â€“ LEGOÂ® components are dropped, heated, crushed, twisted and carefully analysed to make sure they comply with rigorous global safety standards</li></ul>",
                    tags = listOf("Grogu","Mandalorian","Tatooine"),
                ),
                lastUpdated = "2022-05-04T16:21:28.11Z"
            )
}