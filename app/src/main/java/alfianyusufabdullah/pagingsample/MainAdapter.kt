package alfianyusufabdullah.pagingsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : PagedListAdapter<String, MainAdapter.MainHolder>(callback) {

    companion object {
        val callback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(getItem(position) ?: "Alfian ganteng")
    }

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvItem = view.findViewById<TextView>(R.id.tv_item)

        fun bind(text: String) {
            tvItem.text = text
        }
    }
}
