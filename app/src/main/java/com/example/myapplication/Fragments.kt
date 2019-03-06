package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.fiche.*
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

class Fragment1 : BaseFragment(){
    companion object {
        fun newInstance(product: Product) : Fragment1 {
            val fragment = Fragment1()
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
    }

}

class Fragment2 : BaseFragment(){
    companion object {
        fun newInstance(product: Product) : Fragment2 {
            val fragment = Fragment2()
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

class Fragment3 : BaseFragment(){
    companion object {
        fun newInstance(product: Product) : Fragment3 {
            val fragment = Fragment3()
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