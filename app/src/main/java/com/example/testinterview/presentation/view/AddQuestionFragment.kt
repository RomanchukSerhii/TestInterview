package com.example.testinterview.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testinterview.R
import com.example.testinterview.databinding.FragmentAddQuestionBinding

class AddQuestionFragment : Fragment() {
    private var _binding: FragmentAddQuestionBinding? = null
    private val binding: FragmentAddQuestionBinding
        get() = _binding ?: throw RuntimeException("FragmentAddQuestionBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): AddQuestionFragment {
            return AddQuestionFragment()
        }
    }
}