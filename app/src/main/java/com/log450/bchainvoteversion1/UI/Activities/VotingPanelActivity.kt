package com.log450.bchainvoteversion1.UI.Activities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.log450.bchainvoteversion1.UI.Fragments.FragmentStats
import com.log450.bchainvoteversion1.UI.Fragments.FragmentVoting
import com.log450.bchainvoteversion1.R
import com.log450.bchainvoteversion1.Utils.Constants.EXTRA_ID


class VotingPanelActivity : AppCompatActivity(), LocationListener {
    private val locationPermissionCode = 2
    private lateinit var locationManager: LocationManager
    private lateinit var geocoder: Geocoder
    private lateinit var country: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        geocoder = Geocoder(this)
        val id = intent.getStringExtra(EXTRA_ID) as String
        getLocation()
        loadBottomNavigation(id)
    }

    fun loadBottomNavigation(id:String) {
        this.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.StatsOptions -> {
                        loadFragment(FragmentStats(country))
                        return@setOnNavigationItemSelectedListener true
                    }

                    R.id.voteOption -> {
                        loadFragment(FragmentVoting(id))
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                false
            }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainerIdentifiant, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)

    }
    override fun onLocationChanged(location: Location) {
        Log.d( "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK: ",location.latitude.toString())
        Log.d( "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ: ",location.longitude.toString())
        //geocoder.getFromLocation(location.latitude, location.longitude,1)[0].countryName
        Log.d( "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: ",geocoder.getFromLocation(location.latitude, location.longitude,1)[0].countryName
            .toString())
        country = geocoder.getFromLocation(location.latitude, location.longitude,1)[0].countryName
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