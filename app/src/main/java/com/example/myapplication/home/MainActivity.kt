package com.example.myapplication.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Product
import com.example.myapplication.R
import com.example.myapplication.details.DetailsActivity
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.list.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.navigation.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation)

        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this,
            R.drawable.mes_produits
        ))


        main_navigation.setOnNavigationItemSelectedListener {

            val fragment = when(it.itemId) {
                R.id.main_navbar_history -> HistoryFragment()
                R.id.main_navbar_favs -> FavoriteFragment()
                R.id.main_navbar_stats -> StatsFragment()
                R.id.main_navbar_profile -> ProfileFragment()
                else -> throw Exception("")
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment)
                .commitAllowingStateLoss()

            true
        }

        main_navigation.selectedItemId = R.id.main_navbar_history



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
                val intent = Intent()
                intent.setAction("com.google.zxing.client.android.SCAN")
                intent.putExtra(" SCAN_FORMATS", "CODE_39,CODE_93,CODE_128,DATA_MATRIX,ITF,CODABAR")
                val requestCode = 100
                startActivityForResult(intent, requestCode)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}




/*
fun String.toHtml(): Spanned {
    return Html.fromHtml(this)
}

data class Product(val nom: String, val marque: String, val barcode: String, val nutriscore: String, val urlImage: String, val Quantite: String,
                   val Vendu: List<String>, val ingredigent: List<String>, val subsAllergene: List<String>, val additifs: List<String>){

}*/

//data class Product(val name: String, val brand: String){ }