package fi.metropolia.retrofitapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_president.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create the adapter to convert the array to views
        val adapter = PresidentListAdapter(this, GlobalModel.presidents)

        // use a custom layout (instead of the ListActivity default layout)
        setContentView(R.layout.activity_main)

        // attach the adapter to a ListView
        mainlistview.adapter = adapter

        mainlistview.setOnItemClickListener { _, _, position, _ ->
            Log.d("USR", "Selected $position")
            val name = GlobalModel.presidents[position].toString()
            selname.text = name
            seldescription.text = GlobalModel.presidents[position].description

            // Get hits for president
            val call = WikiApi.service.presidentName(name)
            val value = object : Callback<WikiResponse> {

                override fun onFailure(call: Call<WikiResponse>, t: Throwable) {
                    Log.e("MainActivity", "$t")
                }

                override fun onResponse(
                    call: Call<WikiResponse>,
                    response: Response<WikiResponse>?
                ) {
                    if (response != null) {
                        val res: WikiResponse = response.body()!!
                        val totalHits = res.query.searchinfo.totalhits
                        hitsTv.text = "$totalHits"
                    }
                }
            }
            call.enqueue(value)
        }

        mainlistview.setOnItemLongClickListener { _, _, position, _ ->
            val selectedPresident = GlobalModel.presidents[position]
            val detailIntent =
                PresidentDetailActivity.newIntent(
                    this,
                    selectedPresident
                )

            startActivity(detailIntent)
            true
        }
    }

    object WikiApi {
        private const val URL = "https://en.wikipedia.org/w/"

        interface Service {
            @GET("api.php?action=query&format=json&list=search&srsearch=Trump")
            fun presidentName(
                @Query("srsearch") name: String
            ): Call<WikiResponse>
        }

        private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Service::class.java)
    }

    private inner class PresidentListAdapter(context: Context, private val presidents: MutableList<President>) : BaseAdapter() {
        private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return presidents.size
        }

        override fun getItem(position: Int): Any {
            return presidents[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val rowView = inflater.inflate(R.layout.item_president, parent, false)
            val president = presidents[position]
            rowView.tvName.text = president.name
            rowView.tvStartDuty.text = president.startDuty.toString()
            rowView.tvEndDuty.text = president.endDuty.toString()
            return rowView
        }
    }
}
