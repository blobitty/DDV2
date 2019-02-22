package nyc.bbah.ddv2.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import nyc.bbah.ddv2.main.RestaurantsRepository
import nyc.bbah.ddv2.model.Restaurant
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestaurantViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val repository = mock<RestaurantsRepository>()
    val locationProvider = mock<FusedLocationProviderClient>()

    val viewModel = RestaurantViewModel().apply {
        restaurantsRepository = repository
        fusedLocationClient = locationProvider
    }

    @Test
    fun `should post success to live data`() {
        val latitude = 10.0
        val longitude = 20.0
        val location = mock<Location> {
            on { this.latitude } doReturn latitude
            on { this.longitude } doReturn longitude
        }
        val task = mock<Task<Location>> {
            on { result } doReturn location
        }
        whenever(locationProvider.lastLocation).thenReturn(task)
        val restaurants = listOf<Restaurant>()
        whenever(repository.restaurantListCall(latitude, longitude, 0, 50)).thenReturn(Single.just(restaurants))

        viewModel.getRestaurants()

        argumentCaptor<OnSuccessListener<Location>>().apply {
            verify(task).addOnSuccessListener(capture())

            firstValue.onSuccess(location)
        }
        verify(locationProvider).lastLocation
        verify(repository).restaurantListCall(latitude, longitude, 0, 50)
        val networkResult = viewModel.getRestaurants().value
        assert(networkResult is RestaurantViewModel.NetworkResult<List<Restaurant>>)
        val success = networkResult as RestaurantViewModel.NetworkResult.Success<List<Restaurant>>
        assert(success.data == restaurants)
    }
}