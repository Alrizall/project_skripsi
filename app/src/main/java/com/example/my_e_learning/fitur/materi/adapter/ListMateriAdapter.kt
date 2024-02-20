package com.example.my_e_learning.fitur.materi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.databinding.ItemMateriBinding

class ListMateriAdapter(private val listener: ListMateriAdapter.ListMateriAdapterListener) :
    ListAdapter<MateriInformation, ItemListMateriViewHolder>(adapterCallback) {
    companion object {
        val adapterCallback = object : DiffUtil.ItemCallback<MateriInformation>() {
            override fun areItemsTheSame(
                oldItem: MateriInformation,
                newItem: MateriInformation
            ): Boolean {
                return oldItem.description1 == newItem.description1
            }

            override fun areContentsTheSame(
                oldItem: MateriInformation,
                newItem: MateriInformation
            ): Boolean {
                return oldItem.description1 == newItem.description1
            }
        }
    }

    interface ListMateriAdapterListener {
        fun onclick(data: MateriInformation)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemListMateriViewHolder {
        return ItemListMateriViewHolder(
            ItemMateriBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemListMateriViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onclick(data)
        }
    }
}