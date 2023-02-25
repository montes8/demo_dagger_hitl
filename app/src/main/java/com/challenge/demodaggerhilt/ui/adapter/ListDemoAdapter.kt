package com.challenge.demodaggerhilt.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.demodaggerhilt.databinding.RowGenericListBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_generic_list.view.*

class ListDemoAdapter:
    RecyclerView.Adapter<ListDemoAdapter.ListAdapterHolder>() {

    var list: List<String> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            RowGenericListBinding.inflate(layoutInflater, parent, false)
        return ListAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapterHolder, position: Int) {
        val model = list[position]
        holder.itemView.textService.text = model
        Picasso.get().load("https://cloudfront-us-east-1.images.arcpublishing.com/radiomitre/ONIZUCDFUFGM5PFDSV7S2LBGVU.jpg")
            .into(holder.itemView.imgPhoto)

    }

    inner class ListAdapterHolder(binding: RowGenericListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = list.size
}
