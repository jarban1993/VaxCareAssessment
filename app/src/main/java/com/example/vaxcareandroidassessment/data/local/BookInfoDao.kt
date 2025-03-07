package com.example.vaxcareandroidassessment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookInfo(book: BookInfoEntity)

    @Query("DELETE FROM bookinfoentity WHERE id IN(:ids)")
    suspend fun deleteBookInfo(ids: List<Int>)

    @Query("SELECT * FROM bookinfoentity WHERE id = :id")
    suspend fun getBookInfo(id: Int): BookInfoEntity?

    @Query("SELECT * FROM bookinfoentity")
    suspend fun getAllBooksInfo(): List<BookInfoEntity>

}