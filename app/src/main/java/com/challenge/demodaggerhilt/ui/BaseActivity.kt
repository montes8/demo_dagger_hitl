package com.challenge.demodaggerhilt.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getMainView()
    abstract fun setUpView()
    abstract fun observeViewModel()
    abstract fun getViewModel(): BaseViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMainView()
        setUpView()
        observeMainViewModel()
    }


    private fun observeMainViewModel() {
        this.observeViewModel()
        getViewModel()?.let { viewModel ->
            viewModel.errorLiveData.observe(this) {
                Toast.makeText(this, "los datos son oncorrectos", Toast.LENGTH_LONG).show()
            }

            viewModel.progressLiveData.observe(this, {})

        }
    }


}