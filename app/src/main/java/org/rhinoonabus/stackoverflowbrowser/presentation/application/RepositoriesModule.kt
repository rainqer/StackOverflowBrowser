package org.rhinoonabus.stackoverflowbrowser.presentation.application

import dagger.Module
import dagger.Provides
import org.rhinoonabus.stackoverflowbrowser.domain.SourceCodeManagementRepository
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubApiClient
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubSourceCodeManagementRepository

@Module
class RepositoriesModule {

    @Provides
    fun providesSourceCodeManagementRepository(gitHubClient: GitHubApiClient): SourceCodeManagementRepository =
            GitHubSourceCodeManagementRepository(gitHubClient)
}
