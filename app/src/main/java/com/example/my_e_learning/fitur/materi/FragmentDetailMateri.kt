package com.example.my_e_learning.fitur.materi

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.databinding.FragmentDetailMateriBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetailMateri : Fragment() {
    private var imageUri: Uri? = null
    private val materiViewModel: MateriViewModel by viewModels()
    private var _binding: FragmentDetailMateriBinding? = null
    private val binding get() = _binding!!
    private val idMateri: FragmentDetailMateriArgs by navArgs()
    private val intentGalleryLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val intentResult = result.data?.data
                imageUri = intentResult
                Glide.with(requireContext()).load(intentResult).into(binding.ivDetailMateri)
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
        _binding = FragmentDetailMateriBinding.inflate(inflater, container, false)
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
        val isAdmin = materiViewModel.isAdmin()
        val data = materiViewModel.materiViewModelProvider(idMateri.idMateri)
        if (isAdmin) {
            binding.btnEdtImg.setOnClickListener {
                openGalery()
            }
            binding.btnEdtImg.visibility = View.VISIBLE
            binding.edtDescription1.visibility = View.VISIBLE
            binding.edtDescription2.visibility = View.VISIBLE
            binding.btnSaveMateri.visibility = View.VISIBLE
            binding.edtDescription1.setText(data.decription1)
            binding.edtDescription2.setText(data.decription2)
            binding.btnSaveMateri.setOnClickListener {
                materiViewModel.updateQuestion(
                    MateriInformation(
                        id = data.id,
                        decription1 = binding.edtDescription1.text.toString(),
                        decription2 = binding.edtDescription2.text.toString(),
                        image = data.image,
                        title = data.title,
                        uri = if (imageUri !=null) imageUri.toString() else null
                    )
                )
                binding.tvDetailMateri.text = binding.edtDescription1.text.toString()
                binding.tvDetailMateri2.text = binding.edtDescription2.text.toString()
                findNavController().popBackStack()
            }

        } else {
            binding.btnEdtImg.visibility = View.GONE
            binding.edtDescription1.visibility = View.GONE
            binding.edtDescription2.visibility = View.GONE
            binding.btnSaveMateri.visibility = View.GONE
        }

        binding.ivTomboolbackDetail.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvDetailMateri.text = data.decription1
        binding.tvDetailMateri2.text = data.decription2
        if (data.uri != null ){
            Glide.with(binding.ivDetailMateri.context).load(Uri.parse(data.uri)).into(binding.ivDetailMateri)
        }else {
            Glide.with(binding.ivDetailMateri.context).load(data.image).into(binding.ivDetailMateri)
        }
        binding.tvBackDetaillMateri.text = data.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}