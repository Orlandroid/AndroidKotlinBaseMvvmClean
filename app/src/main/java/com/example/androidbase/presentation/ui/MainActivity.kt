package com.example.androidbase.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.androidbase.databinding.ActivityMainBinding
import com.example.androidbase.presentation.extensions.gone
import com.example.androidbase.presentation.extensions.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.progressBar.isVisible = isLoading
    }
}