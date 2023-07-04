package com.example.testinterview.presentation.view.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.testinterview.App
import com.example.testinterview.R
import com.example.testinterview.databinding.FragmentAddQuestionBinding
import com.example.testinterview.domain.model.Category
import com.example.testinterview.presentation.view.dialogs.MoreTopicDialogFragment
import com.example.testinterview.presentation.view.dialogs.MoreTopicDialogListener
import com.example.testinterview.presentation.viewmodel.AddQuestionViewModel
import com.example.testinterview.presentation.viewmodel.CategoryState
import com.example.testinterview.presentation.viewmodel.Error
import com.example.testinterview.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class AddQuestionFragment : Fragment() {

    private var _binding: FragmentAddQuestionBinding? = null
    private val binding: FragmentAddQuestionBinding
        get() = _binding ?: throw RuntimeException("FragmentAddQuestionBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AddQuestionViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private var category = Category.LANGUAGE

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setListeners()
        setupMoreTopicDialogListener()
    }

    private fun setListeners() {
        with(binding) {
            buttonLanguageCategory.setOnClickListener {
                viewModel.switchCategory(Category.LANGUAGE)
            }

            buttonAndroidCategory.setOnClickListener {
                viewModel.switchCategory(Category.ANDROID)
            }

            buttonSave.setOnClickListener {
                viewModel.addQuestion(
                    category = category,
                    topic = etTopic.text.toString(),
                    title = etQuestion.text.toString(),
                    answer =  etAnswer.text.toString()
                )
                parentFragmentManager.popBackStack()
            }

            buttonCancel.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            buttonMore.setOnClickListener {
                showMoreTopicDialogFragment()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Error -> setEditTextError()
                is CategoryState -> {
                    category = if (state.category == Category.LANGUAGE) {
                        setLanguageCategory()
                        Category.LANGUAGE
                    } else {
                        setAndroidCategory()
                        Category.ANDROID
                    }
                }
            }
        }
    }

    private fun showMoreTopicDialogFragment() {
        MoreTopicDialogFragment.show(parentFragmentManager)
    }

    private fun setupMoreTopicDialogListener() {
        val listener: MoreTopicDialogListener = { topicName ->
            binding.etTopic.setText(topicName)
        }

        MoreTopicDialogFragment.setupListener(
            parentFragmentManager,
            viewLifecycleOwner,
            listener
        )
    }

    private fun setLanguageCategory() {
        with(binding) {
            buttonLanguageCategory.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
            buttonLanguageCategory.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            buttonAndroidCategory.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            buttonAndroidCategory.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark))
        }

    }

    private fun setAndroidCategory() {
        with(binding) {
            buttonAndroidCategory.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
            buttonAndroidCategory.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            buttonLanguageCategory.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            buttonLanguageCategory.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark))
        }

    }

    private fun setEditTextError() {
        with(binding) {
            if(etTopic.text.isNullOrEmpty()) etTopic.error = getString(R.string.empty_field_error)
            if(etQuestion.text.isNullOrEmpty()) etQuestion.error = getString(R.string.empty_field_error)
            if(etAnswer.text.isNullOrEmpty()) etAnswer.error = getString(R.string.empty_field_error)
        }
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