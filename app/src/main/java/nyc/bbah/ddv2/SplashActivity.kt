package nyc.bbah.ddv2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.LocationServices
import nyc.bbah.ddv2.main.MainActivity

class SplashActivity : AppCompatActivity() {

    val splashLocationCall: SplashLocationCall = SplashLocationCall()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashLocationCall.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        while (splashLocationCall.longitude == null){
            splashLocationCall.getLocation()
        }

    }
}
