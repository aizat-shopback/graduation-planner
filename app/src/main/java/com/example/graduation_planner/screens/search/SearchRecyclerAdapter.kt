package com.example.graduation_planner.screens.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.example.graduation_planner.database.Modular
import com.example.graduation_planner.database.SavedModulesDatabase
import com.example.graduation_planner.models.Module

class SearchRecyclerAdapter(var addModuleCallback: (module: Module) -> Unit, var modules: MutableList<Module>) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moduleCode: TextView
        var title: TextView
        var semesters: TextView

        init {
            moduleCode = itemView.findViewById(R.id.tvModuleCode)
            title = itemView.findViewById(R.id.tvModuleTitle)
            semesters = itemView.findViewById(R.id.tvModuleSemesters)

            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Added ${modules[position].moduleCode}", Toast.LENGTH_LONG).show()
                addModuleCallback(modules[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.moduleCode.text = modules[position].moduleCode
        holder.title.text = modules[position].title
        holder.semesters.text = semestersToString(modules[position].semesters)
    }

    override fun getItemCount(): Int {
        return modules.size
    }

    fun submitList(newData: List<Module>) {
        modules.clear()
        modules.addAll(newData)
        notifyDataSetChanged()
    }

    private fun semestersToString(semesters: List<Int>): String {
        var result = ""
        for (semester in semesters) {
            result += "Sem ${semester} | "
        }
        return result.substring(0, result.length - 2)
    }
}