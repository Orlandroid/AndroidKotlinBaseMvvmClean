package com.example.androidbase.presentation.ui.home

import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentHomeBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.click


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    override fun setUpUi() = with(binding) {
        button.click {
            val action = HomeFragmentDirections.actionHomeFragmentToUsersFragment()
            findNavController().navigate(action)
        }
    }

}