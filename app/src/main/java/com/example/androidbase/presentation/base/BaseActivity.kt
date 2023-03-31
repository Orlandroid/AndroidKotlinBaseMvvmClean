package com.example.androidbase.presentation.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutId: Int) :
    AppCompatActivity() {


    protected val binding: B by lazy { DataBindingUtil.setContentView<B>(this, layoutId) }


    abstract fun setUpUi()

    open fun showToolbar(shouldShow: Boolean) {}

    open fun showProgress() {}

    open fun hideProgress() {}

    open fun changeTitleToolbar(title: String) {}

    open fun setOnBackClick(clickOnBack: () -> Unit) {}


}