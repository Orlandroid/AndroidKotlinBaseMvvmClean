package com.example.presentation.features.countries

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.countries.CountryResponse
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentCountriesBinding
import com.example.presentation.extensions.observeApiResult
import com.example.presentation.features.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountriesFragment : BaseFragment<FragmentCountriesBinding>(R.layout.fragment_countries) {

    override fun configureToolbar() =
        MainActivity.ToolbarConfiguration(
            showToolbar = true,
            toolbarTitle = getString(R.string.countries)
        )

    private val adapter = CountriesAdapter { clickOnCountry(it) }
    private val viewModel: CountriesViewModel by viewModels()
    override fun setUpUi() {
        viewModel.getCountries()
        binding.recycler.adapter = adapter
    }

    private fun clickOnCountry(country: CountryResponse) {
        findNavController().navigate(
            CountriesFragmentDirections.actionCountriesFragmentToCountryDetailFragment(
                country.name.common
            )
        )
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.countries) {
            adapter.submitList(it)
        }
    }

}