package xyz.droidev.fitness.data.api

import retrofit2.Response
import retrofit2.http.GET
import xyz.droidev.fitness.data.model.HomeResponse

interface ApiService {

    @GET("homepage_v2/")
    suspend fun getData(): Response<HomeResponse>

}