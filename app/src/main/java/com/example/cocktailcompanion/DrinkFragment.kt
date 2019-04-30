package com.example.cocktailcompanion

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DrinkFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DrinkFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DrinkFragment : Fragment() {
    companion object {
        val idDrink = "idDrink"
        val strDrink = "strDrink"
        val strAlcoholic = "strAlcoholic"
        val strGlass = "strGlass"
        val strInstructions = "strInstructions"
        val strDrinkThumb = "strDrinkThumb"
        val strIngredient1 = "strIngredient1"
        val strIngredient2 = "strIngredient2"
        val strIngredient3 = "strIngredient3"
        val strIngredient4 = "strIngredient4"
        val strIngredient5 = "strIngredient5"
        val strIngredient6 = "strIngredient6"
        val strIngredient7 = "strIngredient7"
        val strIngredient8 = "strIngredient8"
        val strIngredient9 = "strIngredient9"
        val strIngredient10 = "strIngredient10"
        val strIngredient11 = "strIngredient11"
        val strIngredient12 = "strIngredient12"
        val strIngredient13 = "strIngredient13"
        val strIngredient14 = "strIngredient14"
        val strIngredient15 = "strIngredient15"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val image = view.findViewById<ImageView>(R.id.drink_image)
        val name = view.findViewById<TextView>(R.id.drink_name)
        val instructions = view.findViewById<TextView>(R.id.drink_instructions)
        val ingredients = view.findViewById<TextView>(R.id.drink_ingredients)
        val glass = view.findViewById<TextView>(R.id.drink_glass)
        val alcaholic = view.findViewById<TextView>(R.id.drink_alcaholic)

        Picasso.get().load(arguments!!.getString(strDrinkThumb)).into(image)
        //image.setImageResource(arguments!!.getSt(strDrinkThumb))
        name.text = arguments!!.getString(strDrink)
        instructions.text = "Instructions: "+arguments!!.getString(strInstructions)
        ingredients.text = ""
        var cnt = 1
        while (arguments!!.getString("strIngredient$cnt") != ""){
            ingredients.append("$cnt: "+arguments!!.getString("strIngredient$cnt") + "\n")
            cnt++
        }
        glass.text = "Glass type: "+arguments!!.getString(strGlass)
        alcaholic.text = "Is alcaholic?: "+arguments!!.getString(strAlcoholic)
    }
}
