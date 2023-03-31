package com.example.androidbase.presentation.ui

import androidx.core.view.isVisible
import com.example.androidbase.R
import com.example.androidbase.databinding.ActivityMainBinding
import com.example.androidbase.presentation.base.BaseActivity
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.gone
import com.example.androidbase.presentation.extensions.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {


    override fun setUpUi() {

    }


    override fun changeTitleToolbar(title: String) {
        binding.toolbarLayout.toolbarTitle.text = title
    }

    override fun setOnBackClick(clickOnBack: () -> Unit) {
        binding.toolbarLayout.toolbarBack.click {
            clickOnBack()
        }
    }

    override fun showToolbar(shouldShow: Boolean) {
        if (shouldShow) {
            binding.toolbarLayout.root.visible()
        } else {
            binding.toolbarLayout.root.gone()
        }
    }

    override fun showProgress() {
        if (!binding.progressBar.isVisible) {
            binding.progressBar.visible()
        }
    }

    override fun hideProgress() {
        if (binding.progressBar.isVisible) {
            binding.progressBar.gone()
        }
    }

    fun shouldShowProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }
}