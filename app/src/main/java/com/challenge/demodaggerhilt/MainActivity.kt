package com.challenge.demodaggerhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.challenge.demodaggerhilt.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel:AppViewModel by viewModels()

    override fun getMainView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    override fun setUpView() {
        viewModel.getUser()
    }

    override fun observeViewModel() {
        viewModel.successUserLiveData.observe(this,{
            Log.d("TAGUSER","$it")
        })
    }

    override fun getViewModel(): BaseViewModel = viewModel

}