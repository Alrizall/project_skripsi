package com.example.my_e_learning.fitur.materi.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.my_e_learning.data.DuedateInformation
import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.databinding.ItemCourseBinding
import com.example.my_e_learning.databinding.ItemMateriBinding

class ItemListMateriViewHolder (private val binding: ItemMateriBinding) : RecyclerView.ViewHolder (binding.root){
    fun bind (data : MateriInformation) {
        binding.tvMateri1.text = data.description1
    }
}