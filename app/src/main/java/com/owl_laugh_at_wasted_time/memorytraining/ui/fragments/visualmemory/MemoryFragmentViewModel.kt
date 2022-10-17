package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.visualmemory

import android.os.CountDownTimer
import androidx.lifecycle.*
import com.owl_laugh_at_wasted_time.memorytraining.R
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.entity.*
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.repository.FieldModelRepository
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases.GenerateQuestionUsecase
import com.owl_laugh_at_wasted_time.memorytraining.domain.verbalcounting.usecases.GetGameSettingsUsecase
import com.owl_laugh_at_wasted_time.memorytraining.domain.visualmemory.entity.Cell
import com.owl_laugh_at_wasted_time.memorytraining.ui.base.uiactions.UiActions
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class MemoryFragmentViewModel @Inject constructor(
   private val repository: FieldModelRepository
) : ViewModel() {

    val getList =repository.getList()

  suspend fun activField(size: Int) {
          repository.activField(size)
    }

   suspend fun notActivField(size: Int){
            repository.notActivField(size)
    }

     fun addItem(item: Cell) {
         viewModelScope.launch {
             repository.addItem(item)
         }
    }

    suspend fun addList(list: List<Cell>) {
       viewModelScope.launch {
           repository.addList(list)
       }
    }

   suspend fun delete() {
       viewModelScope.launch {
           repository.delete()
       }
    }
}