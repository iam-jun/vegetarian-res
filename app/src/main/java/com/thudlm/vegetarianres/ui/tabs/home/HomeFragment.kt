package com.thudlm.vegetarianres.ui.tabs.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thudlm.vegetarianres.R
import com.thudlm.vegetarianres.dependencies.entities.Category
import com.thudlm.vegetarianres.dependencies.entities.ProductCategory
import com.thudlm.vegetarianres.dependencies.presenter.category.CategoryPresenter
import com.thudlm.vegetarianres.dependencies.presenter.product.ProductPresenter
import com.thudlm.vegetarianres.ui.activities.ProductDetailActivity
import com.thudlm.vegetarianres.ui.adapter.ProductHomeAdapter
import com.thudlm.vegetarianres.ui.callback.RecyclerViewItemClick
import com.thudlm.vegetarianres.utils.AppContants.CATEGORIES_DATA
import com.thudlm.vegetarianres.utils.AppContants.VIEW_TYPE_GRID
import com.thudlm.vegetarianres.utils.AppContants.VIEW_TYPE_LIST
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), RecyclerViewItemClick {

    override fun onItemClick(position: Int) {
        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra("product_id", products[position].product!!.id)
        startActivity(intent)
    }

    private val productPresenter: ProductPresenter by viewModel()
    private val categoryPresenter: CategoryPresenter by viewModel()
    private var productHomeAdapter: ProductHomeAdapter? = null
    private var categoriesAdater: ArrayAdapter<String>? = null
    private val products = ArrayList<ProductCategory>()
    private val categories = ArrayList<Category>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var icBack: ImageView
    private lateinit var icSearch: ImageView
    private lateinit var icViewMode: ImageView

    private var viewMode = VIEW_TYPE_LIST

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        spinner = root.findViewById(R.id.spinner)
        icBack = root.findViewById(R.id.icBack)
        icSearch = root.findViewById(R.id.icSearch)
        icViewMode = root.findViewById(R.id.icViewMode)

        initUI()
        initData()
        return root
    }

    private fun initUI() {
        categoriesAdater =
            ArrayAdapter(context!!, android.R.layout.simple_list_item_single_choice, CATEGORIES_DATA)
        spinner.adapter = categoriesAdater

        productHomeAdapter = ProductHomeAdapter(context!!, products, VIEW_TYPE_LIST, this)
        displayList()
        recyclerView.adapter = productHomeAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (categories.isNotEmpty())
                    productPresenter.loadProductByCategory(categories[position].id!!)
            }

        }

        icBack.setOnClickListener {
            activity!!.finish()
        }

        icViewMode.setOnClickListener {
            switchViewMode()
        }

    }

    private fun switchViewMode() {
        if (viewMode == VIEW_TYPE_LIST) {
            displayGrid()
        } else {
            displayList()
        }
        productHomeAdapter = ProductHomeAdapter(context!!, products, viewMode, this)
        recyclerView.adapter = productHomeAdapter

    }

    private fun displayList() {
        viewMode = VIEW_TYPE_LIST
        icViewMode.setImageResource(R.drawable.ic_grid_white_24dp)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun displayGrid() {
        viewMode = VIEW_TYPE_GRID
        icViewMode.setImageResource(R.drawable.ic_list_white_24dp)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    private fun initData() {
        productPresenter.loadProductByCategory(1)
        categoryPresenter.categories.observe(viewLifecycleOwner, Observer {
            it.let {
                categories.clear()
                categories.addAll(it)
                categoriesAdater!!.notifyDataSetChanged()
            }
        })
        productPresenter.products.observe(viewLifecycleOwner, Observer {
            it.let {
                products.clear()
                products.addAll(it)
                productHomeAdapter!!.notifyDataSetChanged()
            }
        })

    }
}