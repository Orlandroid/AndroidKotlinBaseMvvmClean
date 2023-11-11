package com.example.presentation.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.R
import com.example.presentation.databinding.FragmentUsersBinding
import com.example.presentation.base.BaseFragment
import com.example.presentation.extensions.observeApiResult
import com.example.domain.entities.remote.User
import com.example.presentation.extensions.showLog
import com.example.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(R.layout.fragment_users) {


    private val viewModel: UsersViewModel by viewModels()
    private val userAdapter = UserAdapter { clickOnUser(it) }


    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        showBackArrow = false
    )

    override fun configSearchView() = MainActivity.SearchViewConfig(
        showSearchView = true
    )

    override fun setUpUi() = with(binding) {
        recycler.adapter = userAdapter
        swipe.setOnRefreshListener {
            userAdapter.setData(emptyList())
            swipe.isRefreshing = false
            viewModel.getUsers()
        }
    }

    override fun onStart() {
        super.onStart()
        showLog("onStart()")
    }

    override fun onResume() {
        super.onResume()
        showLog("onResume()")
    }

    override fun onPause() {
        super.onPause()
        showLog("onPause()")
    }

    override fun onStop() {
        super.onStop()
        showLog("onStop()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLog("onCreate(savedInstanceState: Bundle?)")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        showLog("onViewCreated(view: View, savedInstanceState: Bundle?)")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        showLog("onViewStateRestored(savedInstanceState: Bundle?)")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showLog("onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        showLog("onSaveInstanceState(outState: Bundle)")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showLog("onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("onDestroy()")
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.userResponse) {
            userAdapter.setData(it)
        }
    }

    private fun clickOnUser(user: User) {
        navController.navigate(UsersFragmentDirections.actionUsersFragmentToUserDetailFragment())
    }

}