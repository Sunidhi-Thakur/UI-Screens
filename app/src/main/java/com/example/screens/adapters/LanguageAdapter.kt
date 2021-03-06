package com.example.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.screens.LocaleListener
import com.example.screens.R
import com.example.screens.models.LanguageList

class LanguageAdapter(private var languageList: List<LanguageList>):
    RecyclerView.Adapter<LanguageAdapter.MyViewHolder>() {
    private var mSelectedItem = -1
    private lateinit var mListener: LocaleListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_language, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val language = languageList[position]
        holder.local.text = language.getLocal()
        holder.english.text = language.getEnglish()
        holder.bindItems(position, mSelectedItem)
        holder.radioButton.setOnClickListener {
            mSelectedItem = holder.adapterPosition
            changeLanguage(mSelectedItem)
            notifyDataSetChanged()
        }
    }

    private fun changeLanguage(position: Int) {
        when (position) {
            1 -> mListener.setLocale("hi")
            2 -> mListener.setLocale("gu")
            3 -> mListener.setLocale("mr")
            4 -> mListener.setLocale("bn")
            5 -> mListener.setLocale("kn")
            6 -> mListener.setLocale("ta")
            7 -> mListener.setLocale("te")
            else -> mListener.setLocale("en")
        }

    }


    override fun getItemCount(): Int {
        return languageList.size
    }

    fun setLocaleListener(listener: LocaleListener) {
        mListener = listener
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var local: TextView = view.findViewById(R.id.localText)
        var english: TextView = view.findViewById(R.id.englishText)
        var radioButton: RadioButton = view.findViewById(R.id.radioButton)
        fun bindItems(position: Int, selectedPosition: Int) {

            if ((selectedPosition == -1 && position == 0))
                radioButton.isChecked = true
            else
                radioButton.isChecked = selectedPosition == position
        }
    }
}