package com.example.testinterview.di.module

import android.app.Application
import com.example.testinterview.data.QuestionRepositoryImpl
import com.example.testinterview.data.database.AppDatabase
import com.example.testinterview.data.database.QuestionListDao
import com.example.testinterview.di.annotation.ApplicationScope
import com.example.testinterview.domain.QuestionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindQuestionRepository(impl: QuestionRepositoryImpl): QuestionRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideQuestionListDao(application: Application): QuestionListDao {
            return AppDatabase.getInstance(application).questionListDao()
        }
    }
}