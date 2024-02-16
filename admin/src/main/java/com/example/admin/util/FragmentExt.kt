package com.example.admin.util

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