package com.example.myapplication

data class History(
    val name: String?,
    val barcode: String,
    val nutriscore: String?,
    val calorie: String?,
    val image: String?
) {

    constructor() : this(null, "", null, null, null)

    fun toProduct(): Product {
        return Product(
            nom = name,
            barcode = barcode,
            nutriscore = nutriscore,
            calorie = calorie,
            urlImage = image
        )
    }

}