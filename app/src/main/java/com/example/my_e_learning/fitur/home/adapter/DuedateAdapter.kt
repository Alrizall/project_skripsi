package com.example.my_e_learning.fitur.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.my_e_learning.data.DuedateInformation
import com.example.my_e_learning.databinding.ItemCourseBinding
import com.example.my_e_learning.databinding.ItemPemberitahuanBinding

class DuedateAdapter(private val listener: DuedateAdapterListener) :
    ListAdapter<DuedateInformation, ItemDuedateViewHolder>(adapterCallback) {
    companion object {
        val adapterCallback = object : DiffUtil.ItemCallback<DuedateInformation>() {
            override fun areItemsTheSame(
                oldItem: DuedateInformation,
                newItem: DuedateInformation
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DuedateInformation,
                newItem: DuedateInformation
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
    interface DuedateAdapterListener {
        fun onclick(data: DuedateInformation)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemDuedateViewHolder {
        return ItemDuedateViewHolder(ItemCourseBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemDuedateViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener{
            listener.onclick(data)
        }
    }

}