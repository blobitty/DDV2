import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import nyc.bbah.ddv2.MainCall
import nyc.bbah.ddv2.R
import nyc.bbah.ddv2.network.RestaurantsService


class MainActivity : AppCompatActivity() {

    private val mainCall: MainCall = MainCall(RestaurantsService.ApiUtils.restaurant_Service)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainCall.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }
}
