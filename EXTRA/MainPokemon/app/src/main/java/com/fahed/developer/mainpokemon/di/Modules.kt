package com.fahed.developer.mainpokemon.di

import com.fahed.developer.mainpokemon.data.net.PokemonApi
import com.fahed.developer.mainpokemon.respository.PokemonRepository
import com.fahed.developer.mainpokemon.respository.PokemonRepositoryImpl
import com.fahed.developer.mainpokemon.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {

    single<PokemonRepository>{ PokemonRepositoryImpl() }
}

val viewmodelModule = module {

    viewModel { MainViewModel(get()) }
}


val networkModule = module {
    val API_BASE_URL = "https://pokeapi.co/api/v2/"

    var servicesApiInterface: PokemonApi? = null

    fun provideHttpClient(interceptor: HttpLoggingInterceptor): PokemonApi?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            PokemonApi::class.java)

        return servicesApiInterface as PokemonApi
    }

    fun provideInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    single { provideInterceptor() }
    single { provideHttpClient(get()) }
}