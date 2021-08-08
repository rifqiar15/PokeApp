package com.rifqi.pokeapp.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rifqi.pokeapp.data.datasource.PokemonDataSource
import com.rifqi.pokeapp.data.api.Api
import com.rifqi.pokeapp.data.repository.PokemonRepository
import com.rifqi.pokeapp.domain.IPokemonRepository
import com.rifqi.pokeapp.domain.PokemonInteractor
import com.rifqi.pokeapp.domain.PokemonUseCase
import com.rifqi.pokeapp.presentation.viewmodel.PokemonViewModel
import com.rifqi.pokeapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModelModule = module {
    viewModel { PokemonViewModel(get()) }
}

val interactorModule = module {
    factory<PokemonUseCase>{ PokemonInteractor(get())}
}

val repositoryModule = module {
    single<IPokemonRepository> { PokemonRepository(get()) }
}

val dataSourceModule = module {
    single { PokemonDataSource(get()) }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit) : Api {
        return retrofit.create(Api::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {
    fun provideGson() : Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient() : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient.Builder()
        return  okHttpClientBuilder.addInterceptor(httpLoggingInterceptor).build()
    }

    fun provideRetrofit(factory : Gson, client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(),get()) }
}