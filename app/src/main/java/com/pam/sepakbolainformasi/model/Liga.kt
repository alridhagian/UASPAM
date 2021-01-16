package com.pam.sepakbolainformasi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Liga(
    val name: String?,
    val image: Int?,
    val id: String? = null
): Parcelable