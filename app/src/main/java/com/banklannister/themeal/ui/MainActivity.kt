package com.banklannister.themeal.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.banklannister.themeal.adapter.MealAdapter
import com.banklannister.themeal.databinding.ActivityMainBinding
import com.banklannister.themeal.viewmodel.MealViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mealViewModel: MealViewModel by viewModels()
    private lateinit var mealAdapter: MealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        mealAdapter = MealAdapter { category ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_CATEGORY, category)
            }
            startActivity(intent)
        }
        binding.apply {
            recyclerView.adapter = mealAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            mealViewModel.categories.collect { categories ->
                mealAdapter.updateCategories(categories)

            }
        }
    }

}