package com.example.androidbase.presentation.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.io.ByteArrayOutputStream
import java.io.IOException


fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun ImageView.setColorFilterImage(context: Context, @ColorRes colorInt: Int) {
    this.setColorFilter(
        ContextCompat.getColor(context, colorInt),
        PorterDuff.Mode.SRC_OVER
    )
}

@RequiresApi(Build.VERSION_CODES.M)
fun Drawable.tint(context: Context, @ColorRes color: Int) {
    DrawableCompat.setTint(this, context.resources.getColor(color, context.theme))
}


fun Bitmap.toBase64(): String {
    var result = ""
    val baos = ByteArrayOutputStream()
    try {
        compress(Bitmap.CompressFormat.JPEG, 100, baos)
        baos.flush()
        baos.close()
        val bitmapBytes = baos.toByteArray()
        result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            baos.flush()
            baos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return result
}

fun String.base64StringToBitmap(): Bitmap {
    val baos = ByteArrayOutputStream()
    var imageBytes: ByteArray = baos.toByteArray()
    imageBytes = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}








