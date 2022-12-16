package com.example.astrology

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.astrology.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    val url = "https://horoscopes.rambler.ru/api/front/v1/horoscope/today/"
    var zod = "taurus"
    val end = "/"
    var fullUrl = url + zod + end;
    var page_zodiak = findViewById<TextView>(R.id.page_zodiak)
    var page_data = findViewById<TextView>(R.id.page_data)
    var page_prediction = findViewById<TextView>(R.id.page_prediction)


    private lateinit var binding : ActivityMainBinding
    private lateinit var userArrayList : ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        downloadZodiak()

        val imageId = intArrayOf(

            R.drawable.blisnezi,R.drawable.vesi,R.drawable.vodolei,
            R.drawable.deva,R.drawable.kozerog,R.drawable.lev,
            R.drawable.oven,R.drawable.rak,R.drawable.riba,
            R.drawable.skorpion,R.drawable.strelez,R.drawable.telez,

        )

        val zodiac = arrayOf(

            "Близнеци","Веси","Водолей",
            "Дева","Козерог","Лев",
            "Овен","Рак","Риби",
            "Скорпион","Стрелец","Телец"

        )

        val prediction = arrayOf(

            "Близнеци",
            "Веси",
            "Водолей",
            "Дева",
            "Козерог",
            "Лев",
            "Овен",
            "Рак",
            "Риби",
            "Скорпион",
            "Стрелец",
            "Телец"

        )

        val data = arrayOf(

            "16.12.2022", "16.12.2022", "16.12.2022",
            "16.12.2022", "16.12.2022", "16.12.2022",
            "16.12.2022", "16.12.2022", "16.12.2022",
            "16.12.2022", "16.12.2022", "16.12.2022"

        )

        if (page_zodiak.equals("Близнеци")) zod = "gemini"
        if (page_zodiak.equals("Веси")) zod = "libra"
        if (page_zodiak.equals("Водолей")) zod = "aquarius"
        if (page_zodiak.equals("Дева")) zod = "virgo"
        if (page_zodiak.equals("Козерог")) zod = "capricorn"
        if (page_zodiak.equals("Лев")) zod = "leo"
        if (page_zodiak.equals("Овен")) zod = "aries"
        if (page_zodiak.equals("Рак")) zod = "cancer"
        if (page_zodiak.equals("Риби")) zod = "pisces"
        if (page_zodiak.equals("Скорпион")) zod = "scorpio"
        if (page_zodiak.equals("Стрелец")) zod = "sagittarius"
        if (page_zodiak.equals("Телец")) zod = "taurus"


        userArrayList = ArrayList()

        for( i in zodiac.indices){

            val user = User(zodiac[i],prediction[i],data[i],imageId[i])
            userArrayList.add(user)

        }

        binding.listview.isClickable = true
        binding.listview.adapter = Adapter(this,userArrayList)
        binding.listview.setOnItemClickListener { parent, view, position, id ->

            val zodiac = zodiac[position]
            val prediction = prediction[position]
            val data = data[position]
            val imageId = imageId[position]

            val i = Intent(this,page::class.java)
            i.putExtra("zodiac",zodiac)
            i.putExtra("prediction",prediction)
            i.putExtra("data",data)
            i.putExtra("imageId",imageId)
            startActivity(i)
        }

    }

    fun downloadZodiak(){
        val queue = Volley.newRequestQueue(this)
        val reques = StringRequest(Request.Method.GET,fullUrl,
            Response.Listener { response ->

                val data = response.toString()
                var jArray = JSONArray(data)
                for (e in 0..jArray.length()-1) {
                    var meta = jArray.getJSONObject(e)
                    var source = meta.getString("source")
                    var teaser = jArray.getJSONObject(e)
                    var text = teaser.getString("text")
                    var output1 = source
                    var output2 = text
                    page_data.setText(output1)
                    page_prediction.setText(output2)
                }

            }, Response.ErrorListener {  })
        queue.add(reques)
    }
}