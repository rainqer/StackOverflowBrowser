package org.rhinoonabus.stackoverflowbrowser.presentation.application

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(GitHubClientModule::class)])
interface StackOverflowBrowserApplicationComponent
