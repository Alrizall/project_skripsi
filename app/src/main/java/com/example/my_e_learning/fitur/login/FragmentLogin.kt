package com.example.my_e_learning.fitur.login

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
import com.example.my_e_learning.data.UserInformation
import com.example.my_e_learning.data.dataUser.DataSourceHelper
import com.example.my_e_learning.data.dataUser.DataSourceVoid
import com.example.my_e_learning.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentLogin : Fragment() {
    private val viewModel : LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            when {
                username.isEmpty() -> {
                    Toast.makeText(it.context, "masukan username", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(it.context,"masukan password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            when (
                                viewModel.signIn(username,password)
                            ) {
                                is DataSourceVoid.Error ->
                                    Toast.makeText(it.context,"gagal login", Toast.LENGTH_SHORT).show()
                                is DataSourceVoid.Success ->
                                    findNavController().navigate(FragmentLoginDirections.actionFragmentLoginToFragmentHome())
                            }
                        }
                    }
                }
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}