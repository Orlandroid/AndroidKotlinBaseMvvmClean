package com.example.presentation.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections


fun NavController.navigateSafe(
    navDirections: NavDirections? = null
) {
    try {
        navDirections?.let {
            this.navigate(navDirections)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}