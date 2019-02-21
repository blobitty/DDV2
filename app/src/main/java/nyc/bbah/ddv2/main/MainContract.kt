package nyc.bbah.ddv2.main

import nyc.bbah.ddv2.model.Restaurant
import retrofit2.Call

interface MainContract {

    //inner interface for call to server used in main
    interface Network{
                            //
        fun restaurantListCall(lat: Double?, lng: Double?, offset: Int, limit: Int, onSuccess: (List<Restaurant>) -> Unit): Call<List<Restaurant>>

        fun singleRestaurantCall(restaurant_id: Int, onSuccess: (Restaurant) -> Unit): Call<Restaurant>
    }

    interface RecyclerOnClickListener {
        fun onItemClick(restaurant: Restaurant)
    }
}