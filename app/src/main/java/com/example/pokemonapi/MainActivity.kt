package com.example.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var PokeImage = ""
    var PokeName = ""
    var PokeType = ""
    var PokeAbility = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPokeImage()
        getPokeName()
        getPokeType()
        getPokeAbiity()
        val button = findViewById<Button>(R.id.button)
        val imageview = findViewById<ImageView>(R.id.imageView)
        val textName = findViewById<TextView>(R.id.nameTextView) as TextView
        val textType = findViewById<TextView>(R.id.typeView) as TextView
        val textAbility = findViewById<TextView>(R.id.abilityView) as TextView
        getNextPokemon(button,imageview,textName,textType,textAbility)
    }

    var PokemonsID = Random.nextInt(151)

    private fun getPokeImage() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/$PokemonsID/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Pokemon", "response successful")
                PokeImage = json.jsonObject.getJSONObject("sprites").getString("front_default")
                Log.d("PokeImage", "pokemon image is confirmed")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon Error", errorResponse)
            }
        }]
    }

    private fun getPokeName() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/$PokemonsID/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Pokemon", "response successful")
                PokeName = json.jsonObject.getJSONObject("species").getString("name")
                Log.d("Name", "pokemon Name is confirmed")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon Error", errorResponse)
            }
        }]
    }

    private fun getPokeType() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/$PokemonsID/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Pokemon", "response successful")
                PokeType = json.jsonObject.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name")
                Log.d("Type", "pokemon Type is confirmed")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon Error", errorResponse)
            }
        }]
    }
    private fun getPokeAbiity() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/$PokemonsID/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Pokemon", "response successful")
                PokeAbility = json.jsonObject.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("name")
                Log.d("Ability", "pokemon Ability is confirmed")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon Error", errorResponse)
            }
        }]
    }

    private fun getNextPokemon(button: Button, imageView: ImageView, textName: TextView, textType: TextView, textAbility: TextView) {
        button.setOnClickListener {
            PokemonsID = Random.nextInt(151)
            getPokeImage()
            getPokeName()
            getPokeType()
            getPokeAbiity()

            Glide.with(this)
                .load(PokeImage)
                .fitCenter()
                .into(imageView)

            textName.text = PokeName
            textType.text = PokeType
            textAbility.text = PokeAbility
        }
    }


}


