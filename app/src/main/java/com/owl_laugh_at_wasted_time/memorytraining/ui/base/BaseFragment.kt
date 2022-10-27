package com.owl_laugh_at_wasted_time.memorytraining.ui.base

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.preference.PreferenceManager
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Level
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.Operation
import com.owl_laugh_at_wasted_time.memorytraining.ui.activity.MainActivity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


open class BaseFragment(layout: Int) : Fragment(layout) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var dateOfCreation: String
    private var showDialog = true
    lateinit var sharedPreferences: SharedPreferences

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateOfCreation = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(Date())
        sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext())
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
                    showDialog = true
                }
                DialogInterface.BUTTON_NEGATIVE -> {}
                DialogInterface.BUTTON_NEUTRAL -> {
                    actionNB1?.invoke()
                    showDialog = true
                }
            }
        }
        val dialog = android.app.AlertDialog.Builder(context)
            .setCancelable(false)
            .setPositiveButton(R.string.repeat, listener)
            .setNeutralButton(R.string.select_level_difficulty, listener)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        if (showDialog) {
            dialog.show()
            showDialog = false
        }


        dialog?.window?.let {
            val lp = it.attributes
            it.setGravity(Gravity.BOTTOM)
            lp.y = 200
            it.setBackgroundDrawableResource(R.drawable.backgraund_selected)
        }
    }

    fun getLevel(): Level {
        val level =
            sharedPreferences.getString(getString(R.string.difficulty_level_key), "EASY") ?: "EASY"
        return when (level) {
            "EASY" -> {
                Level.EASY
            }
            "NORMAL" -> {
                Level.NORMAL
            }
            "HARD" -> {
                Level.HARD
            }
            else -> {
                Level.EASY
            }
        }
    }

    fun getStringByLevel(): String {
        val level =
            sharedPreferences.getString(getString(R.string.difficulty_level_key), "EASY") ?: "EASY"
        return when (level) {
            "EASY" -> {
               getString(R.string.easy_level)
            }
            "NORMAL" -> {
                getString(R.string.normal_level)
            }
            "HARD" -> {
                getString(R.string.hard_level)
            }
            else -> {
                getString(R.string.easy_level)
            }
        }
    }

    fun getOperation(): Operation {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        val level =
            sharedPreferences.getString(getString(R.string.arithmetic_operation_key), "ADDITION")
                ?: "ADDITION"
        return when (level) {
            "ADDITION" -> {
                Operation.ADDITION
            }
            "SUBTRACTION" -> {
                Operation.SUBTRACTION
            }
            "MULTIPLICATION" -> {
                Operation.MULTIPLICATION
            }
            "DIVISION" -> {
                Operation.DIVISION
            }
            else -> {
                Operation.ADDITION
            }
        }
    }

    fun getStrinBygOperation(): String {
        val level =
            sharedPreferences.getString(getString(R.string.arithmetic_operation_key), "ADDITION")
                ?: "ADDITION"
        return when (level) {
            "ADDITION" -> {
                getString(R.string.addition)
            }
            "SUBTRACTION" -> {
                getString(R.string.subtraction)
            }
            "MULTIPLICATION" -> {
                getString(R.string.multiplication)
            }
            "DIVISION" -> {
                getString(R.string.division)
            }
            else -> {
                getString(R.string.addition)
            }
        }
    }

    companion object {
        const val CURRENT_STATISTICS_COUNTING = "CURRENT_STATISTICS_COUNTING"
        const val CURRENT_STATISTICS_COUNTING_ENDURANCE = "CURRENT_STATISTICS_COUNTING_ENDURANCE"
        const val COUNTING = "STATISTICS_COUNTING"
        const val COUNTING_ENDURANCE = "STATISTICS_COUNTING_ENDURANCE"
        const val DATE_TIME_FORMAT = "dd.MMMM.YYYY HH:mm"
        const val COUNTER_MEMORY = "COUNTER_MEMORY"
        const val STATISTIC_COUNTER_MEMORY = "STATISTIC_COUNTER_MEMORY"
    }

}