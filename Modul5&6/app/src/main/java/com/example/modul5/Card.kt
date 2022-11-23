package com.example.modul5

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Card(
    val id: Int?,
    val name: String?,
    val img :  String?

    /*var name:String,
    var description:String,
    var photo:Int
*/
):Parcelable
