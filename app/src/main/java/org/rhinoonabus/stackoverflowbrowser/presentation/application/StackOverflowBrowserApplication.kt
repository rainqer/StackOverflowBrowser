package org.rhinoonabus.stackoverflowbrowser.presentation.application

import android.app.Application

class StackOverflowBrowserApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerStackOverflowBrowserApplicationComponent
                .builder()
                .repositoriesModule(RepositoriesModule())
                .useCasesModule(UseCasesModule())
                .gitHubClientModule(GitHubClientModule())
                .build()
    }
}
