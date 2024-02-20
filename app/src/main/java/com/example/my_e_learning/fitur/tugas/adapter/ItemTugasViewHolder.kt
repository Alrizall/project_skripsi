package com.example.my_e_learning.fitur.tugas.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.my_e_learning.data.TugasInformation
import com.example.my_e_learning.databinding.ItemTugasBinding

class ItemTugasViewHolder(private val binding: ItemTugasBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind (data : TugasInformation){
            binding.tvTugas1.text = data.description
        }
}