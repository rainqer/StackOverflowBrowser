package org.rhinoonabus.stackoverflowbrowser.presentation.application

import android.app.Application

class StackOverflowBrowserApplication: Application() {

    lateinit var component: StackOverflowBrowserApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerStackOverflowBrowserApplicationComponent
                .builder()
                .repositoriesModule(RepositoriesModule())
                .useCasesModule(UseCasesModule())
                .gitHubClientModule(GitHubClientModule())
                .build()
    }
}
