package lt.mazajev.raimond.swapi.api

import retrofit2.http.GET

interface StarWarsRetrofitApi {
    @GET("api/people")
    suspend fun getPeople(): PeopleJson
}