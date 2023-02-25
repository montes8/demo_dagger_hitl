package com.challenge.demodaggerhilt.ui.home

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.challenge.demodaggerhilt.R
import com.challenge.demodaggerhilt.databinding.ActivityHomeBinding
import com.challenge.demodaggerhilt.ui.BaseActivity
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.ui.list_hilt.ListHiltActivity
import com.challenge.demodaggerhilt.ui.list_koin.ListKoinActivity

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    companion object{
        fun newInstance(context: Context) = context.startActivity(Intent(context, HomeActivity::class.java))
    }

    override fun getMainView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
    }

    override fun setUpView() {
        binding.btnTwo.setOnClickListener {
            ListHiltActivity.newInstance(this)
        }

        binding.btnThree.setOnClickListener {
            ListKoinActivity.newInstance(this)
        }
    }

    override fun observeViewModel() {
    }

    override fun getViewModel(): BaseViewModel? = null

}