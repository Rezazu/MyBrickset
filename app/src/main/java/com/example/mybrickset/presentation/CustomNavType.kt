package com.example.mybrickset.presentation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.mybrickset.data.remote.dto.getreviews.Review
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.data.remote.dto.getthemes.Theme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val theme = object : NavType<Theme>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Theme? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Theme {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Theme): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Theme) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    val review = object : NavType<List<Review>>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): List<Review>? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): List<Review> {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<Review>): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: List<Review>) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }

    val set = object : NavType<Set>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Set? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Set {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Set): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Set) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}