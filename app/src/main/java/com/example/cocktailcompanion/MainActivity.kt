package com.example.cocktailcompanion

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.core.view.get
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_category_list.*
import kotlinx.android.synthetic.main.fragment_filter_glass.*
import kotlinx.android.synthetic.main.fragment_filterby_ingredient.*
import kotlinx.android.synthetic.main.fragment_filterby_ingredient.view.*
import kotlinx.android.synthetic.main.fragment_search_drinksby_name.*
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlinx.android.synthetic.main.fragment_searchby_ingredient.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val client = OkHttpClient()

    /**
     * On Create sets up the toolbar and inflates the initial fragment
     * Sets us the nav
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //inflate the first fragment
        if(supportFragmentManager.findFragmentById(R.id.content) == null){
            supportFragmentManager.beginTransaction().add(R.id.content,FirstFragment()).commit()
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

    /**
     * Handles the nav bar when clicked
     * In charge of all of the navigation in this application
     */
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
                //create the search fragment
                val fragment = SearchbyIngredient()
                //inflate it
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }
            R.id.search_by_ingredient -> {
                //create the search fragment
                val fragment = FilterbyIngredient()
                //inflate it
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }

            //all of the filter terms
            R.id.filter_alcahol ->{
                val request = Request.Builder()
                    .url("https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic")
                    .build()

                client.newCall(request).enqueue(object : okhttp3.Callback {
                    override fun onFailure(call: Call, e: IOException) {
                    }

                    override fun onResponse(call: Call, response: Response) {
                        var responseData = response.body()?.string()
                        val gson = GsonBuilder().create()
                        val feed = gson.fromJson(responseData, DrinksFeed::class.java)
                        if(feed.drinks == null){
                            return//Toast.makeText(this@MainActivity, "Nothing found for that search", Toast.LENGTH_LONG).show()
                        }else{
                            val fragment = IngredientsListFragment(feed.drinks)
                            supportFragmentManager.beginTransaction().replace(R.id.content,fragment).commit()
                        }
                    }
                })

            }
            R.id.filter_nonalcahol->{
                val request = Request.Builder()
                    .url("https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic")
                    .build()

                client.newCall(request).enqueue(object : okhttp3.Callback {
                    override fun onFailure(call: Call, e: IOException) {
                    }

                    override fun onResponse(call: Call, response: Response) {
                        var responseData = response.body()?.string()
                        val gson = GsonBuilder().create()
                        val feed = gson.fromJson(responseData, DrinksFeed::class.java)
                        if(feed.drinks == null){
                            return//Toast.makeText(this@MainActivity, "Nothing found for that search", Toast.LENGTH_LONG).show()
                        }else{
                            val fragment = IngredientsListFragment(feed.drinks)
                            supportFragmentManager.beginTransaction().replace(R.id.content,fragment).commit()
                        }
                    }
                })
            }
            R.id.filter_category->{
                val fragment = CategoryList()
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }
            R.id.filter_glass->{
                val fragment = FilterGlass()
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }
            //random
            R.id.random_cocktail-> {
                getRandom(null)
            }
            //favorites
            R.id.my_favorites->{ }
            //to try
            R.id.to_try->{ }
            //about
            R.id.about->{
                //create the search fragment
                val fragment = About()
                //inflate it
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * gets the data from the spinner
     * makes an api call
     * inflates the fragment to display data
     */
    fun getCatagoryData(view:View){
        //filter_category_spinner
        val term = filter_category_spinner.getItemAtPosition(filter_category_spinner.selectedItemPosition)
        if(!term.equals("") && term != null){
            val request = Request.Builder()
                .url("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=$term")
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    var responseData = response.body()?.string()
                    val gson = GsonBuilder().create()
                    val feed = gson.fromJson(responseData, DrinksFeed::class.java)
                    if(feed.drinks == null){
                        return//Toast.makeText(this@MainActivity, "Nothing found for that search", Toast.LENGTH_LONG).show()
                    }else{
                        val fragment = IngredientsListFragment(feed.drinks)
                        supportFragmentManager.beginTransaction().replace(R.id.filter_category_dataresults,fragment).commit()
                    }
                }
            })
        }
    }

    /**
     * Gets a random cocktail
     * makes the api query
     * inflates fragment
     */
    fun getRandom(view: View?){
        //get the data via api call
        val args = Bundle()
        //val randomData = getData("https://www.thecocktaildb.com/api/json/v1/1/random.php")
        val request = Request.Builder()
            .url("https://www.thecocktaildb.com/api/json/v1/1/random.php")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
            }
            override fun onResponse(call: Call, response: Response) {
                var responseData = response.body()?.string()
                val gson = GsonBuilder().create()
                val feed = gson.fromJson(responseData, DrinksFeed::class.java)
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

    /**
     * Gets the data from the spinner
     * makes an api call
     * inflates fragment
     */
    fun getGlassData(view:View){
        //filter_category_spinner
        val term = filter_glass_spinner.getItemAtPosition(filter_glass_spinner.selectedItemPosition)
        if(!term.equals("") && term != null){
            val request = Request.Builder()
                .url("https://www.thecocktaildb.com/api/json/v1/1/filter.php?g=$term")
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    var responseData = response.body()?.string()
                    val gson = GsonBuilder().create()
                    val feed = gson.fromJson(responseData, DrinksFeed::class.java)
                    if(feed.drinks == null){
                        return//Toast.makeText(this@MainActivity, "Nothing found for that search", Toast.LENGTH_LONG).show()
                    }else{
                        val fragment = IngredientsListFragment(feed.drinks)
                        supportFragmentManager.beginTransaction().replace(R.id.filter_glass_dataresults,fragment).commit()
                    }
                }
            })
        }
    }

    /**
     * Gets the drink name from the text feild
     * makes query to the api
     * inflates the fragment
     * removes the keyboard
     */
    fun getDrinkDatabyName(view: View){
        val name = searchbyname.text
        val args = Bundle()
        if(!name.equals("")){
            val request = Request.Builder()
                .url("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$name")
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    var responseData = response.body()?.string()
                    val gson = GsonBuilder().create()
                    val feed = gson.fromJson(responseData, DrinksFeed::class.java)
                    if(feed.drinks == null){
                        return//Toast.makeText(this@MainActivity, "Nothing found for that search", Toast.LENGTH_LONG).show()
                    }else{
                        //inflate the recycler view
                        val fragment = DrinksFragment(feed)
                        supportFragmentManager.beginTransaction().replace(R.id.searchbynameresults, fragment).commit()
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
                    }
                }
            })
        }

    }

    /**
     * Gets the spinner data
     * queries the database
     * displays data in fragement
     */
    fun IngredientSelected(view:View){
        val term = spinner.getItemAtPosition(spinner.selectedItemPosition)
        if(!term.equals("") && term != null){
            val request = Request.Builder()
                .url("https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$term")
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    var responseData = response.body()?.string()
                    val gson = GsonBuilder().create()
                    val feed = gson.fromJson(responseData, DrinksFeed::class.java)
                    if(feed.drinks == null){
                        return//Toast.makeText(this@MainActivity, "Nothing found for that search", Toast.LENGTH_LONG).show()
                    }else{
                        //inflate the recycler view
                        val fragment = IngredientsListFragment(feed.drinks)
                        supportFragmentManager.beginTransaction().replace(R.id.filterbyingredientresults,fragment).commit()
                    }
                }
            })
        }
    }

    /**
     * sets the search term
     * makes a query
     * displays the data
     */
    fun getIngredient(view: View){
        val term = searchIngredient.text
        if(!term.equals("") && term != null){
            val request = Request.Builder()
                .url("https://www.thecocktaildb.com/api/json/v1/1/search.php?i=$term")
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    var responseData = response.body()?.string()
                    val gson = GsonBuilder().create()
                    val feed = gson.fromJson(responseData, IngredientsFeed::class.java)
                    if (feed.ingredients == null) {
                        return//Toast.makeText(this@MainActivity, "Nothing found for that search", Toast.LENGTH_LONG).show()
                    } else {
                        val textString = "Ingredient: ${feed.ingredients.get(0).strIngredient}\n" +
                                "Description: ${feed.ingredients.get(0).strDescription}\n" +
                                "Type of Ingredient: ${feed.ingredients.get(0).strType}"
                        getIngredients.text = textString
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
                    }
                }
            })
        }
    }
}
class DrinksFeed(val drinks:List<Drinks>)
class IngredientsFeed(val ingredients:List<Ingredients>)
class Ingredients(val idIngredient:Int?, val strIngredient: String?,
             val strDescription: String?, val strType: String?)




