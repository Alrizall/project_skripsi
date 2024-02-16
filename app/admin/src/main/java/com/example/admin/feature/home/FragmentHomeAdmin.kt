package com.example.admin.feature.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.admin.R
import com.example.admin.databinding.FragmentHomeAdminBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHomeAdmin : Fragment() {
    private val homeViewModel: HomeAdminViewModel by viewModels()
    private var _binding: FragmentHomeAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        if (homeViewModel.getUserName().isEmpty()) {
            findNavController().navigate(FragmentHomeAdminDirections.actionFragmentHomeAdminToFragmentLoginAdmin())
        } else {
            binding.cvCat1.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentHomeAdmin_to_fragmentMateriAdmin)
            }
            binding.cvCat2.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentHomeAdmin_to_fragmentTugasAdmin)
            }
            binding.cvCat3.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentHomeAdmin_to_fragmentDataUser)
            }
            binding.cvCat4.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentHomeAdmin_to_fragmentNilaiAdmin)
            }

            binding.textView.text = "Hi, ${homeViewModel.getUserName()}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


