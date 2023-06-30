package com.example.testinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testinterview.databinding.ActivityMainBinding
import com.example.testinterview.presentation.view.ListTopicFragment
import com.example.testinterview.presentation.view.MainFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}