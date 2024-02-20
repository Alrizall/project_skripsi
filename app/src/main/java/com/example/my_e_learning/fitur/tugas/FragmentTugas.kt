package com.example.my_e_learning.fitur.tugas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.my_e_learning.data.TugasInformation
import com.example.my_e_learning.databinding.FragmentTugasBinding
import com.example.my_e_learning.fitur.tugas.adapter.TugasAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FragmentTugas : Fragment(),TugasAdapter.TugasAdapterListener {
    private val tugasViewModel: TugasViewModel by viewModels()
    private var _binding: FragmentTugasBinding? = null
    private val binding get() = _binding!!
    private val tugasAdapter: TugasAdapter by lazy { TugasAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTugasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                tugasViewModel.tugasDataState.onEach {
                    when {
                        it.errorMessage.isNotEmpty() -> {
                            binding.tvNoTugas.visibility = View.VISIBLE
                            binding.rvTugas.visibility = View.GONE
                        }

                        it.tugas.isNotEmpty() -> {
                            binding.tvNoTugas.visibility = View.GONE
                            binding.rvTugas.visibility = View.VISIBLE
                            binding.rvTugas.apply {
                                adapter = tugasAdapter
                            }
                            tugasAdapter.submitList(it.tugas)
                        }
                    }
                }.launchIn(this)
            }
        }
        binding.imageView2.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onclick(data: TugasInformation) {
        findNavController().navigate(FragmentTugasDirections.actionFragmentTugasToFragmentDetailTugas(data))
    }

}


