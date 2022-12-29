package com.example.androidbase.presentation.ui.users

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbase.R
import com.example.androidbase.domain.entities.remote.User
import com.example.androidbase.databinding.FragmentUsersBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.observeApiResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(R.layout.fragment_users) {


    private val viewModel: UsersViewModel by viewModels()
    private val userAdapter by lazy {
        UserAdapter()
    }

    override fun setUpUi() = with(binding) {
        toolbarLayout.toolbarBack.click {
            findNavController().popBackStack()
        }
        viewModel.getUsers()
        recycler.adapter = userAdapter
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.userResponse) {
            val users = arrayListOf<User>()
            it.data.forEach { user ->
                users.add(user)
            }
            userAdapter.setData(users)
        }
    }

}