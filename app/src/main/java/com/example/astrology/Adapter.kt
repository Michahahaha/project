package com.example.astrology

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class Adapter (private val context : Activity, private val arrayList : ArrayList<User>) : ArrayAdapter<User>(context,
    R.layout.meny_element,arrayList)        {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.meny_element,null)

        val imageView : ImageView = view.findViewById(R.id.meny_image)
        val zodiac : TextView = view.findViewById(R.id.meny_zodiak)


        imageView.setImageResource(arrayList[position].imageId)
        zodiac.text = arrayList[position].zodiac

        return view
    }

}