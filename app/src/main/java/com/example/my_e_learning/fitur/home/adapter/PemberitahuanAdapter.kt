package com.example.my_e_learning.fitur.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.my_e_learning.data.DashboardInformation
import com.example.my_e_learning.databinding.ItemPemberitahuanBinding

class PemberitahuanAdapter(private val listener: PemberitahuanAdapterListener) :
    ListAdapter<DashboardInformation, ItemPermberitahuanViewHolder>(adapterCallback) {
    companion object {
        val adapterCallback = object : DiffUtil.ItemCallback<DashboardInformation>() {
            override fun areItemsTheSame(
                oldItem: DashboardInformation,
                newItem: DashboardInformation
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DashboardInformation,
                newItem: DashboardInformation
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    interface PemberitahuanAdapterListener {
        fun onclick(data: DashboardInformation)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemPermberitahuanViewHolder {
        return ItemPermberitahuanViewHolder(ItemPemberitahuanBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemPermberitahuanViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener{
            listener.onclick(data)
        }
    }
}