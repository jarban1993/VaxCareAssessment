package com.example.vaxcareandroidassessment.data.local

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BookInfoDb: RoomDatabase() {
    abstract val dao: BookInfoDao
}