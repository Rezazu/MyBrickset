package com.example.mybrickset

object Services {

    fun GetThemeImage(theme: String): Int {
        var image: Int = R.drawable.ic_launcher_background

        when(theme) {
            "City" -> { image = R.drawable.theme_city }
            "Classic" -> { image = R.drawable.theme_classic }
            "Creator" -> { image = R.drawable.theme_creator }
            "Disney" -> { image = R.drawable.theme_disney }
            "Duplo" -> { image = R.drawable.theme_duplo }
            "Friends" -> { image = R.drawable.theme_friends }
            "Harry Potter" -> { image = R.drawable.theme_harrypotter }
            "Ideas" -> { image = R.drawable.theme_ideas }
            "Marvel" -> { image = R.drawable.theme_ideas }
            "Minecraft" -> { image = R.drawable.theme_minecraft }
            "Ninjago" -> { image = R.drawable.theme_speed }
            "Speed" -> { image = R.drawable.theme_speed }
            "Star Wars" -> { image = R.drawable.theme_starwars }
            "Technic" -> { image = R.drawable.theme_technic }
        }
        return image
    }

    fun getThemeIcon(theme: String): Int {
        var image: Int = R.drawable.ic_launcher_background

        when(theme) {
            "Star Wars" -> { image = R.drawable.ic_starwars }
            "Ninjago" -> { image = R.drawable.ic_ninjago }
            "Technic" -> { image = R.drawable.ic_technic }
            "City" -> { image = R.drawable.ic_city }
            "Ideas" -> { image = R.drawable.ic_ideas }
        }
        return image
    }

    fun getPriceIDR(price: Double): String {
        return "Rp. $price"
    }

    fun getConditionIcon(condition: String): Int {
        var image: Int = R.drawable.brickset_banner
        when(condition) {
            "New" -> { image = R.drawable.icon_new}
            "MISB" -> { image = R.drawable.icon_misb}
            "Used" -> { image = R.drawable.icon_used}
        }
        return image
    }

}