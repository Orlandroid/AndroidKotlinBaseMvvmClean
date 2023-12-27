package com.example.presentation.extensions

import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun Any.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}

inline fun <reified T> String.fromJson(): T {
    val gson = Gson()
    val type = object : TypeToken<T>() {}.type
    return gson.fromJson(this, type)
}

inline fun <reified T> Fragment.readRawJson(@RawRes rawResId: Int): T {
    val gson = Gson()
    resources.openRawResource(rawResId).bufferedReader().use {
        return gson.fromJson(it, object : TypeToken<T>() {}.type)
    }
}


