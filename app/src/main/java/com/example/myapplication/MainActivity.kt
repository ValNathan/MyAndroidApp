package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.nfc.tech.NfcBarcode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity(), OnProductClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.mes_produits))


/*
        val product = Product("Petit pois et carottes", "Cassegrain", "3083680085304", "e","https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
            "400 g(280 g net égoutté)", listOf ("France", "Japon", "Suisse"), listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"),
            listOf("Aucune"), listOf("Aucun"))
        codebarre.text = getString(R.string.barcode, product.barcode).toHtml()
        quantite.text = getString(R.string.quantity, product.Quantite).toHtml()
        venduen.text = getString(R.string.sold, product.Vendu).toHtml()
        ingredients.text = getString(R.string.ingredients, product.ingredigent).toHtml()
        substances.text = getString(R.string.substances, product.subsAllergene).toHtml()
        additifs.text = getString(R.string.additives, product.additifs).toHtml()

        nutri_score.setImageResource(
            resources.getIdentifier(
                "nutriscore_${product.nutriscore}",
                "drawable",
                packageName

            )
        )*/


        val products = listOf(
            Product("Petits pois et carottes", "Cassegrain",  "4561348331864694", "A", "image.src", "32", listOf ("France", "Japon", "Suisse"), listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"), listOf("Aucune"), listOf("Aucun")),
            Product("Chili", "Cassegrain",  "4561348331864694", "A", "image.src", "32", listOf ("France", "Japon", "Suisse"), listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"), listOf("Aucune"), listOf("Aucun")),
            Product("Omelette", "Cassegrain",  "4561348331864694", "A", "image.src", "32", listOf ("France", "Japon", "Suisse"), listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"), listOf("Aucune"), listOf("Aucun")),
            Product("Salade", "Cassegrain",  "4561348331864694", "A", "image.src", "32", listOf ("France", "Japon", "Suisse"), listOf("Petits pois 66%", "eau", "garniture 2,8% (salade, oignon grelot)", "sucre", "sel", "arôme naturel"), listOf("Aucune"), listOf("Aucun"))

        )
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = ListAdapter(products, this)




        Log.v("Hello", "Hello World !!")

        Toast.makeText(this, "Hello world", Toast.LENGTH_SHORT).show()


    }

    override fun onProductClicked(product: Product) {
        Toast.makeText(this, "J'ai cliqué", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }

    override fun onBookmarkClicked(product: Product){
        Toast.makeText(this, "Added Bookmark", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.barcode_menu -> {
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}

//adapter pour la liste
class ListAdapter(val products: List<Product>, val listener: OnProductClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductCell(inflater.inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductCell).bindProduct(products[position], listener)
    }
}


//une cellule de la liste
class ProductCell(v: View) : RecyclerView.ViewHolder(v) {
    val productNameView: TextView = v.product_name
    val productBrandView: TextView = v.product_brand
    val productNSView: TextView = v.nutriscore_text
    val productCalView: TextView = v.cal_text
    val cardview: CardView = v.cardview
    val bookmark : ImageView = v.ic_bookmark

    fun bindProduct(product: Product, listener: OnProductClickListener) {
        productNameView.text = product.nom
        productBrandView.text = product.marque
        productNSView.text = product.nutriscore
        productCalView.text = "1234" //product.kcal.toString()

        cardview.setOnClickListener {
            listener.onProductClicked(product)
        }

        bookmark.setOnClickListener{
            listener.onBookmarkClicked(product)
        }
    }
}

interface OnProductClickListener {
    fun onProductClicked(product: Product)
    fun onBookmarkClicked(product: Product)
}
/*
fun String.toHtml(): Spanned {
    return Html.fromHtml(this)
}

data class Product(val nom: String, val marque: String, val barcode: String, val nutriscore: String, val urlImage: String, val Quantite: String,
                   val Vendu: List<String>, val ingredigent: List<String>, val subsAllergene: List<String>, val additifs: List<String>){

}*/

//data class Product(val name: String, val brand: String){ }