package nyc.bbah.ddv2.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import nyc.bbah.ddv2.R
import nyc.bbah.ddv2.RestaurantsNavigator
import nyc.bbah.ddv2.adapter.RestaurantListAdapter
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.network.RestaurantsService
import nyc.bbah.ddv2.viewmodel.RestaurantViewModel
import retrofit2.Call


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[RestaurantViewModel::class.java]
        viewModel.fusedLocationClient = FusedLocationProviderClient(this)
        viewModel.getRestaurants()
            .observe(this,
                Observer<RestaurantViewModel.NetworkResult<List<Restaurant>>> { restaurants ->
                    if (restaurants != null) {
                        when (restaurants) {
                            is RestaurantViewModel.NetworkResult.Success -> showRestaurants(restaurants.data)
                            is RestaurantViewModel.NetworkResult.Error -> showError()
                        }
                    }
                })
    }

    private fun showRestaurants(restaurants: List<Restaurant>) {
        val navigator = RestaurantsNavigator(this)
        val restaurantListAdapter = RestaurantListAdapter(restaurants, navigator)
        discover_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        discover_recyclerview.adapter = restaurantListAdapter
    }

    private fun showError() {
        Toast.makeText(this, "I DONT KNOW BRO", Toast.LENGTH_LONG).show()
    }
}
