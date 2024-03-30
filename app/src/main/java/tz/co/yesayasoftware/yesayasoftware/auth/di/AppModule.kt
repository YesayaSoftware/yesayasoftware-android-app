package tz.co.yesayasoftware.yesayasoftware.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tz.co.yesayasoftware.yesayasoftware.auth.data.repositories.login.LoginRepository
import tz.co.yesayasoftware.yesayasoftware.auth.data.repositories.login.LoginRepositoryImpl
import tz.co.yesayasoftware.yesayasoftware.auth.data.sources.remote.ApiService
import tz.co.yesayasoftware.yesayasoftware.exceptions.ValidationError
import tz.co.yesayasoftware.yesayasoftware.utils.Constants.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        service: ApiService
    ): LoginRepository {
        return LoginRepositoryImpl(service)
    }

    private fun buildClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                var request = chain.request()

                val builder = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Connection", "close")

                request = builder.build()

                chain.proceed(request)
            }

        return builder.build()
    }
}