package com.example.cocktailcompanion

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner


class CategoryList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_category_list, container, false)
        val values = arrayOf(
            "Ordinary Drink",
            "Cocktail",
            "Milk / Float / Shake",
            "Other/Unknown",
            "Cocoa",
            "Shot",
            "Coffee / Tea",
            "Homemade Liqueur",
            "Punch / Party Drink",
            "Beer",
            "Soft Drink / Soda")
        val spinner = v.findViewById(com.example.cocktailcompanion.R.id.filter_category_spinner) as Spinner
        val adapter = ArrayAdapter(this.activity!!, android.R.layout.simple_spinner_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter

        return v
    }
}
