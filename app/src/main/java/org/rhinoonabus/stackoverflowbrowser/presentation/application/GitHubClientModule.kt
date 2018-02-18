package org.rhinoonabus.stackoverflowbrowser.presentation.application

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.rhinoonabus.stackoverflowbrowser.BuildConfig
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class GitHubClientModule {

    private val GITHUB_API_URL = BuildConfig.GITHUB_API_URL

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor
            = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun providesOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesRetrofit(
            okHttpClient: OkHttpClient,
            gson: Gson
    ): Retrofit
            = Retrofit.Builder()
            .baseUrl(GITHUB_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Provides
    @Singleton
    fun providesGitHubApiClient(
            retrofit: Retrofit
    ): GitHubApiClient = retrofit.create(GitHubApiClient::class.java)

}
