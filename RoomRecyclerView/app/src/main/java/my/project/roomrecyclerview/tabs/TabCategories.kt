package my.project.roomrecyclerview.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import my.project.roomrecyclerview.R
import my.project.roomrecyclerview.data.Database
import my.project.roomrecyclerview.databinding.TabCategoriesBinding
import my.project.roomrecyclerview.repositories.CategoryRepository
import my.project.roomrecyclerview.viewModels.CategoryFactory
import my.project.roomrecyclerview.viewModels.CategoryViewModel

class TabCategories : Fragment() {

    private var binding: TabCategoriesBinding? = null
    private var categoryRepository: CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var categoryFactory: CategoryFactory? = null
    private var categoryAdapter: CategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_categories, container, false)

        val categoriesDao = Database.getInstance((context as FragmentActivity).application).categoryDao
        categoryRepository = CategoryRepository(categoriesDao)
        categoryFactory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this, categoryFactory!!).get(CategoryViewModel::class.java)

        initRecyclerCategories()
        displayCategories()

//Кнопка для удаления всех категорий
        binding?.deleteAllCategories?.setOnClickListener(View.OnClickListener {
            categoryViewModel?.deleteAll()
        })
//
        return binding?.root

    }

    private fun initRecyclerCategories() {
        binding?.recyclerCategories?.layoutManager = LinearLayoutManager(context)
        categoryAdapter = CategoryAdapter()
        binding?.recyclerCategories?.adapter = categoryAdapter

    }

    private fun displayCategories(){
        categoryViewModel?.categories?.observe(viewLifecycleOwner, Observer {
            categoryAdapter?.setList(it)
            categoryAdapter?.notifyDataSetChanged()
        })
    }
}