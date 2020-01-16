package com.thudlm.vegetarianres.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.thudlm.vegetarianres.R
import com.thudlm.vegetarianres.dependencies.entities.Category
import com.thudlm.vegetarianres.dependencies.entities.Product
import com.thudlm.vegetarianres.dependencies.presenter.category.CategoryPresenter
import com.thudlm.vegetarianres.dependencies.presenter.product.ProductPresenter
import com.thudlm.vegetarianres.utils.AppContants.CATEGORIES_DATA
import com.thudlm.vegetarianres.utils.AppContants.COOK_METHOD
import com.thudlm.vegetarianres.utils.AppContants.NAME_DATA
import com.thudlm.vegetarianres.utils.AppContants.PRODUCT_IMAGES
import com.thudlm.vegetarianres.utils.AppContants.RECIPE_VIDEO_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class SplashActivity : AppCompatActivity() {

    private val categoryPresenter: CategoryPresenter by viewModel()

    private val productPresenter: ProductPresenter by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        checkIfDatabaseHasData()

    }

    //Check everytime app start
    //If database doesn't contain any category, create random data
    //Else go to home
    private fun checkIfDatabaseHasData() {
        categoryPresenter.categories.observe(this, Observer {
            if (it.isEmpty()) {
                createData()
            }else {
                GlobalScope.launch(context = Dispatchers.Main) {
                    delay(2000)
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        })
    }

    private fun createData() {
        GlobalScope.launch {

            //Remove all data before create new one
            categoryPresenter.deleteAll()
            productPresenter.deleteAll()

            for (i in CATEGORIES_DATA.indices) {
                val category = Category(CATEGORIES_DATA[i])
                categoryPresenter.insert(category)
                for (j in 0 until 20){
                    val product = Product()
                    product.categoryId = i+1
                    product.thumbnail = PRODUCT_IMAGES[(0 until PRODUCT_IMAGES.size-1).random()]
                    product.recipeVideoUrl = RECIPE_VIDEO_URL[(0 until RECIPE_VIDEO_URL.size-1).random()]
                    if(CATEGORIES_DATA[i] == "Drinks"){
                        product.name = "${NAME_DATA[(0 until NAME_DATA.size-1).random()]} juice"
                    }else{
                        product.name = "${COOK_METHOD[(0 until COOK_METHOD.size-1).random()]} ${NAME_DATA[(0 until NAME_DATA.size-1).random()]} ${CATEGORIES_DATA[i].toLowerCase(Locale.ENGLISH)}"
                    }
                    productPresenter.insert(product)
                }
            }
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }

}
