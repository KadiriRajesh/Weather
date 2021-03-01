package rajeshkadiri.weathertestapi.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "main", "description", "icon")
class Weather : Parcelable {
    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: Int? = null

    @get:JsonProperty("main")
    @set:JsonProperty("main")
    @JsonProperty("main")
    var main: String? = null

    @get:JsonProperty("description")
    @set:JsonProperty("description")
    @JsonProperty("description")
    var description: String? = null

    @get:JsonProperty("icon")
    @set:JsonProperty("icon")
    @JsonProperty("icon")
    var icon: String? = null

    @JsonIgnore
    private var additionalProperties: MutableMap<String, Any>? = HashMap()

    protected constructor(`in`: Parcel) {
        id = `in`.readValue(Int::class.java.classLoader) as Int?
        main = `in`.readValue(String::class.java.classLoader) as String?
        description = `in`.readValue(String::class.java.classLoader) as String?
        icon = `in`.readValue(String::class.java.classLoader) as String?
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
        dest.writeValue(id)
        dest.writeValue(main)
        dest.writeValue(description)
        dest.writeValue(icon)
        dest.writeValue(additionalProperties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Weather?> = object : Parcelable.Creator<Weather?> {
            override fun createFromParcel(`in`: Parcel): Weather? {
                return Weather(`in`)
            }

            override fun newArray(size: Int): Array<Weather?> {
                return arrayOfNulls(size)
            }
        }
    }
}