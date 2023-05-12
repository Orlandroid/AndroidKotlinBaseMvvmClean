package com.example.presentation.ui.users

import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.databinding.FragmentUsersBinding
import com.example.presentation.base.BaseFragment
import com.example.presentation.extensions.observeApiResult
import com.example.domain.entities.remote.User
import com.example.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(R.layout.fragment_users) {


    private val viewModel: UsersViewModel by viewModels()
    private val userAdapter = UserAdapter { clickOnUser(it) }


    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true
    )

    override fun configSearchView() = MainActivity.SearchViewConfig(
        showSearchView = true
    )

    override fun setUpUi() = with(binding) {
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