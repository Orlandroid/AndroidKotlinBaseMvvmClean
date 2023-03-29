package com.example.androidbase.presentation.ui.users

import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentUsersBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.observeApiResult
import com.example.androidbase.presentation.extensions.setonBackListener
import com.example.domain.entities.remote.User
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(R.layout.fragment_users) {


    private val viewModel: UsersViewModel by viewModels()
    private val userAdapter = UserAdapter { clickOnUser(it) }


    override fun setUpUi() = with(binding) {
        setonBackListener {

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

    private fun clickOnUser(user: User) {
        navController.navigate(UsersFragmentDirections.actionUsersFragmentToUserDetailFragment())
    }

}