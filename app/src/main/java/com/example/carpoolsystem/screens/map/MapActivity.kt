package com.example.carpoolsystem.screens.map

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.carpoolsystem.R
import com.example.carpoolsystem.screens.AddRideScreen
import com.google.gson.GsonBuilder
import com.mapmyindia.sdk.maps.MapView
import com.mapmyindia.sdk.maps.MapmyIndiaMap
import com.mapmyindia.sdk.maps.OnMapReadyCallback
import com.mapmyindia.sdk.maps.annotations.MarkerOptions
import com.mapmyindia.sdk.maps.annotations.PolylineOptions
import com.mapmyindia.sdk.maps.camera.CameraPosition
import com.mapmyindia.sdk.maps.camera.CameraUpdateFactory
import com.mapmyindia.sdk.maps.geometry.LatLng
import com.mapmyindia.sdk.plugins.places.autocomplete.ui.PlaceAutocompleteFragment
import com.mapmyindia.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
import com.mmi.services.api.OnResponseCallback
import com.mmi.services.api.autosuggest.model.ELocation
import com.mmi.services.api.geocoding.GeoCodeResponse
import com.mmi.services.api.geocoding.MapmyIndiaGeoCoding
import com.mmi.services.api.geocoding.MapmyIndiaGeoCodingManager


data class Location(val lat: Double, val long: Double, val placeName: String)

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var suggestionContainer: FrameLayout
    private var mapMyIndiaMap: MapmyIndiaMap? = null
    private lateinit var btnSrc: AppCompatButton
    private lateinit var btnDest: AppCompatButton
    private lateinit var btnpool: AppCompatButton
    private lateinit var actionContainer: LinearLayoutCompat
    private val gson = GsonBuilder().setPrettyPrinting().serializeNulls().create()
    private var selectSource: Boolean = false
    private var sourceLoc: Location? = null
    private var destLoc: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        mapView = findViewById(R.id.map_view)
        suggestionContainer = findViewById(R.id.container)
        btnSrc = findViewById(R.id.btn_source)
        btnDest = findViewById(R.id.btn_dest)
        btnpool = findViewById(R.id.btn_pool)
        actionContainer = findViewById(R.id.container_actions)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this);

        val placeAutocompleteFragment: PlaceAutocompleteFragment =
            PlaceAutocompleteFragment.newInstance()
        supportFragmentManager.beginTransaction().add(
            suggestionContainer.id,
            placeAutocompleteFragment,
            PlaceAutocompleteFragment::class.java.simpleName
        )
            .commit()

        btnSrc.setOnClickListener {
            selectSource = true
            actionContainer.visibility = View.GONE
            if (suggestionContainer.visibility == View.VISIBLE) {
                suggestionContainer.visibility = View.GONE
            } else {
                suggestionContainer.visibility = View.VISIBLE
            }
        }

        btnDest.setOnClickListener {
            selectSource = false
            actionContainer.visibility = View.GONE
            if (suggestionContainer.visibility == View.VISIBLE) {
                suggestionContainer.visibility = View.GONE
            } else {
                suggestionContainer.visibility = View.VISIBLE
            }
        }


        findViewById<AppCompatButton>(R.id.btn_pool).setOnClickListener {
            val src = sourceLoc ?: return@setOnClickListener Toast.makeText(
                this,
                "select source please",
                Toast.LENGTH_SHORT
            ).show()
            val dest = destLoc ?: return@setOnClickListener Toast.makeText(
                this,
                "select destination please",
                Toast.LENGTH_SHORT
            ).show()

            val sourceLatLng = LatLng(src.lat, src.long)
            val destinLatLng = LatLng(dest.lat, dest.long)

            mapMyIndiaMap?.clear()

            mapMyIndiaMap?.addMarker(
                MarkerOptions().position(sourceLatLng)
            )

            mapMyIndiaMap?.addMarker(
                MarkerOptions().position(destinLatLng)
            )

            mapMyIndiaMap?.addPolyline(
                PolylineOptions()
                    .addAll(listOf(sourceLatLng, destinLatLng))
                    .color(Color.parseColor("#3bb2d0"))
                    .width(2f)
            )

            mapMyIndiaMap?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    sourceLatLng,
                    22.0
                )
            )
            val destination = btnDest.text.toString()
            val source = btnSrc.text.toString()
            val intent = Intent(this@MapActivity, AddRideScreen::class.java)
            intent.putExtra("source", source)
            intent.putExtra("destination", destination)
            startActivity(intent)


        }



        placeAutocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onCancel() {
                //on click of back button
            }

            override fun onPlaceSelected(eLocation: ELocation?) {
                // Select place
                Log.e("MapMyIndia", gson.toJson(eLocation))
                suggestionContainer.visibility = View.GONE
                actionContainer.visibility = View.VISIBLE
                val mapmyIndiaMap = mapMyIndiaMap

                if (mapmyIndiaMap != null && eLocation != null) {

                    val mapmyIndiaGeoCoding = MapmyIndiaGeoCoding.builder()
                        .setAddress(eLocation.placeAddress)
                        .build()

                    MapmyIndiaGeoCodingManager.newInstance(mapmyIndiaGeoCoding)
                        .call(object : OnResponseCallback<GeoCodeResponse> {
                            override fun onSuccess(response: GeoCodeResponse) {
                                //handle response
                                response.results.firstOrNull()?.let {
                                    mapmyIndiaMap.clear()
                                    if (selectSource) {
                                        sourceLoc = Location(
                                            it.latitude, it.longitude, it.formattedAddress
                                        )
                                        btnSrc.text = "Source : ${sourceLoc?.placeName}"
                                    } else {
                                        destLoc = Location(
                                            it.latitude, it.longitude, it.formattedAddress
                                        )
                                        btnDest.text = "Destination : ${destLoc?.placeName}"
                                    }
                                    val latLng = LatLng(it.latitude, it.longitude)
                                    mapmyIndiaMap.animateCamera(
                                        CameraUpdateFactory.newLatLngZoom(
                                            latLng,
                                            20.0
                                        )
                                    )
                                    mapmyIndiaMap.addMarker(
                                        MarkerOptions().position(latLng)
                                            .title(eLocation.placeName)
                                            .snippet(eLocation.placeAddress)
                                    )
                                }
                            }

                            override fun onError(code: Int, message: String) {
                                //handle Error
                                Log.e("Map Error", "Error code $code, message: $message")
                            }
                        })


                }


                val cameraPosition = CameraPosition.Builder()
                    .target(
                        LatLng(
                            eLocation?.latitude?.toDouble() ?: 0.0,
                            eLocation?.longitude?.toDouble() ?: 0.0,
                        )
                    )
                    .zoom(10.0)
                    .tilt(0.0)
                    .build()
                mapmyIndiaMap?.cameraPosition = cameraPosition
            }

        })
    }

    override fun onMapReady(p0: MapmyIndiaMap) {
        mapMyIndiaMap = p0
    }

    override fun onMapError(p0: Int, p1: String?) {
        Log.e("Map Error", "Error code $p0, message: $p1")
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}