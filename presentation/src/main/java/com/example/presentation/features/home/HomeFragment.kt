package com.example.presentation.features.home

import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val adapter = HomeAdapter(setMenu(), clickOnMenu = { clickOnMenu(it) })
    override fun setUpUi() {
        binding.recycler.adapter = adapter
    }

    private fun setMenu() =
        listOf(HomeAdapter.Menus.Bored, HomeAdapter.Menus.Countries, HomeAdapter.Menus.Dogs)

    private fun clickOnMenu(menu: HomeAdapter.Menus) {
        when (menu) {
            HomeAdapter.Menus.Bored -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBoredFragment())
            }

            HomeAdapter.Menus.Countries -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCountriesFragment())
            }

            HomeAdapter.Menus.Dogs -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDogsFragment())
            }
        }
    }

}