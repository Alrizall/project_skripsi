package com.example.my_e_learning.fitur.tugas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.my_e_learning.R
import com.example.my_e_learning.data.TugasInformation
import com.example.my_e_learning.databinding.FragmentMateriBinding
import com.example.my_e_learning.databinding.FragmentTugasBinding

class FragmentTugas : Fragment() {

    private var _binding: FragmentTugasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTugasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.imageView2.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.cvTugas1.setOnClickListener{
            findNavController().navigate(FragmentTugasDirections.actionFragmentTugasToFragmentDetailTugas(1))
        }
        binding.cvTugas2.setOnClickListener{
            findNavController().navigate(FragmentTugasDirections.actionFragmentTugasToFragmentDetailTugas(2))
        }
        binding.cvTugas3.setOnClickListener{
            findNavController().navigate(FragmentTugasDirections.actionFragmentTugasToFragmentDetailTugas(3))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


