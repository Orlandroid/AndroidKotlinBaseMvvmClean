package com.example.presentation.features.countries

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.countries.CountryResponse
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentCountriesBinding
import com.example.presentation.extensions.observeFlow
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
        //viewModel.getCountries()
        binding.recycler.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCountries()
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
        observeFlow(stateFlow = viewModel.countries, progressBar = binding.progressBar) {
            adapter.submitList(it)
        }
    }

}