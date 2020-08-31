package com.example.euvictodoist.ui.todos

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import com.example.euvictodoist.databinding.FragmentTodosBinding
import com.example.euvictodoist.viewmodels.TodosViewModel
import org.koin.android.ext.android.inject


class TodosFragment : Fragment() {

    val todosViewModel : TodosViewModel by inject()
    lateinit var binding: FragmentTodosBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodosBinding.inflate(LayoutInflater.from(context), container, false)
        binding.apply {
            lifecycleOwner = this@TodosFragment
            viewModel = todosViewModel // Setting the .xml ViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        initSearchBar()
        binding.loading.visibility = View.VISIBLE
        todosViewModel.requestTodosList() // Requests the TODOS list from the API.
        todosViewModel.todoList.observe({lifecycle}) {
            todosViewModel.setList(it)
            binding.rvTodoList.scheduleLayoutAnimation()
            binding.loading.visibility = View.GONE
        }
    }

    private fun initSearchBar() {
        val todosAdapter = todosViewModel.adapter
        binding.btnSearch.setOnClickListener {
            if (binding.etFilter.text.toString().isNotBlank()) {
                val userId = Integer.parseInt(binding.etFilter.text.toString())
                todosAdapter.clearFilters()
                todosAdapter.getTodosByUserId(userId)
                binding.rvTodoList.scheduleLayoutAnimation()
                hideKeyboard()
            }
        }

        binding.etFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(binding.etFilter.text.toString().isBlank()){
                    todosAdapter.clearFilters()
                    binding.rvTodoList.scheduleLayoutAnimation()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        } )

        binding.etFilter.setOnEditorActionListener(
            OnEditorActionListener { v, actionId, event -> // Identifier of the action. This will be either the identifier you supplied,
                // or EditorInfo.IME_NULL if being called due to the enter key being pressed.
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.btnSearch.callOnClick()
                    return@OnEditorActionListener true
                }
                // Return true if you have consumed the action, else false.
                false
            })
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0)
    }
}