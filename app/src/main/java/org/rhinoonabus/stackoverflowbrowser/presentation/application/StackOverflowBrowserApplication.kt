package org.rhinoonabus.stackoverflowbrowser.presentation.application

import android.app.Application

class StackOverflowBrowserApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerStackOverflowBrowserApplicationComponent
                .builder()
                .gitHubClientModule(GitHubClientModule())
                .build()
    }
}
