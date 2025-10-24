package com.unsoed.responsi1mobile1d023104.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.unsoed.responsi1mobile1d023104.databinding.ActivityPlayerDetailBinding
import com.unsoed.responsi1mobile1d023104.model.SquadMember

class PlayerDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerJson = intent.getStringExtra("player_json")
        val player = Gson().fromJson(playerJson, SquadMember::class.java)

        binding.tvPlayerName.text = player.name ?: "-"
        binding.tvDob.text = player.dateOfBirth ?: "-"
        binding.tvNationality.text = player.nationality ?: "-"
        binding.tvPosition.text = player.position ?: "-"
    }
}
