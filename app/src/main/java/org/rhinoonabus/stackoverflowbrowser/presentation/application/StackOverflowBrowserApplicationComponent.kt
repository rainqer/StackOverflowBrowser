package org.rhinoonabus.stackoverflowbrowser.presentation.application

import dagger.Component
import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesWithPhraseUseCase
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoriesModule::class, UseCasesModule::class, GitHubClientModule::class])
interface StackOverflowBrowserApplicationComponent {

    fun providesSearchForRepositoriesWithPhraseUseCase(): SearchForRepositoriesWithPhraseUseCase
}
