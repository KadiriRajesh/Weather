package rajeshkadiri.weathertestapi.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("coord", "weather", "base", "main", "visibility", "wind", "clouds", "dt", "sys", "timezone", "id", "name", "cod")
class WeateherResponse : Parcelable {
    @get:JsonProperty("coord")
    @set:JsonProperty("coord")
    @JsonProperty("coord")
    var coord: Coord? = null

    @get:JsonProperty("weather")
    @set:JsonProperty("weather")
    @JsonProperty("weather")
    var weather: List<Weather?>? = null

    @get:JsonProperty("base")
    @set:JsonProperty("base")
    @JsonProperty("base")
    var base: String? = null

    @get:JsonProperty("main")
    @set:JsonProperty("main")
    @JsonProperty("main")
    var main: Main? = null

    @get:JsonProperty("visibility")
    @set:JsonProperty("visibility")
    @JsonProperty("visibility")
    var visibility: Int? = null

    @get:JsonProperty("wind")
    @set:JsonProperty("wind")
    @JsonProperty("wind")
    var wind: Wind? = null

    @get:JsonProperty("clouds")
    @set:JsonProperty("clouds")
    @JsonProperty("clouds")
    var clouds: Clouds? = null

    @get:JsonProperty("dt")
    @set:JsonProperty("dt")
    @JsonProperty("dt")
    var dt: Int? = null

    @get:JsonProperty("sys")
    @set:JsonProperty("sys")
    @JsonProperty("sys")
    var sys: Sys? = null

    @get:JsonProperty("timezone")
    @set:JsonProperty("timezone")
    @JsonProperty("timezone")
    var timezone: Int? = null

    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: Int? = null

    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null

    @get:JsonProperty("cod")
    @set:JsonProperty("cod")
    @JsonProperty("cod")
    var cod: Int? = null

    @JsonIgnore
    private var additionalProperties: MutableMap<String, Any>? = HashMap()

    protected constructor(`in`: Parcel) {
        coord = `in`.readValue(Coord::class.java.classLoader) as Coord?
        `in`.readList(weather!!, Weather::class.java.classLoader)
        base = `in`.readValue(String::class.java.classLoader) as String?
        main = `in`.readValue(Main::class.java.classLoader) as Main?
        visibility = `in`.readValue(Int::class.java.classLoader) as Int?
        wind = `in`.readValue(Wind::class.java.classLoader) as Wind?
        clouds = `in`.readValue(Clouds::class.java.classLoader) as Clouds?
        dt = `in`.readValue(Int::class.java.classLoader) as Int?
        sys = `in`.readValue(Sys::class.java.classLoader) as Sys?
        timezone = `in`.readValue(Int::class.java.classLoader) as Int?
        id = `in`.readValue(Int::class.java.classLoader) as Int?
        name = `in`.readValue(String::class.java.classLoader) as String?
        cod = `in`.readValue(Int::class.java.classLoader) as Int?
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
        dest.writeValue(coord)
        dest.writeList(weather)
        dest.writeValue(base)
        dest.writeValue(main)
        dest.writeValue(visibility)
        dest.writeValue(wind)
        dest.writeValue(clouds)
        dest.writeValue(dt)
        dest.writeValue(sys)
        dest.writeValue(timezone)
        dest.writeValue(id)
        dest.writeValue(name)
        dest.writeValue(cod)
        dest.writeValue(additionalProperties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<WeateherResponse?> = object : Parcelable.Creator<WeateherResponse?> {
            override fun createFromParcel(`in`: Parcel): WeateherResponse? {
                return WeateherResponse(`in`)
            }

            override fun newArray(size: Int): Array<WeateherResponse?> {
                return arrayOfNulls(size)
            }
        }
    }
}