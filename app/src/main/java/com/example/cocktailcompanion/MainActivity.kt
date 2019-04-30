package com.example.cocktailcompanion

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_search_drinksby_name.*
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //inflate the first fragment
        if(supportFragmentManager.findFragmentById(R.id.content) == null){
            supportFragmentManager.beginTransaction().add(R.id.content,FirstFragment()).commit()
            println("I got into the inflation protion")
        }

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            //all of the serach terms
            R.id.search_cocktail -> {
                //create the search fragment
                val fragment = SearchDrinksbyName()
                //inflate it
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()

            }
            R.id.search_ingredient ->{

            }
            R.id.search_by_ingredient -> {

            }

            //all of the filter terms
            R.id.filter_alcahol ->{

            }
            R.id.filter_category->{

            }
            R.id.filter_glass->{

            }

            //random
            R.id.random_cocktail-> {
                getRandom(null)
            }

            //favorites
            R.id.my_favorites->{

            }

            //to try
            R.id.to_try->{

            }

            //about
            R.id.about->{

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun getRandom(view: View?){
        //get the data via api call
        val args = Bundle()
        //val randomData = getData("https://www.thecocktaildb.com/api/json/v1/1/random.php")
        val request = Request.Builder()
            .url("https://www.thecocktaildb.com/api/json/v1/1/random.php")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Something went wrong: API call failed")
            }
            override fun onResponse(call: Call, response: Response) {
                var responseData = response.body()?.string()
                val gson = GsonBuilder().create()
                val feed = gson.fromJson(responseData, DrinksFeed::class.java)
                println("*********"+feed+ "************")
                //create the random fragment
                val fragment = DrinkFragment()

                //send the data
                args.putInt(DrinkFragment.idDrink, feed.drinks[0].idDrink!!)
                args.putString(DrinkFragment.strDrink, feed.drinks[0].strDrink)
                args.putString(DrinkFragment.strDrinkThumb, feed.drinks[0].strDrinkThumb)
                args.putString(DrinkFragment.strAlcoholic, feed.drinks[0].strAlcoholic)
                args.putString(DrinkFragment.strGlass, feed.drinks[0].strGlass)
                args.putString(DrinkFragment.strInstructions, feed.drinks[0].strInstructions)

                //ingredient data
                args.putString(DrinkFragment.strIngredient1, feed.drinks[0].strIngredient1)
                args.putString(DrinkFragment.strIngredient2, feed.drinks[0].strIngredient2)
                args.putString(DrinkFragment.strIngredient3, feed.drinks[0].strIngredient3)
                args.putString(DrinkFragment.strIngredient4, feed.drinks[0].strIngredient4)
                args.putString(DrinkFragment.strIngredient5, feed.drinks[0].strIngredient5)
                args.putString(DrinkFragment.strIngredient6, feed.drinks[0].strIngredient6)
                args.putString(DrinkFragment.strIngredient7, feed.drinks[0].strIngredient7)
                args.putString(DrinkFragment.strIngredient8, feed.drinks[0].strIngredient8)
                args.putString(DrinkFragment.strIngredient9, feed.drinks[0].strIngredient9)
                args.putString(DrinkFragment.strIngredient10, feed.drinks[0].strIngredient10)
                args.putString(DrinkFragment.strIngredient11, feed.drinks[0].strIngredient11)
                args.putString(DrinkFragment.strIngredient12, feed.drinks[0].strIngredient12)
                args.putString(DrinkFragment.strIngredient13, feed.drinks[0].strIngredient13)
                args.putString(DrinkFragment.strIngredient14, feed.drinks[0].strIngredient14)
                args.putString(DrinkFragment.strIngredient15, feed.drinks[0].strIngredient15)

                //build the fragment
                fragment.arguments = args
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }
        })
    }
    fun getDrinkDatabyName(view: View){
        val name = searchbyname.text
        val request = Request.Builder()
            .url("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$name")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Something went wrong: API call failed")
            }

            override fun onResponse(call: Call, response: Response) {
                var responseData = response.body()?.string()
                val gson = GsonBuilder().create()
                val feed = gson.fromJson(responseData, DrinksFeed::class.java)
                println("*********" + feed.drinks.get(0).idDrink + "************")

                //format the data

                //set the data

                //inflate the recycler view

            }
        })
    }
}
class DrinksFeed(val drinks:List<Drinks>)
class Drinks(val idDrink:Int?, val strDrink: String?, val strDrinkAlternative:String?, val strDrinkES: String?,
             val strDrinkDE: String?, val strDrinkFR: String?, val strDrinkZHHANS: String?,val strDrinkZHHANT: String?,
             val strTags: String?, val strVideo: String?, val strCategory: String?, val strIBA: String?,
             val strAlcoholic: String?, val strGlass: String?, val strInstructions: String?, val strInstructionsES: String?,
             val strInstructionsDE: String?,val strInstructionsFR: String?, val strInstructionsZHHANS: String?,
             val strInstructionsZHHANT: String?, val strDrinkThumb: String?, val strIngredient1: String?,val strIngredient2: String?,
             val strIngredient3: String?,val strIngredient4: String?,val strIngredient5: String?,val strIngredient6: String?,
             val strIngredient7: String?,val strIngredient8: String?,val strIngredient9: String?,val strIngredient10: String?,
             val strIngredient11: String?,val strIngredient12: String?,val strIngredient13: String?,val strIngredient14: String?,
             val strIngredient15: String?,val strMeasure1: String?,val strMeasure2: String?,
             val strMeasure3: String?,val strMeasure4: String?,val strMeasure5: String?,val strMeasure6: String?,
             val strMeasure7: String?,val strMeasure8: String?,val strMeasure9: String?,val strMeasure10: String?,
             val strMeasure11: String?,val strMeasure12: String?,val strMeasure13: String?,val strMeasure14: String?,
             val strMeasure15: String?)
