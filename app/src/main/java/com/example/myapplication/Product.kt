package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val nom: String, val marque: String, val barcode: String, val nutriscore: String, val urlImage: String, val Quantite: String,
                   val Vendu: List<String>, val ingredigent: List<String>, val subsAllergene: List<String>, val additifs: List<String>) :
    Parcelable {

}