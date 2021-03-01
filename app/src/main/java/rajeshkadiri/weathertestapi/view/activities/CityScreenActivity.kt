package rajeshkadiri.weathertestapi.view.activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rajeshkadiri.weathertestapi.R
import rajeshkadiri.weathertestapi.base.WeatherService
import rajeshkadiri.weathertestapi.model.WeatherResponse
import rajeshkadiri.weathertestapi.view.activities.CityScreenActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat

class CityScreenActivity : AppCompatActivity() {
    private var textViewCardCityName: TextView? = null
    private var textViewCardWeatherDescription: TextView? = null
    private var textViewCardCurrentTemp: TextView? = null
    private var textViewCardCurrentHumidity: TextView? = null
    private var textViewCardCurrentSpeed: TextView? = null
    private var imageViewCardWeatherIcon: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_card)
        textViewCardCityName = findViewById(R.id.textViewCardCityName)
        textViewCardCurrentSpeed = findViewById(R.id.textViewCardCurrentSpeed)
        textViewCardCurrentHumidity = findViewById(R.id.textViewCardCurrentHumidity)
        textViewCardWeatherDescription = findViewById(R.id.textViewCardWeatherDescription)
        textViewCardCurrentTemp = findViewById(R.id.textViewCardCurrentTemp)
        imageViewCardWeatherIcon = findViewById(R.id.imageViewCardWeatherIcon)
        val i = intent
        val extras = i.extras
        if (extras == null) {
            lat = null
            lon = null
            Toast.makeText(applicationContext, "no data found", Toast.LENGTH_LONG).show()
        } else {
            lat = extras.getString("lat")
            lon = extras.getString("lng")
            getCurrentData(lat, lon)
            Log.e("latlng:", ">>" + lat + ">>" + lon)
        }


        /* findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // city = "" + weatherDataText.getText().toString().trim();
                if (city != "") {
                    getCurrentData();
                } else {
                    weatherDataText.setError("enter city");
                }

            }
        });*/
    }

    fun getCurrentData(lat: String?, lon: String?) {
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppId)
        // Call<WeatherResponse> call = service.getCurrentCity(city, AppId);
        call!!.enqueue(object : Callback<WeatherResponse?> {
            override fun onResponse(call: Call<WeatherResponse?>,
                                    response: Response<WeatherResponse?>) {
                if (response.code() == 200) {
                    val WeatherResponse = response.body()!!
                    val stringBuilder = """
                        City: ${WeatherResponse.name}
                        Temperature: ${WeatherResponse.main!!.temp}
                        Humidity: ${WeatherResponse.main!!.humidity}
                        Pressure: ${WeatherResponse.main!!.pressure}
                        """.trimIndent()
                    var temparature = ("" + WeatherResponse.main!!.temp).toFloat() - 273
                    val df = DecimalFormat("#.0")
                    temparature = df.format(temparature.toDouble()).toFloat()
                    textViewCardCityName!!.text = WeatherResponse.name
                    textViewCardCurrentHumidity!!.text = "Humidity: " + WeatherResponse.main!!.humidity
                    textViewCardWeatherDescription!!.text = "" + WeatherResponse.weather!![0]!!.description
                    textViewCardCurrentTemp!!.text = "$temparature\u00B0C"
                    textViewCardCurrentSpeed!!.text = "Wind Speed: " + WeatherResponse.wind!!.speed + " mph"
                    Log.e("weather  ", "" + stringBuilder)
                }
            }

            override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
                // weatherData.setText(t.getMessage());
                Toast.makeText(this@CityScreenActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    companion object {
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "2e65127e909e178d0af311a81f39948c"
        var lat: String? = "17.3753"
        var lon: String? = "78.4744"
        var city = "Hyderabad"
    }
}