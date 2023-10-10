package com.goodaysolutions.waltmartchallenge.common.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goodaysolutions.waltmartchallenge.databinding.LayoutGenericBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GenericBottomSheet @Inject constructor() : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "BottomSheetError"

    }

    private lateinit var binding: LayoutGenericBottomSheetBinding

    var onClickListener: (() -> Unit)? = null

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = LayoutGenericBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSecondAction.setOnClickListener {
            onClickListener?.invoke()
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

}