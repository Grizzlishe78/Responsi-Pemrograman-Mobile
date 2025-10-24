package com.unsoed.responsi1mobile1d023104.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.unsoed.responsi1mobile1d023104.databinding.ActivitySquadBinding
import com.unsoed.responsi1mobile1d023104.model.TeamResponse

class SquadActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySquadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSquad)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarSquad.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val json = intent.getStringExtra("team_json")
        val team = Gson().fromJson(json, TeamResponse::class.java)
        val squad = team.squad ?: emptyList()
        Log.d("SquadActivity", "Loaded squad size: ${squad.size}")

        val adapter = PlayerAdapter(squad) { player ->
            Log.d("SquadActivity", "Clicked player: ${player.name}")
            val playerJson = Gson().toJson(player)
            val bottomSheet = PlayerDetailBottomSheet.newInstance(playerJson)
            bottomSheet.show(supportFragmentManager, "PlayerDetailBottomSheet")
        }

        binding.rvPlayers.layoutManager = LinearLayoutManager(this)
        binding.rvPlayers.adapter = adapter
    }
}
