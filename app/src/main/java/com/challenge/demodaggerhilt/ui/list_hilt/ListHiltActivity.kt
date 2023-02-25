package com.challenge.demodaggerhilt.ui.list_hilt


import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.challenge.demodaggerhilt.R
import com.challenge.demodaggerhilt.databinding.ActivityListBinding
import com.challenge.demodaggerhilt.ui.BaseActivity
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.ui.adapter.ListDemoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListHiltActivity : BaseActivity() {

    private val viewModel: ListHiltViewModel by viewModels()

    companion object{
        fun newInstance(context: Context) = context.startActivity(Intent(context, ListHiltActivity::class.java))
    }

    private val listDemoAdapter: ListDemoAdapter = ListDemoAdapter()

    private lateinit var binding: ActivityListBinding

    override fun getMainView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.lifecycleOwner = this
    }

    override fun setUpView() {
        binding.rvGeneric.adapter = listDemoAdapter
       viewModel.getList()
    }

    override fun observeViewModel() {
        viewModel.successListLiveData.observe(this){
            listDemoAdapter.list = it
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel


}