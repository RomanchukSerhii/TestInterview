package com.example.testinterview.di.component

import android.app.Application
import com.example.testinterview.di.annotation.ApplicationScope
import com.example.testinterview.di.module.DataModule
import com.example.testinterview.di.module.ViewModelModule
import com.example.testinterview.presentation.view.AddQuestionFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(fragment: AddQuestionFragment)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}