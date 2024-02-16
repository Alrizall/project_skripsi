package com.example.my_e_learning.fitur.tugas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.my_e_learning.databinding.FragmentDetailTugasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetailTugas : Fragment() {
    private val tugasViewModel : TugasViewModel by viewModels()
    private var _binding: FragmentDetailTugasBinding? = null
    private val binding get() = _binding!!
    private val idTugas : FragmentDetailTugasArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTugasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val data = tugasViewModel.tugasInformationProvider(idTugas.idTugas)
        binding.tvTugas.text = "Tugas ${data.id}"
        if (tugasViewModel.locked(data)){
            binding.edtDetailTugas1.isEnabled = false
            binding.button.isEnabled = false
        } else {
            binding.tvDetailTugas1.text = data.decription
            if (data.image != null){
                binding.imageViewtugas1.visibility = View.VISIBLE
                Glide.with(binding.imageViewtugas1.context).load(data.image).into(binding.imageViewtugas1)
            } else {
                binding.imageViewtugas1.visibility = View.GONE
            }
            binding.button.setOnClickListener{
                tugasViewModel.lockJawaban(data)
                binding.edtDetailTugas1.isEnabled = false
                binding.button.isEnabled = false
            }
            binding.tvDetailTugas1.text = data.decription
        }
        binding.ivBacTugas.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}