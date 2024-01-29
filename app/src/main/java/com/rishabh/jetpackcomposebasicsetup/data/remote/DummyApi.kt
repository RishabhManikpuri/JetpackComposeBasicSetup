package  com.rishabh.jetpackcomposebasicsetup.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface DummyApi {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<String>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}