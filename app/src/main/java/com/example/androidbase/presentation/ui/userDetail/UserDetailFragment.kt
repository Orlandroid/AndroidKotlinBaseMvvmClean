package com.example.androidbase.presentation.ui.userDetail

import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentUserDetailBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.setonBackListener


class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>(R.layout.fragment_user_detail) {

    override fun setUpUi() {
        setonBackListener { navController.popBackStack() }
    }


}