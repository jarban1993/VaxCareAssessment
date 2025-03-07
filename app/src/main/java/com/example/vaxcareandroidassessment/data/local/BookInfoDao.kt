package com.example.vaxcareandroidassessment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vaxcareandroidassessment.data.remote.dto.BookDto

@Dao
interface BookInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookInfo(infos: BookDto)

    @Query("DELETE FROM bookinfoentity WHERE id IN(:ids)")
    suspend fun deleteBookInfo(ids: Int)

    @Query("SELECT * FROM bookinfoentity WHERE id LIKE '%' || :id || '%'")
    suspend fun getBookInfo(id: Int): BookInfoEntity
}