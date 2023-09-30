package com.example.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    private var searchViewConfig = SearchViewConfig()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavController()
        setSupportActionBar(binding.toolbarLayout.toolbar)//for use searchView in our custom toolbar
    }

    private fun setUpNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun changeTitleToolbar(title: String) {
        binding.toolbarLayout.toolbarTitle.text = title
    }

    private fun showToolbar(shouldShow: Boolean) {
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

    fun showSearchView(config: SearchViewConfig) {
        searchViewConfig = config
    }

    fun setToolbarConfiguration(configuration: ToolbarConfiguration) {
        setOnBackButton(configuration.clickOnBack)
        changeTitleToolbar(configuration.toolbarTitle)
        showToolbar(configuration.showToolbar)
        showBackArrow(configuration.showBackArrow)
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

    private fun showBackArrow(shouldShow: Boolean) {
        if (shouldShow) {
            binding.toolbarLayout.toolbarBack.visible()
        } else {
            binding.toolbarLayout.toolbarBack.gone()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.search)

        searchItem.isVisible = searchViewConfig.showSearchView
        searchItem.setOnMenuItemClickListener {
            searchViewConfig.clickOnSearchIcon()
            true
        }

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                searchViewConfig.onMenuItemActionExpand.invoke()
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS or MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW)
                searchViewConfig.onMenuItemActionCollapse.invoke()
                return true
            }
        })
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = searchViewConfig.hintText
        searchView.gravity = View.TEXT_ALIGNMENT_CENTER
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewConfig.onQueryTextSubmit.invoke(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchViewConfig.onQueryTextChange.invoke(newText)
                return false
            }
        })
        return true
    }

    data class SearchViewConfig(
        val hintText: String = "Buscar",
        val showSearchView: Boolean = false,
        val onMenuItemActionExpand: () -> Unit = {},
        val onMenuItemActionCollapse: () -> Unit = {},
        val onQueryTextSubmit: (query: String) -> Unit = {},
        val onQueryTextChange: (newText: String) -> Unit = {},
        val clickOnSearchIcon: () -> Unit = {}
    )


    data class ToolbarConfiguration(
        val showToolbar: Boolean = false,
        val showBackArrow: Boolean = true,
        val clickOnBack: (() -> Unit)? = null,
        val toolbarTitle: String = ""
    )


}
