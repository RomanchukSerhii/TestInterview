package com.example.testinterview.di.module

import androidx.lifecycle.ViewModel
import com.example.testinterview.di.annotation.ViewModelKey
import com.example.testinterview.presentation.view.screens.InterviewFragment
import com.example.testinterview.presentation.viewmodel.AddQuestionViewModel
import com.example.testinterview.presentation.viewmodel.InterviewViewModel
import com.example.testinterview.presentation.viewmodel.ListTopicViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddQuestionViewModel::class)
    fun bindAddQuestionViewModel(viewModel: AddQuestionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListTopicViewModel::class)
    fun bindListTopicViewModel(viewModel: ListTopicViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InterviewViewModel::class)
    fun bindInterviewViewModel(viewModel: InterviewViewModel):ViewModel
}