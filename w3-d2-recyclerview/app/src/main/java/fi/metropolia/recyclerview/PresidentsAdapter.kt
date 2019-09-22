package fi.metropolia.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class PresidentsAdapter(private val presidents: ArrayList<President>) : RecyclerView.Adapter<PresidentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return presidents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = presidents[position].fullName
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PresidentDetailsActivity::class.java)
            intent.putExtra("PRESIDENT_INDEX", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
    }

}