package com.example.presentation.features.countries

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentCountryDetailBinding
import com.example.presentation.extensions.observeApiResult
import com.example.presentation.features.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountryDetailFragment :
    BaseFragment<FragmentCountryDetailBinding>(R.layout.fragment_country_detail) {

    private val args: CountryDetailFragmentArgs by navArgs()
    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true, toolbarTitle = getString(R.string.countries)
    )

    private val viewModel: CountriesViewModel by viewModels()
    override fun setUpUi() {
        binding.skeletonImage.showSkeleton()
        binding.skeletonCard.showSkeleton()
        viewModel.getCountryByName(args.countryName)
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.country, shouldCloseTheViewOnApiError = true) {
            it[0].let { myCountryResponse ->
                Glide.with(this).load(myCountryResponse.flags.png).into(binding.imageView)
                binding.tvPais.text = myCountryResponse.name.official
                binding.tvCapital.text = myCountryResponse.capital[0]
                binding.tvMoneda.text = myCountryResponse.population.toString()
                binding.tvCodigoTelefono.text = myCountryResponse.startOfWeek
                binding.skeletonImage.showOriginal()
                binding.skeletonCard.showOriginal()
            }
        }
    }

}