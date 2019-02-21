package nyc.bbah.ddv2.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import nyc.bbah.ddv2.model.Restaurant

class RestaurantViewModel : ViewModel(){


    private val restaurants: MutableLiveData<List<Restaurant>> by lazy {
        MutableLiveData<List<Restaurant>>().also {
            loadRestaurants()
        }
    }

    fun getRestaurants(): LiveData<List<Restaurant>> {
        return restaurants
    }

    private fun loadRestaurants() {
        // Do an asynchronous operation to fetch restaurants.
    }

}