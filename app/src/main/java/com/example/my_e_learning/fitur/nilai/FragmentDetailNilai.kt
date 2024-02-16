//package com.example.my_e_learning.fitur.nilai
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.navArgs
//import com.example.my_e_learning.databinding.FragmentDetailMateriBinding
//import com.example.my_e_learning.databinding.FragmentNilaiBinding
//import com.example.my_e_learning.fitur.materi.FragmentDetailMateriArgs
//import com.example.my_e_learning.fitur.materi.MateriViewModel
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class FragmentDetailNilai : Fragment() {
//    private val nilaiViewModel: NilaiViewModel by viewModels()
//    private var _binding: FragmentNilaiBinding? = null
//    private val binding get() = _binding!!
//    private val idNilai: FragmentDetailMateriArgs by navArgs()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentNilaiBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initView()
//    }
//
//    private fun initView() {
//        val data = nilaiViewModel.nilaiInformationProvider(idNilai.)
//        binding.tvBackNilai.setOnClickListener {
//            findNavController().popBackStack()
//
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}