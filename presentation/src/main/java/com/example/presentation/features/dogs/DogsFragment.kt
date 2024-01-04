package com.example.presentation.features.dogs

import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentDogsBinding
import com.example.presentation.extensions.click
import com.example.presentation.extensions.loadImage
import com.example.presentation.extensions.observeFlow
import com.example.presentation.features.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DogsFragment : BaseFragment<FragmentDogsBinding>(R.layout.fragment_dogs) {

    private val viewModel: DoogsViewModel by viewModels()
    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.dogs_api)
    )

    override fun setUpUi() {
        viewModel.getRandomImage()
        binding.btnUpdate.click {
            viewModel.getRandomImage()
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeFlow(stateFlow = viewModel.getRandomImage, progressBar = binding.progressBar) {
            binding.imageDog.loadImage(it.message)
        }
    }

}