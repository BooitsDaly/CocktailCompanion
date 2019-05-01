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


class FilterGlass : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_filter_glass, container, false)
        val values = arrayOf(
            "Highball glass",
            "Cocktail glass",
            "Old-fashioned glass",
            "Collins glass",
            "Pousse cafe glass",
            "Champagne flute",
            "Whiskey sour glass",
            "Brandy snifter",
            "White wine glass",
            "Nick and Nora Glass",
            "Hurricane glass",
            "Coffee mug",
            "Shot glass",
            "Jar",
            "Irish coffee cup",
            "Punch bowl",
            "Pitcher",
            "Pint glass",
            "Copper Mug",
            "Wine Glass",
            "Cordial glass",
            "Beer mug",
            "Margarita/Coupette glass",
            "Beer pilsner",
            "Beer Glass",
            "Parfait glass",
            "Mason jar",
            "Margarita glass",
            "Martini Glass",
            "Ballon Glass",
            "Coupe Glass")
        val spinner = v.findViewById(R.id.filter_glass_spinner) as Spinner
        val adapter = ArrayAdapter(this.activity!!, android.R.layout.simple_spinner_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter

        return v
    }

}
