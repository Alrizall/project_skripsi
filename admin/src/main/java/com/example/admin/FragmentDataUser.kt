package com.example.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.admin.data.UserInformation
import com.example.admin.data.dataAdmin.DataSourceHelper
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.example.admin.databinding.FragmentDataUserBinding
import com.example.admin.databinding.FragmentTugasAdminBinding
import com.example.admin.feature.home.HomeAdminViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FragmentDataUser : Fragment() {
    private val dataUserViewModel: ViewModelDataUser by viewModels()
    private var _binding: FragmentDataUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.ivBackInputData.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.button.setOnClickListener {
            val email = binding.editText.text.toString()
            val password = binding.editText2.text.toString()
            val alamat = binding.editText3.text.toString()
            val no_telepon = binding.editText4.text.toString()
            when {
                email.isEmpty() -> {
                    Toast.makeText(requireContext(), "masukan email", Toast.LENGTH_SHORT).show()
                }

                password.isEmpty() -> {
                    Toast.makeText(requireContext(), "masukan password", Toast.LENGTH_SHORT).show()
                }

                alamat.isEmpty() -> {
                    Toast.makeText(requireContext(), "masukan alamat", Toast.LENGTH_SHORT).show()
                }

                no_telepon.isEmpty() -> {
                    Toast.makeText(requireContext(), "masukan no telepon", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            when (
                                val register = dataUserViewModel.signUp(email, password)
                            ) {
                                is DataSourceHelper.Error -> {
                                    Log.e("TAG", "initView: register gagal : ${register.errorMessage}", )
                                }

                                is DataSourceHelper.Success -> {
                                    when (
                                        val upload = dataUserViewModel.uploadFirebaseUserData(
                                            UserInformation(
                                                id = register.data.getUid()!!,
                                                username = email,
                                                password = password,
                                                alamat = alamat,
                                                no_telepon = no_telepon.toInt()
                                            )
                                        )
                                    ){
                                        is DataSourceVoid.Error ->
                                            Log.e("TAG", "initView: upload gagal : ${upload.errorMessage}", )
                                        DataSourceVoid.Success -> Toast.makeText(
                                            requireContext(),
                                            "upload berhasil",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Input Berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}