package nyc.bbah.ddv2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Location
import android.support.v4.content.ContextCompat.startActivity
import com.google.android.gms.location.FusedLocationProviderClient
import nyc.bbah.ddv2.main.MainActivity

class SplashLocationCall : SplashContract.Location {
    var latitude: Double? = null
    var longitude: Double?  = null
    lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun getLocation(){
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    latitude = location?.latitude!!
                    longitude = location.longitude

                }
    }
}