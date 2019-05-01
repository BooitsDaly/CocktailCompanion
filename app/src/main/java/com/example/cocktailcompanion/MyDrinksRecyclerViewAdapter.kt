package com.example.cocktailcompanion

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.example.cocktailcompanion.DrinksFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_drinks.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyDrinksRecyclerViewAdapter(
    private val mValues: List<Drinks>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyDrinksRecyclerViewAdapter.ViewHolder>() {

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_drinks, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        //holder.mIdView.text = "$position: "
        holder.mContentView.text = item.strDrink
        Picasso.get().load(item.strDrinkThumb).into(holder.image)
        var descriptionString = "\nInstructions: ${item.strInstructions}\nAlcoholic?: ${item.strAlcoholic}\n" +
                "Glass Type: ${item.strGlass}\n"

        //this is really annoying but it was the only way i could think of to traverse the whole
        //instructions strings
        if(item.strIngredient1 != "" && item.strIngredient1 != null){
            descriptionString += "Ingredients:\n1. ${item.strIngredient1}"
        }
        if(item.strIngredient2 != "" && item.strIngredient2 != null){
            descriptionString += "\n2. ${item.strIngredient2}"
        }
        if(item.strIngredient3 != "" && item.strIngredient3 != null){
            descriptionString += "\n3. ${item.strIngredient3}"
        }
        if(item.strIngredient4 != "" && item.strIngredient4 != null){
            descriptionString += "\n4. ${item.strIngredient4}"
        }
        if(item.strIngredient5 != "" && item.strIngredient5 != null){
            descriptionString += "\n5. ${item.strIngredient5}"
        }
        if(item.strIngredient6 != "" && item.strIngredient6 != null){
            descriptionString += "\n6. ${item.strIngredient6}"
        }
        if(item.strIngredient7 != "" && item.strIngredient7 != null){
            descriptionString += "\n7. ${item.strIngredient7}"
        }
        if(item.strIngredient8 != "" && item.strIngredient8 != null){
            descriptionString += "\n8. ${item.strIngredient8}"
        }
        if(item.strIngredient9 != "" && item.strIngredient9 != null){
            descriptionString += "\n9. ${item.strIngredient9}"
        }
        if(item.strIngredient10 != "" && item.strIngredient10 != null){
            descriptionString += "\n10. ${item.strIngredient10}"
        }
        if(item.strIngredient11 != "" && item.strIngredient11 != null){
            descriptionString += "\n11. ${item.strIngredient11}"
        }
        if(item.strIngredient12 != "" && item.strIngredient12 != null){
            descriptionString += "\n12. ${item.strIngredient12}"
        }
        if(item.strIngredient13 != "" && item.strIngredient13 != null){
            descriptionString += "\n13. ${item.strIngredient13}"
        }
        if(item.strIngredient14 != "" && item.strIngredient14 != null){
            descriptionString += "\n14. ${item.strIngredient14}"
        }
        if(item.strIngredient15 != "" && item.strIngredient15 != null){
            descriptionString += "\n15. ${item.strIngredient15}"
        }

        holder.mContentView.append(descriptionString)


        with(holder.mView) {
            tag = item
            //setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val image:ImageView = mView.drink_image

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
