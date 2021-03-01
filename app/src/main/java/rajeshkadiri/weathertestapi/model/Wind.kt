package rajeshkadiri.weathertestapi.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("speed", "deg")
class Wind : Parcelable {
    @get:JsonProperty("speed")
    @set:JsonProperty("speed")
    @JsonProperty("speed")
    var speed: Double? = null

    @get:JsonProperty("deg")
    @set:JsonProperty("deg")
    @JsonProperty("deg")
    var deg: Int? = null

    @JsonIgnore
    private var additionalProperties: MutableMap<String, Any>? = HashMap()

    protected constructor(`in`: Parcel) {
        speed = `in`.readValue(Double::class.java.classLoader) as Double?
        deg = `in`.readValue(Int::class.java.classLoader) as Int?
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
        dest.writeValue(speed)
        dest.writeValue(deg)
        dest.writeValue(additionalProperties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Wind?> = object : Parcelable.Creator<Wind?> {
            override fun createFromParcel(`in`: Parcel): Wind? {
                return Wind(`in`)
            }

            override fun newArray(size: Int): Array<Wind?> {
                return arrayOfNulls(size)
            }
        }
    }
}