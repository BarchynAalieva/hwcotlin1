package kg.geektech.hwcotlin1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.hwcotlin1.R
import kg.geektech.hwcotlin1.databinding.ItemBinding
import kg.geektech.hwcotlin1.models.Story

class ListAdapter(private var list: ArrayList<Story>): RecyclerView.Adapter<ListAdapter.HistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount()=list.size

    class HistoryHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding= ItemBinding.bind(itemView)

        fun bind(story: Story) = with(binding){
            tvTitle.text=story.text
            tvCreatedAt.text=story.data.toString()
        }
    }
}