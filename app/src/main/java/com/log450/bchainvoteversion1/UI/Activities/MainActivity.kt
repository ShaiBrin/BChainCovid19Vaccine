package com.log450.bchainvoteversion1.UI.Activities

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.log450.bchainvoteversion1.R


class MainActivity : AppCompatActivity(), LocationListener{

    private val locationPermissionCode = 2
    private lateinit var locationManager: LocationManager
    private lateinit var geocoder: Geocoder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        geocoder = Geocoder(this)

        getLocation()
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)

    }
    override fun onLocationChanged(location: Location) {
        Log.d( "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK: ",location.latitude.toString())
        Log.d( "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ: ",location.longitude.toString())
        //geocoder.getFromLocation(location.latitude, location.longitude,1)[0].countryName
        Log.d( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: ",geocoder.getFromLocation(location.latitude, location.longitude,1)[0].countryName
            .toString())

        //tvGpsLocation = findViewById(R.id.textView)
       // tvGpsLocation.text = "Latitude: " + location.latitude + " , Longitude: " + location.longitude
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}