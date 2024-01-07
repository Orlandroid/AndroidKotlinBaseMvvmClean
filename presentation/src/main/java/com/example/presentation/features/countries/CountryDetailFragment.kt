package com.example.presentation.features.countries

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentCountryDetailBinding
import com.example.presentation.extensions.observeFlow
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
        observeFlow(
            stateFlow = viewModel.country,
            shouldCloseTheViewOnApiError = true,
            progressBar = binding.progressBar
        ) {
            it[0].let { myCountryResponse ->
                Glide.with(this).load(myCountryResponse.flags.png).into(binding.imageView)
                with(binding) {
                    tvPais.text = myCountryResponse.name.official
                    if (myCountryResponse.capital.isNotEmpty()) {
                        tvCapital.text = myCountryResponse.capital[0]
                    }
                    tvMoneda.text = myCountryResponse.population.toString()
                    tvCodigoTelefono.text = myCountryResponse.startOfWeek
                    skeletonImage.showOriginal()
                    skeletonCard.showOriginal()
                    tvMyCurrency.text =
                        myCountryResponse.currencies?.let {
                            it.toNonEmptyCurrencyNamesList()[0]
                        }
                    tvLanguaje.text = myCountryResponse.languages?.let {
                        it.toNonEmptyList()[0]
                    }
                }
            }
        }
    }

}