package com.example.modul5

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul5.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import okhttp3.internal.http.RealResponseBody
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val list = ArrayList<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //list.addAll(listCard)
        //showRecyclerList()
        getList()
    }

    private fun getList() {
        val client = AsyncHttpClient()
        val url = "https://anime-facts-rest-api.herokuapp.com/api/v1"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    val responseObject = JSONObject(result)
                    val data = responseObject.getString("data")
                    val jsonArray = JSONArray(data)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)

                        val id = jsonObject.getInt("anime_id")
                        val name = jsonObject.getString("anime_name")
                        val img = jsonObject.getString("anime_img")

                        list.add(Card(id, name, img))
                    }
                    showRecyclerList()
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Toast.makeText(this@MainActivity, statusCode, Toast.LENGTH_SHORT).show()
            }
        })
    }


    /*private val listCard: ArrayList<Card>
        get(){
            val dataName = resources.getIntArray(R.array.card_name)
            val dataDesc = resources.getStringArray(R.array.card_description)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listCard = ArrayList<Card>()
            for (i in dataName.indices){
                val card = Card(dataName[i],dataDesc[i],dataPhoto.getResourceId(i, -1))
                listCard.add(card)
            }
            return listCard
        }
*/
    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.listCard.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.listCard.layoutManager = LinearLayoutManager(this)
        }

        val adapter = CardAdapter(list)
        binding.listCard.adapter = adapter
        adapter.setOnItemClickCallback(object : CardAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Card) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ANIME, data.name)
                intent.putExtra(DetailActivity.EXTRA_ANIME_IMG, data.img)
                startActivity(intent)
                /*adapter.setOnItemClickCallback(CardAdapter.OnItemClickCallback){
            override fun onItemClicked(data:Card){
                Toast.makeText(this@ListCardActivity, "Kamu memilih " + data.name, Toast.LENGTH_SHORT).show()
            }
        }*/
            }
        })
    }
}