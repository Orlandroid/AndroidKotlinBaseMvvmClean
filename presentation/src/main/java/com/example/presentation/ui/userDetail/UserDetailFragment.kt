package com.example.presentation.ui.userDetail

import com.example.presentation.R
import com.example.presentation.databinding.FragmentUserDetailBinding
import com.example.presentation.base.BaseFragment
import com.example.presentation.extensions.setonBackListener


class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>(R.layout.fragment_user_detail) {

    override fun setUpUi() {
        setonBackListener { navController.popBackStack() }
    }

}