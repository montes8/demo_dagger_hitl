package com.challenge.demodaggerhilt.ui.splash

import android.annotation.SuppressLint
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.challenge.demodaggerhilt.R
import com.challenge.demodaggerhilt.databinding.ActivitySplashBinding
import com.challenge.demodaggerhilt.ui.AppViewModel
import com.challenge.demodaggerhilt.ui.BaseActivity
import com.challenge.demodaggerhilt.ui.BaseViewModel

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    private val viewModel: AppViewModel by viewModels()

    override fun getMainView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this
    }

    override fun setUpView() {
        binding.btnLogin.setOnClickListener {
            viewModel.getUser(binding.editEmail.text.toString(),binding.editPassword.text.toString())
        }

    }

    override fun observeViewModel() {
        viewModel.successUserLiveData.observe(this,{
            Log.d("TAGUSER","$it")
        })
    }

    override fun getViewModel(): BaseViewModel = viewModel

}