package nyc.bbah.ddv2.network

import io.reactivex.Single
import nyc.bbah.ddv2.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantsService {

    //Gets a full list of restaurants in the area.
    @GET("restaurant/")
    fun fetchRestaurantList(@Query("lat") lat: Double?, @Query("lng") lng: Double?,
                            @Query("offset") offset: Int, @Query("limit") limit: Int): Single<List<Restaurant>>

    //Gets s single restaurant with the argument id
    @GET("restaurant/{restaurant_id}")
    fun fetchSingleRestaurant(@Path("restaurant_id") restaurant_id: Int): Single<Restaurant>


    object ApiUtils {
        private const val BASE_URL = "https://api.doordash.com//v2/"
        val restaurant_Service: RestaurantsService get() = RetrofitClient
            .getClient(BASE_URL)!!.create(RestaurantsService::class.java)
        
    }

}