package com.example.testinterview.presentation.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testinterview.R
import com.example.testinterview.databinding.FragmentInterviewBinding


class InterviewFragment : Fragment() {

    private var _binding: FragmentInterviewBinding? = null
    private val binding: FragmentInterviewBinding
        get() = _binding ?: throw RuntimeException("FragmentInterviewBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): InterviewFragment {
            return InterviewFragment()
        }
    }
}