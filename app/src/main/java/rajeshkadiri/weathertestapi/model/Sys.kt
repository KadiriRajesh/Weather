package rajeshkadiri.weathertestapi.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("type", "id", "country", "sunrise", "sunset")
class Sys : Parcelable {
    @get:JsonProperty("type")
    @set:JsonProperty("type")
    @JsonProperty("type")
    var type: Int? = null

    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: Int? = null

    @get:JsonProperty("country")
    @set:JsonProperty("country")
    @JsonProperty("country")
    var country: String? = null

    @get:JsonProperty("sunrise")
    @set:JsonProperty("sunrise")
    @JsonProperty("sunrise")
    var sunrise: Int? = null

    @get:JsonProperty("sunset")
    @set:JsonProperty("sunset")
    @JsonProperty("sunset")
    var sunset: Int? = null

    @JsonIgnore
    private var additionalProperties: MutableMap<String, Any>? = HashMap()

    protected constructor(`in`: Parcel) {
        type = `in`.readValue(Int::class.java.classLoader) as Int?
        id = `in`.readValue(Int::class.java.classLoader) as Int?
        country = `in`.readValue(String::class.java.classLoader) as String?
        sunrise = `in`.readValue(Int::class.java.classLoader) as Int?
        sunset = `in`.readValue(Int::class.java.classLoader) as Int?
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
        dest.writeValue(type)
        dest.writeValue(id)
        dest.writeValue(country)
        dest.writeValue(sunrise)
        dest.writeValue(sunset)
        dest.writeValue(additionalProperties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Sys?> = object : Parcelable.Creator<Sys?> {
            override fun createFromParcel(`in`: Parcel): Sys? {
                return Sys(`in`)
            }

            override fun newArray(size: Int): Array<Sys?> {
                return arrayOfNulls(size)
            }
        }
    }
}