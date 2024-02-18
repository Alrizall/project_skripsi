package com.example.my_e_learning.fitur.materi

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
import com.bumptech.glide.Glide
import com.example.my_e_learning.R
import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.databinding.FragmentMateriBinding
import com.example.my_e_learning.fitur.home.FragmentHomeDirections
import com.example.my_e_learning.fitur.home.adapter.PemberitahuanAdapter
import com.example.my_e_learning.fitur.materi.adapter.ListMateriAdapter
import com.example.my_e_learning.fitur.tugas.FragmentTugasDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FragmentMateri : Fragment(), ListMateriAdapter.ListMateriAdapterListener {
    private val materiViewModel : MateriViewModel by viewModels()

    private var _binding: FragmentMateriBinding? = null
    private val binding get() = _binding!!
    private val materiAdapter: ListMateriAdapter by lazy { ListMateriAdapter(this) }

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

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                materiViewModel.materiDataSate.onEach {
                    when {
                        it.errorMessage.isNotEmpty() -> {
                            binding.tvNoInput.visibility = View.VISIBLE
                            binding.rvMateriPmblj.visibility = View.GONE
                        }
                        it.materi.isNotEmpty() -> {
                            binding.tvNoInput.visibility = View.GONE
                            binding.rvMateriPmblj.visibility = View.VISIBLE
                            binding.rvMateriPmblj.apply {
                                adapter = materiAdapter
                            }
                            materiAdapter.submitList(it.materi)
                    }
                }
            }.launchIn(this)
        }
    }
        binding.tvBackMateri.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onclick(data: MateriInformation) {
        findNavController().navigate(FragmentMateriDirections.actionFragmentMateriToFragmentDetailMateri(data))
    }
}


