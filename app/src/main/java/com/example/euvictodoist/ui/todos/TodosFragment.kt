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
import androidx.lifecycle.Observer
import com.example.euvictodoist.adapters.TodosAdapter
import com.example.euvictodoist.bindingadapter.prepareAdapter
import com.example.euvictodoist.databinding.FragmentTodosBinding
import com.example.euvictodoist.models.PostResponse
import com.example.euvictodoist.models.TodoResponse
import com.example.euvictodoist.viewmodels.TodosViewModel
import org.koin.android.ext.android.inject


class TodosFragment : Fragment() {

    private val todosViewModel : TodosViewModel by inject()
    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private val todosAdapter = TodosAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTodosBinding.inflate(LayoutInflater.from(context), container, false)
        binding.apply {
            lifecycleOwner = this@TodosFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initFragment() {
        initSearchBar()
        binding.loading.visibility = View.VISIBLE
        todosViewModel.requestTodosList()
        todosViewModel.todoList.observe(viewLifecycleOwner, Observer {
            setupData(it)
            binding.loading.visibility = View.GONE
        })
    }

    private fun initSearchBar() {
        binding.btnSearch.setOnClickListener {
            if (binding.etFilter.text.toString().isNotBlank()) {
                val userId = Integer.parseInt(binding.etFilter.text.toString())
                todosAdapter.clearFilters()
                todosAdapter.getTodosByUserId(userId)
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
            OnEditorActionListener { v, actionId, event ->
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

    private fun setupData(data: ArrayList<TodoResponse>) {
        todosAdapter.apply {
            setData(data)
        }
        binding.rvTodoList.prepareAdapter(todosAdapter)
        binding.rvTodoList.scheduleLayoutAnimation()
    }
}