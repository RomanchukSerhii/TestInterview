package com.example.testinterview.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testinterview.R
import com.example.testinterview.databinding.FragmentListTopicBinding

class ListTopicFragment : Fragment() {
    private var _binding: FragmentListTopicBinding? = null
    private val binding: FragmentListTopicBinding
        get() = _binding ?: throw RuntimeException("FragmentListTopicBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTopicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): ListTopicFragment {
            return ListTopicFragment()
        }
    }
}