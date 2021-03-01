package rajeshkadiri.weathertestapi.view.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import rajeshkadiri.weathertestapi.R
import rajeshkadiri.weathertestapi.utils.DBHelper
import java.io.IOException
import java.util.*

class MapsFragment2 : Fragment(), View.OnClickListener {


    // declare a global variable of FusedLocationProviderClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("UseRequireInsteadOfGet", "MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        mMap = googleMap
        if (mLocationPermissionsGranted) {
            //deviceLocation
            if (ActivityCompat.checkSelfPermission(activity!!,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(activity!!,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@OnMapReadyCallback
            }

            mMap!!.isMyLocationEnabled = true
            mMap!!.uiSettings.isMyLocationButtonEnabled = false

            getLastKnownLocation()
            init()
        }
    }
    private var mRootView: ViewGroup? = null
    var dbHelper: DBHelper? = null

    //widgets
    private var mSearchText: EditText? = null
    private var mGps: ImageView? = null
    private var bookmarklocation: AppCompatButton? = null

    //vars
    private var mLocationPermissionsGranted = false
    private var mMap: GoogleMap? = null
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    var latitude: String? = null
    var longitude: String? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_maps, null) as ViewGroup
            initViews()
            // registerViewListeners()
            //initObjects()
        } else {
            if (mRootView!!.parent != null) {
                (mRootView!!.parent as ViewGroup).removeView(mRootView)
            }
        }
        return mRootView
    }

    private fun initViews() {
        mSearchText = mRootView!!.findViewById<View>(R.id.input_search) as EditText
        mGps = mRootView!!.findViewById<View>(R.id.ic_gps) as ImageView
        bookmarklocation = mRootView!!.findViewById<View>(R.id.bookmarklocation) as AppCompatButton
        dbHelper = DBHelper(activity)
        bookmarklocation!!.setOnClickListener(this)

        mSearchText!!.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || keyEvent.action == KeyEvent.ACTION_DOWN || keyEvent.action == KeyEvent.KEYCODE_ENTER) {

                //execute our method for searching
                geoLocate()
            }
            false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val locationPermission: Unit
        private get() {
            Log.d(TAG, "getLocationPermission: getting location permissions")
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
            if (ContextCompat.checkSelfPermission(requireActivity().applicationContext,
                            FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(requireActivity().applicationContext,
                                COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionsGranted = true
                    //   initMap();
                } else {
                    ActivityCompat.requestPermissions(requireActivity(),
                            permissions,
                            LOCATION_PERMISSION_REQUEST_CODE)
                }
            } else {
                ActivityCompat.requestPermissions(requireActivity(),
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE)
            }
        }

    companion object {
        private const val TAG = "MapActivity"
        private const val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        private const val COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1234
        private const val DEFAULT_ZOOM = 15f
    }

    private fun savelatlngvalues() {
        val geocoder = Geocoder(activity, Locale.getDefault())
        var addresses: List<Address>? = null
        try {
            addresses = geocoder.getFromLocation(latitude!!.toDouble(), longitude!!.toDouble(), 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val tsLong = System.currentTimeMillis() / 1000
        val timeStamp = tsLong.toString()
        val cityName = addresses!![0].getAddressLine(0)
        dbHelper!!.insertContact(latitude, longitude, cityName, timeStamp)
    }

    private fun init() {
        Log.d(TAG, "init: initializing")

        mGps!!.setOnClickListener {
            Log.d(TAG, "onClick: clicked gps icon")
            // deviceLocation
        }
        mMap!!.setOnMapClickListener { point -> //allPoints.add(point);
            mMap!!.clear()
            moveCamera(point, DEFAULT_ZOOM, "Your selected location")
        }
        hideSoftKeyboard()
    }

    private fun geoLocate() {
        Log.d(TAG, "geoLocate: geolocating")
        val searchString = mSearchText!!.text.toString()
        val geocoder = Geocoder(activity)
        var list: List<Address> = ArrayList()
        try {
            list = geocoder.getFromLocationName(searchString, 1)
        } catch (e: IOException) {
            Log.e(TAG, "geoLocate: IOException: " + e.message)
        }
        if (list.size > 0) {
            val address = list[0]
            Log.d(TAG, "geoLocate: found a location: $address")
            //Toast.makeText(getActivity(), address.toString(), Toast.LENGTH_SHORT).show();
            moveCamera(LatLng(address.latitude, address.longitude), DEFAULT_ZOOM,
                    address.getAddressLine(0))
        }
    }

    private fun hideSoftKeyboard() {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun moveCamera(latLng: LatLng, zoom: Float, title: String) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + "," +
                " lng: " + latLng.longitude)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
        if (title != "My Location") {
            mMap!!.clear()
            val options = MarkerOptions()
                    .position(latLng)
                    .title(title)
            mMap!!.addMarker(options)
        } else {
            mMap!!.clear()
            val options = MarkerOptions()
                    .position(latLng)
                    .title(title)
            mMap!!.addMarker(options)
        }
        latitude = "" + latLng.latitude
        longitude = "" + latLng.longitude
        hideSoftKeyboard()
    }//   initMap();

    /*    private val deviceLocation: Unit
    private get() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location")
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        try {
            if (mLocationPermissionsGranted) {
                val location: Task<*> = mFusedLocationProviderClient?.getLastLocation()!!
                location.addOnCompleteListener(OnCompleteListener<Any?> { p0 ->
                    if (p0.isSuccessful) {
                        Log.d(TAG, "onComplete: found location!")
                        val currentLocation = p0.result as Location?
                        moveCamera(LatLng(currentLocation!!.latitude, currentLocation.longitude),
                                DEFAULT_ZOOM,
                                "My Location")
                    } else {
                        Log.d(TAG, "onComplete: current location is null")
                        Toast.makeText(activity, "unable to get current location", Toast.LENGTH_SHORT).show()
                    }
                } as Nothing)
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.message)
        }
    }*/

    /**
     * call this method for receive location
     * get location and give callback when successfully retrieve
     * function itself check location permission before access related methods
     *
     */
    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        // use your location object
                        // get latitude , longitude and other info from this
                        moveCamera(LatLng(location!!.latitude, location.longitude),
                                DEFAULT_ZOOM,
                                "My Location")
                    }

                }

    }

    override fun onClick(p0: View?) {
        savelatlngvalues()
    }
}