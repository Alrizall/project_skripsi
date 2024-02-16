package com.example.my_e_learning.fitur.nilai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.my_e_learning.data.DuedateInformation
import com.example.my_e_learning.data.NilaiInformation
import com.example.my_e_learning.databinding.ItemCourseBinding
import com.example.my_e_learning.databinding.ItemNilaiBinding
import com.example.my_e_learning.fitur.home.adapter.DuedateAdapter
import com.example.my_e_learning.fitur.home.adapter.ItemDuedateViewHolder

class NilaiAdapter(private val listener: NilaiAdapter.NilaiAdapterListener) :
    ListAdapter<NilaiInformation, NilaiViewHolder>(NilaiAdapter.adapterCallback) {
    companion object {
        val adapterCallback = object : DiffUtil.ItemCallback<NilaiInformation>() {
            override fun areItemsTheSame(
                oldItem: NilaiInformation,
                newItem: NilaiInformation
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NilaiInformation,
                newItem: NilaiInformation
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
    interface NilaiAdapterListener {
        fun onclick(data: NilaiInformation)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NilaiViewHolder {
        return NilaiViewHolder(
            ItemNilaiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NilaiViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            listener.onclick(data)
        }
    }
}
