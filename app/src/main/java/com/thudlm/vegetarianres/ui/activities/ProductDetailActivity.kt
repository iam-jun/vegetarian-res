package com.thudlm.vegetarianres.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings.PluginState
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.thudlm.vegetarianres.dependencies.presenter.product.ProductPresenter
import com.thudlm.vegetarianres.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : AppCompatActivity() {

    private val productPresenter: ProductPresenter by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.thudlm.vegetarianres.R.layout.activity_product_detail)
        title = ""
        initUI()
        initData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initUI(){
        webView.settings.javaScriptEnabled = true
        webView.settings.pluginState = PluginState.ON
        webView.webChromeClient = WebChromeClient()
    }

    @SuppressLint("SetTextI18n")
    private fun initData(){
        val productId = intent.getLongExtra("product_id", -1)
        if(productId > 0){
            productPresenter.getProductById(productId)
            productPresenter.product.observe(this, Observer {
                it.let {
                    if(!it.recipeVideoUrl.isNullOrEmpty()){
                        imgProduct.visibility = View.INVISIBLE
                        webView.visibility = View.VISIBLE
                        webView.loadUrl(it.recipeVideoUrl)
                    }else{
                        imgProduct.visibility = View.VISIBLE
                        webView.visibility = View.INVISIBLE
                        GlideUtils.loadNoCache(this, it.thumbnail!!, imgProduct)
                    }

                    tvName.text = it.name
                    tvPrice.text = "$"+it.price
                    tvWeight.text = "${it.weight}g"
                    tvDescription.text = it.description
                    tvIngredients.text = it.ingredients
                    tvCalories.text = "${it.calories}"
                    tvProtein.text = "${it.protein}g"
                    tvTotalFat.text = "${it.totalFat}g"
                    tvTotalCarbs.text = "${it.totalCarbs}g"
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.thudlm.vegetarianres.R.menu.order_menu, menu)
        return true
    }

}
