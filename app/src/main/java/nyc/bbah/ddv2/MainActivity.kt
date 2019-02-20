package nyc.bbah.ddv2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import nyc.bbah.ddv2.MainCall
import nyc.bbah.ddv2.R
import nyc.bbah.ddv2.adapter.RestaurantListAdapter
import nyc.bbah.ddv2.model.Restaurant
import nyc.bbah.ddv2.network.RestaurantsService
import retrofit2.Call


class MainActivity : AppCompatActivity() {

    val mainCall: MainCall = MainCall(RestaurantsService.ApiUtils.restaurant_Service)
    var call: Call<List<Restaurant>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainCall.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mainCall.getUserLocations()
        mainCall.restaurantListCall(mainCall.latitude, mainCall.longitude, 0, 50, fun(it) {
            val restaurantListAdapter = RestaurantListAdapter(it)
            discover_recyclerview.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
            discover_recyclerview.adapter = restaurantListAdapter
        })
    }

    override fun onStop() {
        super.onStop()
        call?.cancel()
    }
}
