package nyc.bbah.ddv2.main

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_main.*
import nyc.bbah.ddv2.R
import nyc.bbah.ddv2.RestaurantsNavigator
import nyc.bbah.ddv2.adapter.RestaurantListAdapter
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.viewmodel.RestaurantViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RestaurantViewModel
    private val REQUEST_LOCATION_CODE = 123
    private lateinit var managePermissions: ManagePermissions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        managePermissions = ManagePermissions(this, Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_LOCATION_CODE)
        viewModel = ViewModelProviders.of(this)[RestaurantViewModel::class.java]
        runtimeLocationCheck()

    }
    private fun showRestaurants(restaurants: List<Restaurant>) {
        val navigator = RestaurantsNavigator(this)
        val restaurantListAdapter = RestaurantListAdapter(restaurants, navigator)
        discover_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        discover_recyclerview.adapter = restaurantListAdapter
    }

    private fun showError() {
        Toast.makeText(this, "Restaurants could not be found :( ", Toast.LENGTH_LONG).show()
    }

    private fun runtimeLocationCheck(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            managePermissions.checkPermissions()

        } else {
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
    }

    // Receive the permissions request result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_CODE ->{
                val isPermissionsGranted = managePermissions
                    .processPermissionsResult(requestCode,permissions,grantResults)

                if(isPermissionsGranted){
                    // Do task
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show()
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
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}
