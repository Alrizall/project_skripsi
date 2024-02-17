package com.example.my_e_learning.fitur.home

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.my_e_learning.R
import com.example.my_e_learning.data.DashboardInformation
import com.example.my_e_learning.data.DuedateInformation
import com.example.my_e_learning.databinding.FragmentHomeBinding
import com.example.my_e_learning.fitur.home.adapter.DuedateAdapter
import com.example.my_e_learning.fitur.home.adapter.PemberitahuanAdapter
import com.example.my_e_learning.fitur.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class  FragmentHome : Fragment(), PemberitahuanAdapter.PemberitahuanAdapterListener,
    DuedateAdapter.DuedateAdapterListener {
    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val pemberitahuanAdapter: PemberitahuanAdapter by lazy { PemberitahuanAdapter(this) }
    private val duedateAdapter: DuedateAdapter by lazy { DuedateAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.userDataState.onEach {
                    when {
                        it.errorMessage.isNotEmpty() -> {
                            findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToFragmentLogin())
                        }

                        it.user != null -> {
                            Glide.with(binding.ibProfilhome.context).load(R.drawable.pass_foto)
                                .into(binding.ibProfilhome)
                            binding.cvCat1.setOnClickListener {
                                findNavController().navigate(R.id.action_fragmentHome_to_fragmentMateri)
                            }
                            binding.cvCat2.setOnClickListener {
                                findNavController().navigate(R.id.action_fragmentHome_to_fragmentTugas)
                            }
                            binding.cvCat3.setOnClickListener {
                                findNavController().navigate((R.id.action_fragmentHome_to_fragmentQuiz))
                            }
                            binding.cvCat4.setOnClickListener {
                                findNavController().navigate(R.id.action_fragmentHome_to_fragmentNilai)
                            }
                            binding.textView.text = "Hi, ${it.user.getEmail()}"

                            binding.rvPemberitahuan.apply {
                                adapter = pemberitahuanAdapter
                            }
                            pemberitahuanAdapter.submitList(homeViewModel.dashboardInformationProvider())

                            binding.rvPopuler.apply {
                                adapter = duedateAdapter
                            }
                            duedateAdapter.submitList(homeViewModel.duedateInformationProvider())
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

    override fun onclick(data: DashboardInformation) {
        val i = Intent(ACTION_VIEW, Uri.parse("https://www.google.com"))
        i.setPackage("com.android.chrome")
        startActivity(i)
    }

    override fun onclick(data: DuedateInformation) {
        findNavController().navigate(R.id.action_fragmentHome_to_fragmentTugas)
    }

}