package com.example.my_e_learning.util

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.overrideFragmentBackPressed(func: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                func.invoke()
            }
        }
    )
}