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
    //API client for retrieving device location
    lateinit var fusedLocationClient: FusedLocationProviderClient
    var restaurantsRepository = RestaurantsRepository(RestaurantsService.ApiUtils.restaurant_Service)
    private val restaurants: MutableLiveData<NetworkResult<List<Restaurant>>> = MutableLiveData()

    //handles network call results to return data if successful, throw when error occurs
    sealed class NetworkResult<T> {
        class Success<T>(val data: T) : NetworkResult<T>()
        class Error<T>(val throwable: Throwable) : NetworkResult<T>()
    }

    //grab a list of restaurants if restaurant list is empty
    fun getRestaurants(): LiveData<NetworkResult<List<Restaurant>>> {
        if (restaurants.value == null) {
            loadRestaurants()
        }
        return restaurants
    }

    @SuppressLint("CheckResult", "MissingPermission")
    private fun loadRestaurants(): Disposable { //wrap Single<T> to be disposed when location and call is finished
        val disposable: Disposable = Single.create<Location> { emitter: SingleEmitter<Location> ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let { location ->
                        //.let allows us to directly use the location variable as it using lambda
                        emitter.onSuccess(location) }
                } //takes location Single and returns a SingleSource holding our response body or list: List<Restaurant>
        }.flatMap { location ->
            restaurantsRepository.restaurantListCall(location.latitude, location.longitude, 0, 50)
        }.subscribe({ location -> //when Single emits onSucces or onError notification do task
            // success //adds a task to the main thread to assign this value to restaurants
            restaurants.postValue(NetworkResult.Success(location))

        }, { t ->
            // error
            restaurants.postValue(NetworkResult.Error(t))
        })
        return disposable

    }

    /** When view model is destroyed dispose of Single responsible
     *  for fetching location and data from RESTAPI
     */
    override fun onCleared() {
        super.onCleared()
        loadRestaurants().dispose()
    }
}

