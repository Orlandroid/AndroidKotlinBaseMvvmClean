package com.example.presentation.features.bored

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.state.Result
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentBoredBinding
import com.example.presentation.extensions.click
import com.example.presentation.extensions.gone
import com.example.presentation.extensions.observeApiResultFlow
import com.example.presentation.extensions.setTextFromHtml
import com.example.presentation.extensions.shouldShowProgress
import com.example.presentation.extensions.showErrorApi
import com.example.presentation.extensions.showLog
import com.example.presentation.extensions.showProgress
import com.example.presentation.extensions.visible
import com.example.presentation.features.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BoredFragment : BaseFragment<FragmentBoredBinding>(R.layout.fragment_bored) {

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = getString(R.string.the_bored_api)
    )

    private val viewModel: BoredViewModel by viewModels()
    override fun setUpUi() {
        binding.btnUpdate.click {
            viewModel.getActivity()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun observerViewModel() {
        super.observerViewModel()
        /*
        lifecycleScope.launch {
            viewModel.getActivityResponse.collect {
                if (it is Result.Loading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                }
                when (it) {
                    Result.EmptyList -> {}
                    is Result.Error -> {}
                    is Result.ErrorNetwork -> {}
                    Result.Loading -> {

                    }

                    is Result.SocketTimeoutException -> {}
                    is Result.Success -> {
                        it.data.let {
                            binding.tvLink.gone()
                            binding.tvActivity.text = "Activity: ${it.activity}"
                            binding.tvActivityType.text = "Type: ${it.type}"
                            binding.tvParticipants.text = "Participants: ${it.participants}"
                            if (it.link.isNotEmpty()) {
                                val link = "<a href=${it.link}>${it.link}</a>"
                                binding.tvLink.visible()
                                binding.tvLink.setTextFromHtml(link)
                            }

                        }
                    }
                }
            }
        }*/

        observeApiResultFlow(
            viewModel.getActivityResponse,
            onLoading = { binding.progressBar.visible() },
            onFinishLoading = { binding.progressBar.gone() }) {
            binding.tvLink.gone()
            binding.tvActivity.text = "Activity: ${it.activity}"
            binding.tvActivityType.text = "Type: ${it.type}"
            binding.tvParticipants.text = "Participants: ${it.participants}"
            if (it.link.isNotEmpty()) {
                val link = "<a href=${it.link}>${it.link}</a>"
                binding.tvLink.visible()
                binding.tvLink.setTextFromHtml(link)
            }
        }
    }

}