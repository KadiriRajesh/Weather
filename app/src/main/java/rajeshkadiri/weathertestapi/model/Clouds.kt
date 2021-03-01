package rajeshkadiri.weathertestapi.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("all")
class Clouds : Parcelable {
    @get:JsonProperty("all")
    @set:JsonProperty("all")
    @JsonProperty("all")
    var all: Int? = null

    @JsonIgnore
    private var additionalProperties: MutableMap<String, Any>? = HashMap()

    protected constructor(`in`: Parcel) {
        all = `in`.readValue(Int::class.java.classLoader) as Int?
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
        dest.writeValue(all)
        dest.writeValue(additionalProperties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Clouds?> = object : Parcelable.Creator<Clouds?> {
            override fun createFromParcel(`in`: Parcel): Clouds? {
                return Clouds(`in`)
            }

            override fun newArray(size: Int): Array<Clouds?> {
                return arrayOfNulls(size)
            }
        }
    }
}