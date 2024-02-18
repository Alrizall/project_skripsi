package com.example.admin.feature.tugas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.admin.data.MateriInformation
import com.example.admin.data.TugasInformation
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.example.admin.databinding.FragmentTugasAdminBinding
import com.example.admin.feature.materi.MateriViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentTugasAdmin : Fragment() {
    private val tugasViewModel: TugasViewModel by viewModels()
    private var _binding: FragmentTugasAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTugasAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.ivTombolbackTugas.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSaveMateri.setOnClickListener{
            val description1 = binding.edtDescription1.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    uploadMateri(description1)
                }
            }
        }
    }

    private suspend fun uploadMateri(description1: String) {
        when (val state = tugasViewModel.uploadTugasData(
            TugasInformation(
                description1
            )
        )) {
            is DataSourceVoid.Error -> Toast.makeText(requireContext(),state.errorMessage , Toast.LENGTH_SHORT).show()
            DataSourceVoid.Success -> {
                binding.edtDescription1.setText("")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}