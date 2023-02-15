package com.example.androidbase.presentation.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.androidbase.presentation.extensions.hideProgress

abstract class BaseFragment<ViewBinding : ViewDataBinding>(@LayoutRes protected val contentLayoutId: Int) :
    Fragment() {


    private var _binding: ViewBinding? = null

    protected val binding: ViewBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        return binding.root
    }

    protected abstract fun setUpUi()

    open fun observerViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observerViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        hideProgress()
    }
}