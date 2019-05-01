package com.example.cocktailcompanion

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.example.cocktailcompanion.IngredientsListFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_ingredientslist.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyIngredientsListRecyclerViewAdapter(
    private val mValues: List<Drinks>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyIngredientsListRecyclerViewAdapter.ViewHolder>() {

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_ingredientslist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.strDrink
        Picasso.get().load(item.strDrinkThumb).into(holder.image)
        //holder.mContentView.text = item.content

        with(holder.mView) {
            tag = item
            //setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val image: ImageView = mView.drink_image

        override fun toString(): String {
            return super.toString() + " '" + mIdView.text + "'"
        }
    }
}
