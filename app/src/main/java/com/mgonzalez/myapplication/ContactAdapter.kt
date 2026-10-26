package com.mgonzalez.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (private val dataSet: ArrayList<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView
        val textTelMobile: TextView
        val textEmail: TextView

        init {
            // Define click listener for the ViewHolder's View
            textName = view.findViewById(R.id.textName)
            textTelMobile = view.findViewById(R.id.textTelMobile)
            textEmail = view.findViewById(R.id.textEmail)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contact_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textName.text = dataSet.get(position).name
        viewHolder.textTelMobile.text = dataSet.get(position).telNum
        viewHolder.textEmail.text = dataSet.get(position).email
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}