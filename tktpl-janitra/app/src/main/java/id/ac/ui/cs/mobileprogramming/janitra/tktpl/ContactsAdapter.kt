package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Contacts

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private var listContacts: List<Contacts> = listOf()

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ListViewHolder {
        val itemView: View = LayoutInflater.from(view.context).inflate(R.layout.item_contact, view, false)
        return ListViewHolder(itemView)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Contacts, position: Int)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int){

        var currentContacts = listContacts.get(position)
        holder.name.setText(currentContacts.name)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listContacts[holder.adapterPosition], position)
        }
    }

    override fun getItemCount(): Int {
        return listContacts.size
    }

    fun setContacts(contacts: List<Contacts>) {
        listContacts = contacts
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
    }
}