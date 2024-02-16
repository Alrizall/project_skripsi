package com.example.admin.feature.materi

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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.admin.databinding.FragmentMateriBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMateri : Fragment() {
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
            Toast.makeText(requireContext(),"Update Sukses",Toast.LENGTH_SHORT).show()
        }
        binding.btnEdtImg.setOnClickListener{
            Toast.makeText(requireContext(),"Sukses Load Image",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

