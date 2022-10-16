package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.visualmemory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.data.GameImp
import com.owl_laugh_at_wasted_time.memorytraining.databinding.FragmentMemoryBinding
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.Game
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.viewBinding

class MemoryFragment : Fragment(R.layout.fragment_memory) {

    private val binding: FragmentMemoryBinding by viewBinding(FragmentMemoryBinding::bind)
    private lateinit var game: Game

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        game = GameImp(requireArguments().getInt("DIFFICULTY_LEVEL"))

        val adapter = FieldRVAdapter(game.field) {

        }
        val spanCount=if (game.field.size<11){
            game.field.size
        }else{
            game.field.size-5
        }
        binding.recyclerViewField.layoutManager =
            GridLayoutManager(requireContext(), spanCount)
        binding.recyclerViewField.adapter = adapter
    }
}