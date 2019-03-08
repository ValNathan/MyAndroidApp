package com.example.myapplication.details

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.NetworkManager
import com.example.myapplication.Product
import com.example.myapplication.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_details_pager.*
import kotlinx.android.synthetic.main.fiche.*
import kotlinx.coroutines.*
import java.lang.Exception

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!intent.hasExtra("barcode")){
            throw Exception("missing product")
        }

        setContentView(R.layout.activity_details_pager)
        setSupportActionBar(toolbar)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this,
            R.drawable.mes_produits
        ))
        supportActionBar?.setTitle(R.string.details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val barcode = intent.getStringExtra("barcode")
        val database = FirebaseDatabase.getInstance()

        GlobalScope.launch(Dispatchers.Main) {
            viewpager.visibility = View.GONE
            progressbar.visibility = View.VISIBLE
            notfound.visibility = View.GONE
            val product = withContext(Dispatchers.IO) {
                NetworkManager.getProduct(barcode)
            }
            if(product != null){
                progressbar.visibility = View.GONE
                viewpager.visibility = View.VISIBLE

                val map = hashMapOf("Name" to product.nom, "BarCode" to product.barcode, "Brand" to product.marque, "Nutriscore" to product.nutriscore, "Cal" to product.calorie, "Image" to product.urlImage)
                val task = database.getReference("Users/user_id/history/${System.currentTimeMillis()}").updateChildren(map as Map<String, Any>)

                task.addOnSuccessListener{}
                task.addOnCompleteListener{}
                task.addOnFailureListener{}


                /*titre.text = product.nom
                codebarre.text = getString(R.string.barcode, product.barcode).toHtml()
                quantite.text = getString(R.string.quantity, product.Quantite).toHtml()
                venduen.text = getString(R.string.sold, product.Vendu).toHtml()
                ingredients.text = getString(R.string.ingredients, product.ingredigent).toHtml()
                substances.text = getString(R.string.substances, product.subsAllergene).toHtml()
                additifs.text = getString(R.string.additives, product.additifs).toHtml()*/

                viewpager.adapter = ProductDetailsAdapter(supportFragmentManager, product)
                tabs.setupWithViewPager(viewpager)



            } else {
                Log.d("404", "Not found")
                notfound.visibility = View.VISIBLE
                progressbar.visibility = View.GONE



            }

        }
    }

    fun String.toHtml(): Spanned {
        return Html.fromHtml(this)
    }


}

class ProductDetailsAdapter(fm : FragmentManager, private val product: Product) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> Fiche.newInstance(product)
            1 -> Nutrition.newInstance(product)
            2 -> InfoN.newInstance(product)
            else -> throw Exception("WTF")
        }
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Fiche"
            1 -> "Nutrition"
            2 -> "Infos nutritionnelles"
            else -> "WTF"
        }
    }
}