package com.nirbhay.vitalvault01.data



import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ReportEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao
}