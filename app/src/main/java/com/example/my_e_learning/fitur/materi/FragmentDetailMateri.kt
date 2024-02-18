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
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.my_e_learning.R
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
    private val materi: FragmentDetailMateriArgs by navArgs()

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

    private fun initView() {

        binding.ivTomboolbackDetail.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvDetailMateri.text =   materi.materiArgs.description1
        binding.tvDetailMateri2.text =   materi.materiArgs.description2
        if (  materi.materiArgs.image != null ){
            Glide.with(binding.ivDetailMateri.context).load(materi.materiArgs.image).into(binding.ivDetailMateri)
        }else {
            Glide.with(binding.ivDetailMateri.context).load(ContextCompat.getDrawable(requireContext(),
                R.drawable.ss_ux)).into(binding.ivDetailMateri)
        }
        binding.tvBackDetaillMateri.text = materi.materiArgs.description1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}