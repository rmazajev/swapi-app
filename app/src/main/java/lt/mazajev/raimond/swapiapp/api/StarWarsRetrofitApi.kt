package lt.mazajev.raimond.swapiapp.api

import retrofit2.http.GET

interface StarWarsRetrofitApi {
    @GET("api/people")
    suspend fun getPeople(): PeopleJson
}