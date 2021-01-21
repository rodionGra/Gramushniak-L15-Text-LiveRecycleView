package com.a7acdhmwtext_liverecycleview

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter() : RecyclerView.Adapter<PersonAdapter.MyViewHolder>() {

    var list: List<Person> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyleview_item_person, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val personTextView: TextView = itemView.findViewById<TextView>(R.id.tv_person_name)

        fun bind(person: Person) {
            val spannableString = SpannableString(person.toString())

            spannableString.setSpan(
                ForegroundColorSpan(Color.YELLOW),
                person.firstName.length + 1,
                spannableString.length,
                0
            )

            spannableString.setSpan(
                UnderlineSpan(),
                person.firstName.length + 1,
                spannableString.length,
                0
            )

            personTextView.text = spannableString

        }
    }
}