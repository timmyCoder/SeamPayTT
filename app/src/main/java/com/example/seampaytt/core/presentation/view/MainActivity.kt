package com.example.seampaytt.core.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.seampaytt.R
import com.example.seampaytt.core.base.BaseActivity
import com.example.seampaytt.core.data.remote.network.Resource
import com.example.seampaytt.core.presentation.viewModel.MainViewModel
import com.example.seampaytt.databinding.ActivityMainBinding
import com.example.seampaytt.utils.gone
import com.example.seampaytt.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: MainViewModel by viewModels()

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setup() {
        initView()
        subscribeViewModel()
    }

    override fun subscribeViewModel() {
        viewModel.exhibit().onLiveDataResult {
            when (it) {
                is Resource.Loading -> binding.progressBar.visible()
                is Resource.Success -> {
                    binding.progressBar.gone()
                    setLog(it.data.toString())
                }
                is Resource.Error -> {
                    binding.progressBar.gone()
                }
            }
        }
    }
    private fun initView() {
        binding.swipeRefresh.setOnRefreshListener(this)
    }


    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}