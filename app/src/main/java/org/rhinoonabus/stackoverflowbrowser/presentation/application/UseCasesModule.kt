package org.rhinoonabus.stackoverflowbrowser.presentation.application

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.rhinoonabus.stackoverflowbrowser.domain.GetDetailsForUserWithLoginUseCase
import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesOrUsersWithPhraseUseCase
import org.rhinoonabus.stackoverflowbrowser.domain.SourceCodeManagementRepository

@Module
class UseCasesModule {

    private val ioScheduler get() = Schedulers.io()
    private val mainThreadScheduler get() = AndroidSchedulers.mainThread()

    @Provides
    fun providesSearchForRepositoriesWithPhraseUseCase(sourceCodeManagementRepository: SourceCodeManagementRepository) =
            SearchForRepositoriesOrUsersWithPhraseUseCase(
                    ioScheduler,
                    mainThreadScheduler,
                    sourceCodeManagementRepository
            )

    @Provides
    fun providesGetDetailsForUserWithLoginUseCase(sourceCodeManagementRepository: SourceCodeManagementRepository) =
            GetDetailsForUserWithLoginUseCase(
                    ioScheduler,
                    mainThreadScheduler,
                    sourceCodeManagementRepository
            )
}
