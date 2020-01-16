package com.thudlm.vegetarianres.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.widget.textChanges
import com.thudlm.vegetarianres.R
import com.thudlm.vegetarianres.dependencies.entities.Product
import com.thudlm.vegetarianres.dependencies.presenter.product.ProductPresenter
import com.thudlm.vegetarianres.ui.adapter.RecipeAdapter
import com.thudlm.vegetarianres.ui.adapter.RecipeVideoAdapter
import com.thudlm.vegetarianres.ui.callback.RecipesItemClick
import kotlinx.android.synthetic.main.activity_search_product.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class SearchProductActivity : AppCompatActivity() , RecipesItemClick {

    override fun onItemClick(productId: Long) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("product_id", productId)
        startActivity(intent)
    }

    private val productPresenter: ProductPresenter by viewModel()
    private val recipes = ArrayList<Product>()
    private val recipesVideo = ArrayList<Product>()

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeVideoAdapter: RecipeVideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)
        supportActionBar?.hide()
        initUI()
        initData()
    }

    @SuppressLint("CheckResult")
    private fun initUI() {
        recipeAdapter = RecipeAdapter(this, recipes, this)
        recipeVideoAdapter = RecipeVideoAdapter(this, recipesVideo, this)

        rvRecipe.layoutManager = LinearLayoutManager(this)
        rvRecipe.adapter = recipeAdapter

        rvRecipeVideo.layoutManager = LinearLayoutManager(this)
        rvRecipeVideo.adapter = recipeVideoAdapter

        icClearText.setOnClickListener { edtSearch.setText("") }

        edtSearch.textChanges()
            .debounce(2000, TimeUnit.MILLISECONDS)
            .subscribe {
                productPresenter.searchProduct("%$it%")
            }
    }

    private fun initData(){
        productPresenter.productsSearchResult.observe(this, Observer {
            it?.let {
                recipes.clear()
                recipesVideo.clear()
                for(item in it){
                    if(item.recipeVideoUrl.isNullOrEmpty() && recipes.size < 4) recipes.add(item)
                    else recipesVideo.add(item)
                }
                recipeAdapter.notifyDataSetChanged()
                recipeVideoAdapter.notifyDataSetChanged()
            }
        })
    }

}
