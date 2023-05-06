package com.example.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.extensions.click
import com.example.presentation.extensions.gone
import com.example.presentation.extensions.visible
import com.example.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavController()
    }

    private fun setUpNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun changeTitleToolbar(title: String) {
        binding.toolbarLayout.toolbarTitle.text = title
    }

    fun setOnBackClick(clickOnBack: () -> Unit) {
        binding.toolbarLayout.toolbarBack.click {
            clickOnBack()
        }
    }

    fun showToolbar(shouldShow: Boolean) {
        if (shouldShow) {
            binding.toolbarLayout.root.visible()
        } else {
            binding.toolbarLayout.root.gone()
        }
    }

    private fun setOnBackButton(clickOnBack: (() -> Unit)?) = with(binding) {
        val clickOnBackButton = if (clickOnBack == null) {
            {
                navController?.popBackStack()
            }
        } else {
            {
                clickOnBack()
            }
        }
        toolbarLayout.toolbarBack.click {
            clickOnBackButton()
        }
    }


    fun setToolbarConfiguration(configuration: ToolbarConfiguration) {
        setOnBackButton(configuration.clickOnBack)
        changeTitleToolbar(configuration.toolbarTitle)
        showToolbar(configuration.showToolbar)
    }


    fun showProgress() {
        if (!binding.progressBar.isVisible) {
            binding.progressBar.visible()
        }
    }

    fun hideProgress() {
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

    data class ToolbarConfiguration(
        val showToolbar: Boolean = false,
        val clickOnBack: (() -> Unit)? = null,
        val toolbarTitle: String = ""
    )
}
