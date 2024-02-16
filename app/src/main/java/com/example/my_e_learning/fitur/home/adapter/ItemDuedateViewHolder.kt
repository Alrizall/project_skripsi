package com.example.my_e_learning.fitur.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.my_e_learning.data.DuedateInformation
import com.example.my_e_learning.databinding.ItemCourseBinding

class ItemDuedateViewHolder (private val binding: ItemCourseBinding ) : RecyclerView.ViewHolder (binding.root){
    fun bind (data : DuedateInformation) {
        binding.tvMateri1.text = data.description
    }
}