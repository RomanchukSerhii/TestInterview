package com.example.testinterview.presentation.view.screens

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.testinterview.App
import com.example.testinterview.R
import com.example.testinterview.databinding.FragmentInterviewBinding
import com.example.testinterview.domain.model.Category
import com.example.testinterview.domain.model.Question
import com.example.testinterview.presentation.view.adapters.QuestionItemActionListener
import com.example.testinterview.presentation.view.adapters.QuestionItemViewHolder
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

    private var questionMode = UNKNOWN_MODE
    private var topicName = UNKNOWN_TOPIC_NAME
    private var shuffleListState = NOT_SHUFFLED

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
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
            var questionList = when (questionMode) {
                GENERAL_MODE -> it
                LANGUAGE_MODE -> {
                    it.filter { question -> question.category == Category.LANGUAGE }
                }
                ANDROID_MODE -> {
                    it.filter { question -> question.category == Category.ANDROID }
                }
                TOPIC_MODE -> {
                    it.filter { question -> question.topic.name == topicName }
                }
                else -> throw RuntimeException("Unknown questionMode: $questionMode")
            }

            if (shuffleListState) {
                questionList = shuffleList(questionList)
            }
            questionListAdapter.submitList(questionList)
        }

        viewModel.isShuffled.observe(viewLifecycleOwner) {
            shuffleListState = it
        }
    }

    private fun shuffleList(questionList: List<Question>): List<Question> {
        return questionList.shuffled()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(KEY_QUESTION_MODE)) {
            throw RuntimeException("Param question mode is absent")
        }

        val mode = args.getString(KEY_QUESTION_MODE)
        if (mode != GENERAL_MODE && mode != LANGUAGE_MODE && mode != ANDROID_MODE && mode != TOPIC_MODE) {
            throw RuntimeException("Unknown question mode: $mode")
        }
        questionMode = mode

        if (questionMode == TOPIC_MODE) {
            if (!args.containsKey(KEY_TOPIC_NAME)) {
                throw RuntimeException("Topic name is absent")
            }
            topicName = args.getString(KEY_TOPIC_NAME, UNKNOWN_TOPIC_NAME)
        }
    }

    private fun getQuestionItemActionListener(): QuestionItemActionListener {
        return object : QuestionItemActionListener {
            override fun onDeleteButtonClick(question: Question) {
                val listener = DialogInterface.OnClickListener { _, which ->
                    when (which) {
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
                val fragment = AddQuestionFragment.newInstanceEditItem(questionId = question.id)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }

            override fun onShowAnswerButtonClick(viewHolder: QuestionItemViewHolder) {
                viewHolder.showAnswer()
            }

            override fun onNextQuestionButtonClick() {
                val currentPosition = binding.viewPager.currentItem

                if (currentPosition < (binding.viewPager.adapter?.itemCount?.minus(1) ?: 0)) {
                    binding.viewPager.currentItem = currentPosition + 1
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val KEY_QUESTION_MODE = "KEY_QUESTION_MODE"
        private const val KEY_TOPIC_NAME = "KEY_TOPIC_NAME"
        private const val GENERAL_MODE = "GENERAL_MODE"
        private const val LANGUAGE_MODE = "LANGUAGE_MODE"
        private const val ANDROID_MODE = "ANDROID_MODE"
        private const val TOPIC_MODE = "TOPIC_MODE"
        private const val UNKNOWN_MODE = ""
        private const val UNKNOWN_TOPIC_NAME = ""
        private const val NOT_SHUFFLED = false


        fun newInstanceGeneralMode(): InterviewFragment {
            return InterviewFragment().apply {
                arguments = bundleOf(
                    KEY_QUESTION_MODE to GENERAL_MODE
                )
            }
        }

        fun newInstanceLanguageMode(): InterviewFragment {
            return InterviewFragment().apply {
                arguments = bundleOf(
                    KEY_QUESTION_MODE to LANGUAGE_MODE
                )
            }
        }

        fun newInstanceAndroidMode(): InterviewFragment {
            return InterviewFragment().apply {
                arguments = bundleOf(
                    KEY_QUESTION_MODE to ANDROID_MODE
                )
            }
        }

        fun newInstanceTopicMode(topicName: String): InterviewFragment {
            return InterviewFragment().apply {
                arguments = bundleOf(
                    KEY_QUESTION_MODE to TOPIC_MODE,
                    KEY_TOPIC_NAME to topicName
                )
            }
        }
    }
}