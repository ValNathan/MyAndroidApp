package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val nom: String?,
    val marque: List<String?>? = null,
    val barcode: String,
    val nutriscore: String?,
    val urlImage: String?,
    val Quantite: String? = null,
    val Vendu: List<String?>? = null,
    val ingredigent: List<String?>? = null,
    val subsAllergene: List<String?>? = null,
    val additifs: Map<String, String>? = null,
    val calorie: String?
) :
    Parcelable {

}