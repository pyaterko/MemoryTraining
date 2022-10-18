package com.owl_laugh_at_wasted_time.memorytraining.ui.base

import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.ui.activity.MainActivity
import kotlinx.coroutines.launch
import javax.inject.Inject


open class BaseFragment(layout: Int) : Fragment(layout) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val component by lazy {
        (activity as MainActivity).component
    }

    fun launchScope(block: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                block.invoke()
            }
        }
    }

    fun displayAConfirmationDialog(
        context: Context,
        actionPB1: (() -> Unit)? = null,
        actionNB1: (() -> Unit)? = null,
    ) {
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    actionPB1?.invoke()
                }
                DialogInterface.BUTTON_NEGATIVE -> {}
                DialogInterface.BUTTON_NEUTRAL -> {
                    actionNB1?.invoke()
                }
            }
        }
        val dialog = android.app.AlertDialog.Builder(context)
            .setCancelable(true)
            .setPositiveButton(R.string.repeat, listener)
            .setNeutralButton(R.string.select_level_difficulty, listener)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        dialog?.window?.let {
            val lp = it.attributes
            it.setGravity(Gravity.BOTTOM)
            lp.y = 200
            it.setBackgroundDrawableResource(R.drawable.backgraund_selected)
        }
    }

}