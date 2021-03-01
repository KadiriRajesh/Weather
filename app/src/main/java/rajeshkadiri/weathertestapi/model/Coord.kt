package rajeshkadiri.weathertestapi.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("lon", "lat")
class Coord : Parcelable {
    @get:JsonProperty("lon")
    @set:JsonProperty("lon")
    @JsonProperty("lon")
    var lon: Double? = null

    @get:JsonProperty("lat")
    @set:JsonProperty("lat")
    @JsonProperty("lat")
    var lat: Double? = null

    @JsonIgnore
    private var additionalProperties: MutableMap<String, Any>? = HashMap()

    protected constructor(`in`: Parcel) {
        lon = `in`.readValue(Double::class.java.classLoader) as Double?
        lat = `in`.readValue(Double::class.java.classLoader) as Double?
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
        dest.writeValue(lon)
        dest.writeValue(lat)
        dest.writeValue(additionalProperties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Coord?> = object : Parcelable.Creator<Coord?> {
            override fun createFromParcel(`in`: Parcel): Coord? {
                return Coord(`in`)
            }

            override fun newArray(size: Int): Array<Coord?> {
                return arrayOfNulls(size)
            }
        }
    }
}