package com.example.room_teste_teste

import  android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.data.local.Task


class TaskListAdpater(
    private val openTaskDetailView:(task: Task) -> Unit
) :ListAdapter<Task, TaskListViewHolder>(TaskListAdpater) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_task, parent, false
            )
        return TaskListViewHolder(view)
    }

    //tamanho da lista
    // Atrela uma coisa a outra
    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task,openTaskDetailView)
    }

    // implementamos um diffutil callbacka
    companion object: DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem== newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.title==newItem.title &&
                    oldItem.description== newItem.description

        }

    }
}
//-----------------------------------------------------------------------
// essa funcao e fora dos parentesses

class TaskListViewHolder(private val view:View):RecyclerView.ViewHolder(view) {
    private val tvTitle = view.findViewById<TextView>(R.id.tv_task_title)
    private val tvDesc = view.findViewById<TextView>(R.id.tv_task_description)

    fun bind(task: Task, openTaskDetailView:(task: Task) -> Unit)
    {
        tvTitle.text = task.title
        tvDesc.text="${task.id} ${task.description}"
        itemView.setOnClickListener{
            openTaskDetailView.invoke(task)
        }

    }

}

