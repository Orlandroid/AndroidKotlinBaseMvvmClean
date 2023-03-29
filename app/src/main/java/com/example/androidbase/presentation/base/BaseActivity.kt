package com.example.androidbase.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity(@LayoutRes val layoutId: Int) :
    AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi(layoutId)
    }

    abstract fun setUpUi(@LayoutRes layoutId: Int)

    open fun showToolbar(shouldShow: Boolean) {}

    open fun showProgress() {}

    open fun hideProgress() {}

    open fun setOnBackClick(clickOnBack: () -> Unit) {}
}