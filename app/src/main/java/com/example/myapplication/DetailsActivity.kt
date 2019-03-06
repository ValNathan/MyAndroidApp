package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.text.Spanned
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details_pager.*
import kotlinx.android.synthetic.main.list_item.*
import java.lang.Exception

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!intent.hasExtra("product")){
            throw Exception("missing product")
        }

        setContentView(R.layout.activity_details_pager)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.mes_produits))
        supportActionBar?.setTitle(R.string.details)

        val product = intent.getParcelableExtra<Product>("product")
        pager.adapter = ProductDetailsAdapter(supportFragmentManager, product)
        /*
        titre.text = product.nom


        codebarre.text = getString(R.string.barcode, product.barcode).toHtml()
        quantite.text = getString(R.string.quantity, product.Quantite).toHtml()
        venduen.text = getString(R.string.sold, product.Vendu).toHtml()
        ingredients.text = getString(R.string.ingredients, product.ingredigent).toHtml()
        substances.text = getString(R.string.substances, product.subsAllergene).toHtml()
        additifs.text = getString(R.string.additives, product.additifs).toHtml()*/



    }

    fun String.toHtml(): Spanned {
        return Html.fromHtml(this)
    }


}

class ProductDetailsAdapter(fm : FragmentManager, private val product: Product) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> Fragment1.newInstance(product)
            1 -> Fragment2.newInstance(product)
            2 -> Fragment3.newInstance(product)
            else -> throw Exception("WTF")
        }
    }

    override fun getCount(): Int = 3
}