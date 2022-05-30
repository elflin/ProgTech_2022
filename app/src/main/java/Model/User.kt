package Model

import android.os.Parcel
import android.os.Parcelable

data class User (
    var nama:String,
    var alamat:String,
    var no_telp: String,
    var email:String,
    var password:String,
    var id: Int,
    var imageString: String
)