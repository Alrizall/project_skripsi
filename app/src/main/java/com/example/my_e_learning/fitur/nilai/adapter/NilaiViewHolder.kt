package com.example.my_e_learning.fitur.nilai.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.my_e_learning.data.NilaiInformation
import com.example.my_e_learning.databinding.ItemNilaiBinding

class NilaiViewHolder (private val binding: ItemNilaiBinding) : RecyclerView.ViewHolder (binding.root){
    fun bind (data : NilaiInformation) {
        binding.tvNilai1.text = "${data.title} ${data.value.toString()}"
    }
}