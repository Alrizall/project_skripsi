package com.example.my_e_learning.fitur.tugas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.my_e_learning.databinding.FragmentDetailTugasBinding
import com.example.my_e_learning.fitur.login.LoginViewModel
import com.example.my_e_learning.fitur.profil.FragmentProfilDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentDetailTugas : Fragment() {
    private val tugasViewModel : TugasViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()
    private var _binding: FragmentDetailTugasBinding? = null
    private val binding get() = _binding!!
    private val tugas : FragmentDetailTugasArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTugasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.userDataState.onEach { user ->
                    when {
                        user.errorMessage.isNotEmpty() -> {

                        }

                        user.user != null -> {

                            val data = user.user.getUid()
                            if ( !data.isNullOrEmpty()){
                                binding.button.isEnabled = !tugasViewModel.isAnswerLock(tugas.tugasArgs, data)
                                binding.button.setOnClickListener{
                                    tugasViewModel.saveLockQuestion(tugas.tugasArgs, data)
                                    findNavController().popBackStack()
                                }
                            }else {
                                Toast.makeText(requireContext(),"uid null", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }.launchIn(this)
            }
        }

        binding.ivBacTugas.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.tvDetailTugas1.text = tugas.tugasArgs.description



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}