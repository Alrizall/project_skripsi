package com.example.admin.feature.materi

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.admin.data.MateriInformation
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.example.admin.databinding.FragmentMateriBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentMateri : Fragment() {
    private val materiViewModel: MateriViewModel by viewModels()

    private var imageUri: Uri? = null
    private var _binding: FragmentMateriBinding? = null
    private val binding get() = _binding!!
    private val intentGalleryLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val intentResult = result.data?.data
                imageUri = intentResult
                binding.ivEdtImg.visibility = View.VISIBLE
                Glide.with(requireContext()).load(intentResult).into(binding.ivEdtImg)
            } catch (e: Exception) {
                Log.e("tag", "${e.message}")
                Snackbar.make(
                    requireContext(),
                    binding.root,
                    "tidak bisa load image",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMateriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun openGalery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intentGalleryLauncher.launch(intent)
    }

    private fun initView() {
        binding.ivTombolbackMateri.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSaveMateri.setOnClickListener {
            val description1 = binding.edtDescription1.text.toString()
            val description2 = binding.edtDescription2.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    uploadMateri(description1, description2)
                }
            }



        }
        binding.btnEdtImg.setOnClickListener{
            openGalery()
        }
    }

    private suspend fun uploadMateri(description1: String, description2: String) {
        if (imageUri != null) {
            when (val state = materiViewModel.uploadMateriData(
                MateriInformation(
                    description1,
                    description2,
                    imageUri.toString()
                )
            )) {
                is DataSourceVoid.Error -> Toast.makeText(requireContext(),state.errorMessage , Toast.LENGTH_SHORT).show()
                DataSourceVoid.Success -> {
                    binding.edtDescription1.setText("")
                    binding.edtDescription2.setText("")
                    binding.ivEdtImg.visibility = View.GONE
                    imageUri = null
                }
            }

        } else {
            when (val state = materiViewModel.uploadMateriData(
                MateriInformation(
                    description1,
                    description2,
                    null
                )
            )) {
                is DataSourceVoid.Error -> Toast.makeText(requireContext(),state.errorMessage , Toast.LENGTH_SHORT).show()
                DataSourceVoid.Success -> {
                    binding.edtDescription1.setText("")
                    binding.edtDescription2.setText("")
                    binding.ivEdtImg.visibility = View.GONE
                    imageUri = null
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

