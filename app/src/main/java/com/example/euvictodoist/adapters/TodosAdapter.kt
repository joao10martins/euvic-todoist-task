package com.example.euvictodoist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.euvictodoist.databinding.ListItemTodoBinding
import com.example.euvictodoist.models.TodoResponse

class TodosAdapter() : RecyclerView.Adapter<TodosAdapter.TodoViewHolder>(){

    private var originalTodos = listOf<TodoResponse>()
    private var originalTodosCopy = listOf<TodoResponse>()
    private var filteredTodos = listOf<TodoResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ListItemTodoBinding.inflate(LayoutInflater.from(parent.context))
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return originalTodos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(originalTodos[position])
    }

    inner class TodoViewHolder(private val binding: ListItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoResponse) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    fun setData(todos: List<TodoResponse>) {
        this.originalTodos = todos
        if (originalTodosCopy.isEmpty()) {
            originalTodosCopy = originalTodos
        }
        notifyDataSetChanged()
    }

    fun getTodosByUserId(userId : Int) {
        filteredTodos = originalTodos.filter { todoResponse -> todoResponse.userId == userId }
        setData(filteredTodos)
    }

    fun clearFilters() {
        setData(originalTodosCopy)
    }
}