package com.mubaihaqi.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Articles(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
