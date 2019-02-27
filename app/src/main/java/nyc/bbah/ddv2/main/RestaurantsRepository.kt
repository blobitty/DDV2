package nyc.bbah.ddv2.main

import android.util.Log
import io.reactivex.Single
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.network.RestaurantsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantsRepository(private val restaurantsService: RestaurantsService) {

    fun restaurantListCall(lat: Double?, lng: Double?, offset: Int, limit: Int): Single<List<Restaurant>> {
        return restaurantsService.fetchRestawurantList(lat, lng, offset, limit)
    }

    fun singleRestaurantCall(restaurant_id: Int, onSuccess: (Restaurant) -> Unit): Single<Restaurant> {
        return restaurantsService.fetchSingleRestaurant(restaurant_id)
    }
}