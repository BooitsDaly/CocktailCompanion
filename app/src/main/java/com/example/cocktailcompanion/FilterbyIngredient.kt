package com.example.cocktailcompanion

import android.os.Bundle
import com.example.cocktailcompanion.MainActivity
import androidx.fragment.app.Fragment
//import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.R
import android.app.Activity
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FilterbyIngredient.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FilterbyIngredient.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FilterbyIngredient : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(com.example.cocktailcompanion.R.layout.fragment_searchby_ingredient, container, false)
        val v = inflater.inflate(com.example.cocktailcompanion.R.layout.fragment_filterby_ingredient, container, false)
        //val v = inflater.inflate(R.layout.manual, container, false)

        val values = arrayOf(
            "Light rum",
            "Applejack",
            "Gin",
            "Dark Rum",
            "Sweet Vermouth",
            "Strawberry schnapps",
            "Scotch",
            "Apricot brandy",
            "Triple sec",
            "Southern Comfort",
            "Orange bitters",
            "Brandy",
            "Lemon vodka",
            "Blended whiskey",
            "Dry Vermouth",
            "Amaretto",
            "Tea",
            "Champagne",
            "Coffee Liqueur",
            "Burbon",
            "Tequila",
            "Vodka",
            "Bitters",
            "Sugar",
            "Kahlua",
            "demerara Sugar",
            "Dubonnet Rouge",
            "Lime Juice",
            "Irish Whiskey",
            "Apple brandy",
            "Carbonated Water",
            "Cherry brandy",
            "Creme de Cacao",
            "Grenadine",
            "Port",
            "Coffee Brandy",
            "Red Wine",
            "Rum",
            "Grapefruit Juice",
            "Ricard",
            "Sherry",
            "Cognac",
            "Sloe Gin",
            "Apple Juice",
            "Pineapple Juice",
            "Lemon Juice",
            "Sugar syrup",
            "Milk",
            "Strawberries",
            "Chocolate Syrup",
            "Yoghurt",
            "Mango",
            "Ginger",
            "Lime",
            "Cantaloupe",
            "Berries",
            "Grapes",
            "Kiwi",
            "Tomato Juice",
            "Coco Powder",
            "Chocolate",
            "Heavy Cream",
            "Galliano",
            "Peach Vodka",
            "Ouzo",
            "Coffee",
            "Spiced Rum",
            "Water",
            "Espresso",
            "Angelica root",
            "Orange",
            "Cranberries",
            "Johnnie Walker",
            "Apple cider",
            "Everclear",
            "Cranberry Juice",
            "Egg Yolk",
            "Egg",
            "Grape Juice",
            "Peach nectar",
            "Lemon",
            "Firewater",
            "Lemonade",
            "Lager",
            "Whiskey",
            "Absolut Citron",
            "Pisco",
            "Irish Cream",
            "Ale",
            "Chocolate liqueur",
            "Midori melon liqueur",
            "Sambuca",
            "Cider",
            "Sprite",
            "7-up",
            "Blackberry brandy",
            "Peppermint schnapps",
            "Creme de Cassis",
            "Jack Daniels",
            "Bailey's Irish Cream",
            "151 proof rum",
            "Absolut Vodka",
            "Goldschlager",
            "Crown Royal",
            "Cointreau",
            "Vermouth",
            "Advocaat",
            "Absolut Kurant",
            "Beer",
            "Kool-Aid",
            "Cherry Heering",
            "White Creme de Menthe",
            "Malibu rum",
            "Vanilla Vodka",
            "Jagermeister",
            "Kiwi liqueur",
            "Grand Marnier",
            "Cachaca",
            "Peachtree schnapps",
            "Wild Turkey",
            "Cranberry vodka",
            "Corona",
            "Orange Juice",
            "Yukon Jack",
            "Chocolate ice-cream",
            "Coconut rum",
            "Banana liqueur",
            "Black Sambuca",
            "Mint",
            "Ice",
            "Sour Mix",
            "Absinthe")
        val spinner = v.findViewById(com.example.cocktailcompanion.R.id.spinner) as Spinner
        val adapter = ArrayAdapter(this.activity!!, R.layout.simple_spinner_item, values)
        adapter.setDropDownViewResource(R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter

        return v
    }
}
