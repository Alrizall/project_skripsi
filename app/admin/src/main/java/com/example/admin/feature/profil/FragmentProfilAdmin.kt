package com.example.admin.feature.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.admin.R
import com.example.admin.databinding.FragmentProfilAdminBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentProfilAdmin : Fragment() {
    private val viewModel : ViewModelProfil by viewModels()
    private var _binding : FragmentProfilAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView ()
    }

    private fun initView (){
        if (viewModel.getUserName().isNotEmpty()){
            binding.btnLogout.setOnClickListener{
                viewModel.deleteUserName()

                findNavController().navigate(FragmentProfilAdminDirections.actionFragmentProfilAdminToFragmentLoginAdmin())
            }
            Glide.with(binding.imageViewProfil.context).load(R.drawable.pass_foto).into(binding.imageViewProfil)
            binding.tvUsername.text = viewModel.getUserName()
            binding.tvNamaProfil.text = viewModel.getUserName()
            binding.tvAlamat.text = "Jalan Villa Regency 1, Blok OB 8 no.29"
            binding.tvJurusan.text = "Teknik Informatika"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}