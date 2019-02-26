package nyc.bbah.ddv2.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.disposables.Disposable
import nyc.bbah.ddv2.main.RestaurantsRepository
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.network.RestaurantsService

class RestaurantViewModel : ViewModel(){

    lateinit var fusedLocationClient: FusedLocationProviderClient
    var restaurantsRepository = RestaurantsRepository(RestaurantsService.ApiUtils.restaurant_Service)
    private val restaurants: MutableLiveData<NetworkResult<List<Restaurant>>> = MutableLiveData()


    sealed class NetworkResult<T> {

        class Success<T>(val data: T) : NetworkResult<T>()
        class Error<T>(val throwable: Throwable) : NetworkResult<T>()
    }


    fun getRestaurants(): LiveData<NetworkResult<List<Restaurant>>> {
        if (restaurants.value == null) {
            loadRestaurants()
        }
        return restaurants
    }

    @SuppressLint("CheckResult", "MissingPermission")
    private fun loadRestaurants(): Disposable {
        val disposable: Disposable = Single.create<Location> { emitter: SingleEmitter<Location> ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let { emitter.onSuccess(it) }
                }
        }.flatMap { location ->
            restaurantsRepository.restaurantListCall(location.latitude, location.longitude, 0, 50)
        }.subscribe({
            // success
            restaurants.postValue(NetworkResult.Success(it))

        }, {
            // error
            restaurants.postValue(NetworkResult.Error(it))
        })
        return disposable

    }

    override fun onCleared() {
        super.onCleared()
        loadRestaurants().dispose()
    }
}

