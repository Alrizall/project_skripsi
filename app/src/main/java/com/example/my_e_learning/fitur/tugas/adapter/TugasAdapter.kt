package com.example.my_e_learning.fitur.tugas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.my_e_learning.data.TugasInformation
import com.example.my_e_learning.databinding.ItemTugasBinding
import com.example.my_e_learning.fitur.tugas.FragmentTugas

class TugasAdapter(private val listener: TugasAdapter.TugasAdapterListener) :
    ListAdapter<TugasInformation, ItemTugasViewHolder>(adapterCallback) {
    companion object {
        val adapterCallback = object : DiffUtil.ItemCallback<TugasInformation>() {
            override fun areItemsTheSame(
                oldItem: TugasInformation,
                newItem: TugasInformation
            ): Boolean {
                return oldItem.description == newItem.description
            }

            override fun areContentsTheSame(
                oldItem: TugasInformation,
                newItem: TugasInformation
            ): Boolean {
                return oldItem.description == newItem.description
            }
        }
    }

    interface TugasAdapterListener {
        fun onclick(data: TugasInformation)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemTugasViewHolder {
        return ItemTugasViewHolder(
            ItemTugasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemTugasViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onclick(data)
        }
    }
}