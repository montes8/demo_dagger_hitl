package com.challenge.demodaggerhilt.ui.splash

import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.challenge.demodaggerhilt.R
import com.challenge.demodaggerhilt.databinding.ActivityLoginBinding
import com.challenge.demodaggerhilt.ui.BaseActivity
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.ui.home.HomeActivity


@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: AppViewModel by viewModels()

    override fun getMainView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
    }

    override fun setUpView() {
        binding.btnLogin.setOnClickListener {
            viewModel.getUser(binding.editEmail.text.toString(),binding.editPassword.text.toString())
        }

    }

    override fun observeViewModel() {
        viewModel.successUserLiveData.observe(this) {
            HomeActivity.newInstance(this)
            finish()
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel

}