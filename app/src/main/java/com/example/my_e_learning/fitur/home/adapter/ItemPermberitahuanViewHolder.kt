package com.example.my_e_learning.fitur.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.my_e_learning.data.DashboardInformation
import com.example.my_e_learning.databinding.ItemPemberitahuanBinding

class ItemPermberitahuanViewHolder (private val binding: ItemPemberitahuanBinding ) : RecyclerView.ViewHolder (binding.root){
    fun bind (data : DashboardInformation) {
        binding.tvInformation.text = data.title
        Glide.with(binding.uvUx1.context).load(data.image).into(binding.uvUx1) //load image
    }
}