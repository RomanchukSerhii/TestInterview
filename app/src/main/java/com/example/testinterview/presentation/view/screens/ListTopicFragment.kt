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
import com.example.testinterview.databinding.FragmentListTopicBinding
import com.example.testinterview.domain.model.Topic
import com.example.testinterview.presentation.view.adapters.TopicItemActionListener
import com.example.testinterview.presentation.view.adapters.TopicListAdapter
import com.example.testinterview.presentation.view.adapters.TopicListLaunchMode
import com.example.testinterview.presentation.viewmodel.ListTopicViewModel
import com.example.testinterview.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class ListTopicFragment : Fragment() {
    private var _binding: FragmentListTopicBinding? = null
    private val binding: FragmentListTopicBinding
        get() = _binding ?: throw RuntimeException("FragmentListTopicBinding is null")

    private val topicListAdapter by lazy {
        val actionListener = getTopicItemActionListener()
        TopicListAdapter(TopicListLaunchMode.FRAGMENT, actionListener)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ListTopicViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTopicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding.recyclerView.adapter = topicListAdapter
    }

    private fun observeViewModel() {
        viewModel.initList(this)
        viewModel.filteredTopicListLD.observe(viewLifecycleOwner) {
            topicListAdapter.submitList(it)
        }
    }

    private fun getTopicItemActionListener(): TopicItemActionListener {
        return object : TopicItemActionListener {
            override fun onTopicItemClick(topic: Topic) {
            }

            override fun onDeleteButtonClick(topic: Topic) {
                val listener = DialogInterface.OnClickListener { _, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            viewModel.deleteTopic(topic)
                        }
                    }
                }

                AlertDialog.Builder(requireContext())
                    .setCancelable(true)
                    .setTitle(getString(R.string.delete_topic))
                    .setMessage(getString(R.string.delete_topic_description))
                    .setNegativeButton(R.string.no, listener)
                    .setPositiveButton(R.string.confirm, listener)
                    .create()
                    .show()
            }

            override fun onPlayButtonClick(topic: Topic) {
            }
        }
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