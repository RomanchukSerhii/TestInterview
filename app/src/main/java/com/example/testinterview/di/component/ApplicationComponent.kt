package com.example.testinterview.di.component

import android.app.Application
import com.example.testinterview.di.annotation.ApplicationScope
import com.example.testinterview.di.module.DataModule
import com.example.testinterview.di.module.ViewModelModule
import com.example.testinterview.presentation.view.dialogs.MoreTopicDialogFragment
import com.example.testinterview.presentation.view.screens.AddQuestionFragment
import com.example.testinterview.presentation.view.screens.InterviewFragment
import com.example.testinterview.presentation.view.screens.ListTopicFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(fragment: AddQuestionFragment)

    fun inject(fragment: MoreTopicDialogFragment)

    fun inject(fragment: ListTopicFragment)

    fun inject(fragment: InterviewFragment)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}