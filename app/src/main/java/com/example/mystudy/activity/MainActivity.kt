package com.example.mystudy.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mystudy.MainViewModel
import com.example.mystudy.activity.MyNote
import com.example.mystudy.R
import com.example.mystudy.databinding.ActivityWordbookBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordbookBinding
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_wordbook
        )
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        binding.homebutton.setOnClickListener{
            val intent = Intent(this, MyNote::class.java)
            startActivity(intent)
        }

    }
}