package com.challenge.demodaggerhilt.ui.list_koin


import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.challenge.demodaggerhilt.R
import com.challenge.demodaggerhilt.databinding.ActivityListBinding
import com.challenge.demodaggerhilt.ui.BaseActivity
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.ui.adapter.ListDemoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListKoinActivity : BaseActivity() {

    private val viewModel: ListKoinViewModel by viewModel(clazz = ListKoinViewModel::class)

    companion object{
        fun newInstance(context: Context) = context.startActivity(Intent(context, ListKoinActivity::class.java))
    }

    private val listDemoAdapter: ListDemoAdapter = ListDemoAdapter()

    private lateinit var binding: ActivityListBinding

    override fun getMainView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.lifecycleOwner = this
    }

    override fun setUpView() {
        binding.rvGeneric.adapter = listDemoAdapter
        binding.textTitleList.text = getString(R.string.text_koin)
        viewModel.getList()
    }

    override fun observeViewModel() {
        viewModel.successListLiveData.observe(this){
            listDemoAdapter.list = it
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel



}