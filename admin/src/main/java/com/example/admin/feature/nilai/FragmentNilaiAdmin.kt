package com.example.admin.feature.nilai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.admin.databinding.FragmentMateriBinding
import com.example.admin.databinding.FragmentNilaiAdminBinding

class FragmentNilaiAdmin : Fragment() {
    private var _binding: FragmentNilaiAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNilaiAdminBinding.inflate(inflater, container, false)
        return binding.root
        initView()
    }
    private fun initView() {
        binding.ivTombolbackNilai.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}