package org.rhinoonabus.stackoverflowbrowser.presentation.application

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesWithPhraseUseCase
import org.rhinoonabus.stackoverflowbrowser.domain.SourceCodeManagementRepository

@Module
class UseCasesModule {

    private val ioScheduler get() = Schedulers.io()
    private val mainThreadScheduler get() = AndroidSchedulers.mainThread()

    @Provides
    fun providesSearchForRepositoriesWithPhraseUseCase(sourceCodeManagementRepository: SourceCodeManagementRepository) =
            SearchForRepositoriesWithPhraseUseCase(ioScheduler, mainThreadScheduler, sourceCodeManagementRepository)
}
