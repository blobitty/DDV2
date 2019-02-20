package nyc.bbah.ddv2.model

import android.os.Parcel
import android.os.Parcelable


data class Restaurant(val delivery_fee: Int?,
                      val id: Int?,
                      val status: String?,
                      val description: String?,
                      val cover_img_url: String?,
                      val name: String?): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(delivery_fee)
        parcel.writeValue(id)
        parcel.writeString(status)
        parcel.writeString(description)
        parcel.writeString(cover_img_url)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Restaurant> {
        override fun createFromParcel(parcel: Parcel): Restaurant {
            return Restaurant(parcel)
        }

        override fun newArray(size: Int): Array<Restaurant?> {
            return arrayOfNulls(size)
        }
    }


}