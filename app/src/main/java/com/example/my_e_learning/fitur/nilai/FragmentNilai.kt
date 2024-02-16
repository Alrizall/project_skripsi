package com.example.my_e_learning.fitur.nilai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.my_e_learning.data.NilaiInformation
import com.example.my_e_learning.databinding.FragmentNilaiBinding
import com.example.my_e_learning.fitur.home.adapter.PemberitahuanAdapter
import com.example.my_e_learning.fitur.materi.FragmentDetailMateriArgs
import com.example.my_e_learning.fitur.nilai.adapter.NilaiAdapter
import com.example.my_e_learning.util.overrideFragmentBackPressed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentNilai : Fragment(), NilaiAdapter.NilaiAdapterListener {
    private val nilaiViewModel: NilaiViewModel by viewModels()
    private var _binding: FragmentNilaiBinding? = null
    private val binding get() = _binding!!
    private val nilaiAdapter: NilaiAdapter by lazy { NilaiAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNilaiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        val data = nilaiViewModel.nilaiInformationProvider()
        binding.tvBackNilai.setOnClickListener {
            findNavController().navigate(FragmentNilaiDirections.actionFragmentNilaiToFragmentHome())
        }
        overrideFragmentBackPressed {
            findNavController().navigate(FragmentNilaiDirections.actionFragmentNilaiToFragmentHome())
        }

        binding.rvNilai.apply {
            adapter = nilaiAdapter
        }
        if (data.isNotEmpty()){
            nilaiAdapter.submitList(data)
            binding.tvNilaiEmpty.visibility =View.GONE
        }else {
            binding.tvNilaiEmpty.visibility =View.VISIBLE
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onclick(data: NilaiInformation) {
    }
}


