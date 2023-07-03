package com.example.testinterview.presentation.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.testinterview.App
import com.example.testinterview.R
import com.example.testinterview.databinding.PartMoreTopicDialogBinding
import com.example.testinterview.domain.model.Topic
import com.example.testinterview.presentation.view.adapters.TopicItemActionListener
import com.example.testinterview.presentation.view.adapters.TopicListAdapter
import com.example.testinterview.presentation.viewmodel.ListTopicViewModel
import com.example.testinterview.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

typealias MoreTopicDialogListener = (topicName: String) -> Unit

class MoreTopicDialogFragment : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ListTopicViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private val topicListAdapter by lazy {
        val actionListener = getTopicItemActionListener()
        TopicListAdapter(actionListener)
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        observeViewModel()
        val dialogBinding = PartMoreTopicDialogBinding.inflate(layoutInflater)
        dialogBinding.moreTopicRecyclerView.adapter = topicListAdapter

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.topic_list)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.editTextSearch.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.searchTopic(s.toString())
            }

        })

        return dialog
    }

    private fun observeViewModel() {
        viewModel.initList(this)
        viewModel.filteredTopicListLD.observe(this) {
            topicListAdapter.submitList(it)
        }
    }
    private fun getTopicItemActionListener(): TopicItemActionListener {
        return object : TopicItemActionListener {
            override fun onTopicItemClick(topic: Topic) {
                parentFragmentManager.setFragmentResult(
                    REQUEST_KEY, bundleOf(
                        KEY_TOPIC_RESPONSE to topic.name
                    )
                )
                dismiss()
            }
        }
    }

    companion object {
        private val TAG = MoreTopicDialogFragment::class.java.simpleName
        private const val REQUEST_KEY = "REQUEST_KEY"
        private const val KEY_TOPIC_RESPONSE = "KEY_TOPIC_RESPONSE"

        fun show(manager: FragmentManager) {
            MoreTopicDialogFragment().show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: MoreTopicDialogListener
        ) {
            manager.setFragmentResultListener(
                REQUEST_KEY,
                lifecycleOwner,
                FragmentResultListener { _, result ->
                    listener.invoke(result.getString(KEY_TOPIC_RESPONSE) ?: "")
                }
            )
        }
    }
}