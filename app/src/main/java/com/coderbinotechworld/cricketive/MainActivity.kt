package com.coderbinotechworld.cricketive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.lifecycleScope
import com.coderbinotechworld.cricketive.adapter.MatchAdapter
import com.coderbinotechworld.cricketive.api.ApiInterface
import com.coderbinotechworld.cricketive.api.ApiUtilities
import com.coderbinotechworld.cricketive.constant.Constant.API_KEY
import com.coderbinotechworld.cricketive.constant.Constant.SERIES_ID
import com.coderbinotechworld.cricketive.constant.Constant.isNetworkAvailable
import com.coderbinotechworld.cricketive.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val matchApi = ApiUtilities.getInstance().create(ApiInterface::class.java)

        if (isNetworkAvailable(this)){
            binding.internetStatus.visibility = GONE

            lifecycleScope.launch(Dispatchers.IO) {

                val result = matchApi.getSeries(API_KEY, SERIES_ID)

                if (result.body() != null){
                    Log.d("SHUBH", "onCreate: ${result.body()!!.data.matchList}")

                    withContext(Dispatchers.Main) {
                        binding.pb.visibility = GONE
                        binding.rv.adapter = MatchAdapter(this@MainActivity, result.body()!!.data.matchList)
                    }
                }

            }

        }else{
            binding.pb.visibility = GONE
            binding.rv.visibility = GONE

            binding.internetStatus.text = "Lost the Connection!\nPlease \"Tern-On\" the Internet"
            binding.internetStatus.visibility = VISIBLE
        }



    }
}