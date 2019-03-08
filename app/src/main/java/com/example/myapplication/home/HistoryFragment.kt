package com.example.myapplication.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebHistoryItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.History
import com.example.myapplication.Product
import com.example.myapplication.R
import com.example.myapplication.details.DetailsActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*

class HistoryFragment : Fragment(), OnProductClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val database = FirebaseDatabase.getInstance()
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val history = mutableListOf<History>()

                for (item in dataSnapshot.children) {
                    val historyItem = item.getValue(History::class.java)

                    if (historyItem != null) {
                        history.add(historyItem)

                    }
                }


                val products = history.map {
                    it.toProduct()
                }

                recyclerview.adapter = ListAdapter(products, this@HistoryFragment)
            }


        }

        database.getReference("Users/user_id/history").addValueEventListener(listener)



    }

    override fun onProductClicked(product: Product) {
        //Toast.makeText(requireActivity(), "J'ai cliqué", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("product", product.barcode)
        startActivity(intent)
    }

    override fun onBookmarkClicked(product: Product) {
        Toast.makeText(requireActivity(), "Added Bookmark", Toast.LENGTH_SHORT).show()
    }


}

//adapter pour la liste
class ListAdapter(val products: List<Product>, val listener: OnProductClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductCell(
            inflater.inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
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
    val bookmark: ImageView = v.ic_bookmark
    val imgView: ImageView = v.img_petits_pois

    fun bindProduct(product: Product, listener: OnProductClickListener) {
        productNameView.text = product.nom
        productBrandView.text = product.marque.toString()
        productNSView.text = product.nutriscore
        productCalView.text = product.calorie.toString()

        cardview.setOnClickListener {
            listener.onProductClicked(product)
        }

        bookmark.setOnClickListener {
            listener.onBookmarkClicked(product)
        }
    }
}

interface OnProductClickListener {
    fun onProductClicked(product: Product)
    fun onBookmarkClicked(product: Product)
}