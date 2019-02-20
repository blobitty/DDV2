package nyc.bbah.ddv2

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.network.RestaurantsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainCall(private val restaurantsService: RestaurantsService): MainContract.Network {

    lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude: Double? = null
    private var longitude: Double? = null


    override fun restaurantListCall(lat: Double, lng: Double, offset: Int, limit: Int,
        onSuccess: (List<Restaurant>) -> Unit): Call<List<Restaurant>> {

        getUserLocations()
        val call: Call<List<Restaurant>> = restaurantsService.fetchRestaurantList(latitude, longitude, 0, 50)

        call.enqueue(object: Callback<List<Restaurant>> {
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                val data: List<Restaurant>? = response.body()
                data?.let(onSuccess)
                Log.d("RESPONSE CHECK: ", response.raw().toString())
            }
            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    override fun singleRestaurantCall(restaurant_id: Int, onSuccess: (Restaurant) -> Unit): Call<Restaurant> {
        val call: Call<Restaurant> = restaurantsService.fetchSingleRestaurant(restaurant_id)

        call.enqueue(object: Callback<Restaurant>{
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                val data: Restaurant? = response.body()
                data?.let(onSuccess)
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return call
    }

    @SuppressLint("MissingPermission")
    fun getUserLocations(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                latitude =  location?.latitude
                longitude = location?.longitude
            }
    }
}