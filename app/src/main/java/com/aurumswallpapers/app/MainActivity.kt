package com.aurumswallpapers.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurumswallpapers.app.aurumadapter.AurumsAdapter
import com.aurumswallpapers.app.aurumutils.AurumUtils
import com.aurumswallpapers.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var adapterAurum : AurumsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rcAurumInit()
    }

    private fun rcAurumInit() = with(binding){
        adapterAurum = AurumsAdapter(AurumUtils.arrayAurumDataImg,this@MainActivity)
        rcAurum.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        rcAurum.adapter = adapterAurum

    }
}