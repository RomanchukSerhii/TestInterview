package com.example.testinterview.presentation.view.screens

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.testinterview.App
import com.example.testinterview.R
import com.example.testinterview.databinding.FragmentInterviewBinding
import com.example.testinterview.domain.model.Question
import com.example.testinterview.presentation.view.adapters.QuestionItemActionListener
import com.example.testinterview.presentation.view.adapters.QuestionListAdapter
import com.example.testinterview.presentation.viewmodel.InterviewViewModel
import com.example.testinterview.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject


class InterviewFragment : Fragment() {

    private var _binding: FragmentInterviewBinding? = null
    private val binding: FragmentInterviewBinding
        get() = _binding ?: throw RuntimeException("FragmentInterviewBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[InterviewViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private val questionListAdapter by lazy {
        val actionListener = getQuestionItemActionListener()
        QuestionListAdapter(actionListener)
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding.viewPager.adapter = questionListAdapter
    }

    private fun observeViewModel() {
        viewModel.questionListLD.observe(viewLifecycleOwner) {
            questionListAdapter.submitList(it)
        }
    }

    private fun getQuestionItemActionListener(): QuestionItemActionListener {
        return object : QuestionItemActionListener {
            override fun onDeleteButtonClick(question: Question) {
                val listener = DialogInterface.OnClickListener { _, which ->
                    when(which) {
                        DialogInterface.BUTTON_POSITIVE -> viewModel.deleteQuestion(question)
                    }

                }
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.delete_question)
                    .setMessage(R.string.delete_question_message)
                    .setNegativeButton(R.string.no, listener)
                    .setPositiveButton(R.string.confirm, listener)
                    .create()
                    .show()

            }

            override fun onEditButtonClick(question: Question) {
                viewModel.editQuestion(question)
            }
        }
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