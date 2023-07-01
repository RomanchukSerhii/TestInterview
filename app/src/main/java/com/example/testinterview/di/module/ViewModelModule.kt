package com.example.testinterview.di.module

import androidx.lifecycle.ViewModel
import com.example.testinterview.di.annotation.ViewModelKey
import com.example.testinterview.presentation.viewmodel.AddQuestionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddQuestionViewModel::class)
    fun bindAddQuestionViewModel(viewModel: AddQuestionViewModel): ViewModel
}