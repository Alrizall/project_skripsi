package com.example.my_e_learning.fitur.profil

import android.os.Bundle
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
import com.bumptech.glide.Glide
import com.example.my_e_learning.R
import com.example.my_e_learning.databinding.FragmentProfilBinding
import com.example.my_e_learning.fitur.home.FragmentHomeDirections
import com.example.my_e_learning.fitur.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentProfil : Fragment() {
    private val viewModel : LoginViewModel by viewModels()
    private var _binding : FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView ()
        observeData()
    }

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userDataState.onEach {
                    when {
                        it.errorMessage.isNotEmpty() -> {

                        }

                        it.user != null -> {
                            binding.btnLogout.setOnClickListener{
                                viewModel.initLogout {
                                    findNavController().navigate(FragmentProfilDirections.actionFragmentProfilToFragmentLogin())
                                }
                            }
                            val data = it.user.getUid()
                            if ( !data.isNullOrEmpty()){
                                viewModel.getDetailUser(data)
                            }else {
                                Toast.makeText(requireContext(),"uid null",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }.launchIn(this)
            }
        }
    }

    fun observeData (){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profilState.onEach {
                    when {
                        it.errorMessage.isNotEmpty() -> {
                        Toast.makeText(requireContext(),it.errorMessage,Toast.LENGTH_SHORT).show()
                        }

                        it.user != null -> {
                            Glide.with(binding.imageViewProfil.context).load(R.drawable.pass_foto).into(binding.imageViewProfil)
                            binding.tvUsername.text = it.user.username
                            binding.tvNis.text = (1000  .. 9999).random().toString()
                            binding.tvNamaProfil.text = it.user.username
                            binding.tvAlamat.text = it.user.alamat
                            binding.tvJurusan.text = "Teknik Informatika"
                            binding.tvNoTlp.text = it.user.no_telepon.toString()
                        }
                    }
                }.launchIn(this)

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}