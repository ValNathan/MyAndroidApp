package com.example.myapplication.details

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.Product
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fiche.*
import java.io.File
import java.lang.Exception

open class BaseFragment : Fragment(){

    companion object {
        public val ARG_PRODUCT = "product"
    }

    lateinit var product : Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(arguments == null || arguments?.containsKey(ARG_PRODUCT) == false){
            throw Exception("missing product")
        }

        product = arguments!!.getParcelable<Product>(ARG_PRODUCT)!!


    }


}

class Fiche : BaseFragment(){
    companion object {
        fun newInstance(product: Product) : Fiche {
            val fragment = Fiche()
            val args = Bundle()
            args.putParcelable(ARG_PRODUCT, product)
            fragment.arguments = args



            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fiche, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(product.urlImage).into(image)

        titre.text = product.nom
        cassegrain.text = product.marque.toString()
        codebarre.text = getString(R.string.barcode, product.barcode).toHtml()
        quantite.text = getString(R.string.quantity, product.Quantite).toHtml()
        venduen.text = getString(R.string.sold, product.Vendu).toHtml()
        ingredients.text = getString(R.string.ingredients, product.ingredigent).toHtml()
        substances.text = getString(R.string.substances, product.subsAllergene).toHtml()
        additifs.text = getString(R.string.additives, product.additifs).toHtml()



        if(product.nutriscore == "a"){
            Picasso.get().load(R.drawable.nutri_score_a).into(nutri_score)
        }
        else if(product.nutriscore == "b"){
            Picasso.get().load(R.drawable.nutri_score_b).into(nutri_score)
        }
        else if(product.nutriscore == "c"){
            Picasso.get().load(R.drawable.nutri_score_c).into(nutri_score)
        }
        else if(product.nutriscore == "d"){
            Picasso.get().load(R.drawable.nutri_score_d).into(nutri_score)
        }
        else{
            Picasso.get().load(R.drawable.nutri_score_e).into(nutri_score)
        }


    }

    fun String.toHtml(): Spanned {
        return Html.fromHtml(this)
    }

}

class Nutrition : BaseFragment(){
    companion object {
        fun newInstance(product: Product) : Nutrition {
            val fragment = Nutrition()
            val args = Bundle()
            args.putParcelable(ARG_PRODUCT, product)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.nutrition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}

class InfoN : BaseFragment(){
    companion object {
        fun newInstance(product: Product) : InfoN {
            val fragment = InfoN()
            val args = Bundle()
            args.putParcelable(ARG_PRODUCT, product)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.infos_nutritionnelles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

class LastSearch : BaseFragment(){
    companion object {
        fun newInstance(product: Product) : InfoN {
            val fragment = InfoN()
            val args = Bundle()
            args.putParcelable(ARG_PRODUCT, product)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}