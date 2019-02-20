package nyc.bbah.ddv2.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantsService {

    @GET("restaurant/")
    fun fetchRestaurantList(@Query("lat") lat: Long, @Query("lng") lng: Long,
                            @Query("offset") offset: Int, @Query("limit") limit: Int)

    @GET("restaurant/{restaurant_id}")
    fun fetchSingleRestaurant(@Path("restaurant_id") restaurant_id: Int)

    //compan
    object ApiUtils {

        private const val BASE_URL = "https://api.doordash.com//v2/"

        val restaurant_Service: RestaurantsService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(RestaurantsService::class.java)
    }

}