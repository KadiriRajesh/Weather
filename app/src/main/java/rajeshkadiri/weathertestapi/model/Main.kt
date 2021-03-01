package rajeshkadiri.weathertestapi.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("temp", "feels_like", "temp_min", "temp_max", "pressure", "humidity")
class Main : Parcelable {
    @get:JsonProperty("temp")
    @set:JsonProperty("temp")
    @JsonProperty("temp")
    var temp: Double? = null

    @get:JsonProperty("feels_like")
    @set:JsonProperty("feels_like")
    @JsonProperty("feels_like")
    var feelsLike: Double? = null

    @get:JsonProperty("temp_min")
    @set:JsonProperty("temp_min")
    @JsonProperty("temp_min")
    var tempMin: Double? = null

    @get:JsonProperty("temp_max")
    @set:JsonProperty("temp_max")
    @JsonProperty("temp_max")
    var tempMax: Double? = null

    @get:JsonProperty("pressure")
    @set:JsonProperty("pressure")
    @JsonProperty("pressure")
    var pressure: Int? = null

    @get:JsonProperty("humidity")
    @set:JsonProperty("humidity")
    @JsonProperty("humidity")
    var humidity: Int? = null

    @JsonIgnore
    private var additionalProperties: MutableMap<String, Any>? = HashMap()

    protected constructor(`in`: Parcel) {
        temp = `in`.readValue(Double::class.java.classLoader) as Double?
        feelsLike = `in`.readValue(Double::class.java.classLoader) as Double?
        tempMin = `in`.readValue(Double::class.java.classLoader) as Double?
        tempMax = `in`.readValue(Double::class.java.classLoader) as Double?
        pressure = `in`.readValue(Int::class.java.classLoader) as Int?
        humidity = `in`.readValue(Int::class.java.classLoader) as Int?
        additionalProperties = `in`.readValue(MutableMap::class.java.classLoader) as MutableMap<String, Any>?
    }

    constructor() {}

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any>? {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties!![name] = value
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(temp)
        dest.writeValue(feelsLike)
        dest.writeValue(tempMin)
        dest.writeValue(tempMax)
        dest.writeValue(pressure)
        dest.writeValue(humidity)
        dest.writeValue(additionalProperties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Main?> = object : Parcelable.Creator<Main?> {
            override fun createFromParcel(`in`: Parcel): Main? {
                return Main(`in`)
            }

            override fun newArray(size: Int): Array<Main?> {
                return arrayOfNulls(size)
            }
        }
    }
}