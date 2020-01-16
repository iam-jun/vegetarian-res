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
import androidx.recyclerview.widget.LinearLayoutManager
import com.thudlm.vegetarianres.dependencies.entities.ExtraFood
import com.thudlm.vegetarianres.dependencies.presenter.extrafood.ExtraFoodPresenter
import com.thudlm.vegetarianres.dependencies.presenter.product.ProductPresenter
import com.thudlm.vegetarianres.ui.adapter.ExtraAdapter
import com.thudlm.vegetarianres.ui.callback.RecyclerViewItemClick
import com.thudlm.vegetarianres.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : AppCompatActivity(), RecyclerViewItemClick {

    override fun onItemClick(position: Int) {
        extraAdapter.selectedPosition = if(position == extraAdapter.selectedPosition) -1 else position
        extraAdapter.notifyDataSetChanged()
    }

    private val productPresenter: ProductPresenter by viewModel()
    private val extraFoodPresenter: ExtraFoodPresenter by viewModel()
    private val extras = ArrayList<ExtraFood>()
    private lateinit var extraAdapter: ExtraAdapter
    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.thudlm.vegetarianres.R.layout.activity_product_detail)
        title = ""
        initUI()
        initData()
    }

    @SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
    private fun initUI(){
        webView.settings.javaScriptEnabled = true
        webView.settings.pluginState = PluginState.ON
        webView.webChromeClient = WebChromeClient()
        extraAdapter = ExtraAdapter(this, extras, -1, this)
        rvExtras.layoutManager = LinearLayoutManager(this)
        rvExtras.adapter = extraAdapter

        btnAdd.setOnClickListener {
            tvQuantity.text = "${++quantity} plates"
        }

        btnSub.setOnClickListener {
            if(quantity > 1) tvQuantity.text = "${--quantity} plates"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initData(){
        extraFoodPresenter.loadExtraFoods()
        extraFoodPresenter.extraFoods.observe(this, Observer {
            extras.clear()
            it?.let {
                extras.addAll(it)
                extraAdapter.notifyDataSetChanged()
            }
        })
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
