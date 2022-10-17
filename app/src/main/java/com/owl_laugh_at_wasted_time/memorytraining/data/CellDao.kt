package com.owl_laugh_at_wasted_time.memorytraining.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CellDao {
    @Query("SELECT * FROM CellDbModelTable")
    fun getList(): LiveData<List<CellDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: CellDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addList(listVerbs: List<CellDbModel>)

    @Query("DELETE FROM CellDbModelTable")
    suspend fun delete()
}