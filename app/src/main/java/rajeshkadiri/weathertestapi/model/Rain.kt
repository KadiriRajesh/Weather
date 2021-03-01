package rajeshkadiri.weathertestapi.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.*
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("3h")
class Rain : Parcelable {
    @JsonProperty("3h")
    private var _3h: Int? = null

    @JsonIgnore
    private var additionalProperties: MutableMap<String, Any>? = HashMap()

    protected constructor(`in`: Parcel) {
        _3h = `in`.readValue(Int::class.java.classLoader) as Int?
        additionalProperties = `in`.readValue(MutableMap::class.java.classLoader) as MutableMap<String, Any>?
    }

    constructor() {}

    @JsonProperty("3h")
    fun get3h(): Int? {
        return _3h
    }

    @JsonProperty("3h")
    fun set3h(_3h: Int?) {
        this._3h = _3h
    }

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any>? {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties!![name] = value
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(_3h)
        dest.writeValue(additionalProperties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Rain?> = object : Parcelable.Creator<Rain?> {
            override fun createFromParcel(`in`: Parcel): Rain? {
                return Rain(`in`)
            }

            override fun newArray(size: Int): Array<Rain?> {
                return arrayOfNulls(size)
            }
        }
    }
}