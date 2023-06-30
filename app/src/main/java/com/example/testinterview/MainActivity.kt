package com.example.testinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.example.testinterview.databinding.ActivityMainBinding
import com.example.testinterview.databinding.TopicItemBinding
import com.example.testinterview.presentation.view.AddQuestionFragment
import com.example.testinterview.presentation.view.ListTopicFragment
import com.example.testinterview.presentation.view.MainFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startMainFragment()
        setMenuListeners()
    }

    private fun startMainFragment() {
        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setMenuListeners() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_question -> {
                    val fragment = AddQuestionFragment.newInstance()
                    startNewFragment(fragment)
                    true
                }
                R.id.topic_list -> {
                    val fragment = ListTopicFragment.newInstance()
                    startNewFragment(fragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun startNewFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}