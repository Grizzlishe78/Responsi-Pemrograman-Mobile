package com.unsoed.responsi1mobile1d023104.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.unsoed.responsi1mobile1d023104.databinding.ActivityHistoryBinding
import com.unsoed.responsi1mobile1d023104.model.TeamResponse

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHistory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarHistory.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val json = intent.getStringExtra("team_json")
        val team = Gson().fromJson(json, TeamResponse::class.java)

        Glide.with(this)
            .load(team.crest)
            .into(binding.ivStadium)

        binding.tvClubName.text = team.name ?: "-"
        binding.tvVenue.text = "Stadium: ${team.venue ?: "-"}"
        binding.tvFounded.text = "Founded: ${team.founded ?: "-"}"

        binding.tvHistory.text = """
            Girona FC adalah klub sepak bola profesional yang berbasis di kota Girona, Spanyol. 
            Klub ini berdiri pada tahun 1930 dan bermarkas di Estadi Municipal de Montilivi, 
            stadion dengan kapasitas sekitar 14.000 penonton.
            
            Dalam beberapa tahun terakhir, Girona menunjukkan performa luar biasa di La Liga 
            dengan gaya permainan menyerang cepat dan efisien. Klub ini menjadi simbol kebanggaan 
            bagi masyarakat Catalonia dan terus berkembang menjadi kekuatan baru di sepak bola Spanyol.
        """.trimIndent()
    }
}
