package lt.mazajev.raimond.swapi.api

import dagger.Binds
import dagger.Module
import dagger.Provides
import lt.mazajev.raimond.swapi.di.ApplicationScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module(includes = [ApiModule.Bindings::class])
class ApiModule {

    @Provides
    fun providesStarWarsRetrofitApi(): StarWarsRetrofitApi =
        Retrofit.Builder()
            .baseUrl("https://swapi.co")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsRetrofitApi::class.java)

    @Module
    interface Bindings {

        @ApplicationScope
        @Binds
        fun bindStarWarsApi(starWarsApi: StarWarsApiImpl): StarWarsApi

    }
}