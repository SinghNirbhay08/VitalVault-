package com.nirbhay.vitalvault01.di



import android.content.Context
import androidx.room.Room
import com.nirbhay.vitalvault01.data.AppDatabase
import com.nirbhay.vitalvault01.data.ReportDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "vitalvault_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideReportDao(database: AppDatabase): ReportDao {
        return database.reportDao()
    }
}