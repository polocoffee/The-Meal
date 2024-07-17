package com.banklannister.themeal.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.banklannister.themeal.R
import com.banklannister.themeal.databinding.ActivityDetailBinding
import com.banklannister.themeal.model.Category

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getParcelableExtra<Category>(EXTRA_CATEGORY)
        category?.let {
            binding.textTitle.text = it.strCategory
            binding.textDescription.text = it.strCategoryDescription
            binding.imageDetail.load(it.strCategoryThumb) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
            }
        }

    }


    companion object {
        const val EXTRA_CATEGORY = "extra_category"
    }
}