package com.challenge.demodaggerhilt.ui.home

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.challenge.demodaggerhilt.R
import com.challenge.demodaggerhilt.databinding.ActivityHomeBinding
import com.challenge.demodaggerhilt.ui.BaseActivity
import com.challenge.demodaggerhilt.ui.BaseViewModel
//import com.challenge.demodaggerhilt.ui.splash.LoginThreeViewModel
//import com.challenge.demodaggerhilt.usecases.DataKoinUseCase
//import org.koin.android.ext.android.inject
//import org.koin.core.inject

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    //private val appUseCase: LoginThreeViewModel by inject()

    companion object{
        fun newInstance(context: Context) = context.startActivity(Intent(context, HomeActivity::class.java))
    }

    override fun getMainView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
    }

    override fun setUpView() {
        //appUseCase.getList()
    }

    override fun observeViewModel() {

      /*  appUseCase.successListLiveData.observe(this){
            Toast.makeText(this,"it",Toast.LENGTH_LONG).show()
        }*/
    }

    override fun getViewModel(): BaseViewModel? = null

}