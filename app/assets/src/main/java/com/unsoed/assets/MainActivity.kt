package com.example.teamviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.teamviewer.databinding.ActivityMainBinding
import com.example.teamviewer.model.TeamResponse
import com.example.teamviewer.ui.PlayerAdapter
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load JSON dari assets/team.json
        val team = loadTeamFromAssets()

        // tampil header tim
        binding.tvTeamName.text = team?.name ?: "-"
        binding.tvVenue.text = "Venue: ${team?.venue ?: "-"}"
        binding.tvFounded.text = "Founded: ${team?.founded ?: "-"}"
        binding.tvClubColors.text = "Colors: ${team?.clubColors ?: "-"}"
        val crestUrl = team?.crest
        Glide.with(this).load(crestUrl).into(binding.ivCrest)

        // tampil coach (jika ada)
        if (team?.coach != null) {
            binding.layoutCoach.root.visibility = android.view.View.VISIBLE
            binding.layoutCoach.tvCoachName.text = team.coach.name ?: "-"
            binding.layoutCoach.tvCoachNationality.text = "Nationality: ${team.coach.nationality ?: "-"}"
            binding.layoutCoach.tvCoachDob.text = "DOB: ${team.coach.dateOfBirth ?: "-"}"
            val contract = team.coach.contract
            binding.layoutCoach.tvCoachContract.text = "Contract: ${contract?.start ?: "-"} — ${contract?.until ?: "-"}"
        } else {
            binding.layoutCoach.root.visibility = android.view.View.GONE
        }

        // players RecyclerView
        val players = team?.squad ?: emptyList()
        val adapter = PlayerAdapter(players)
        binding.rvPlayers.layoutManager = LinearLayoutManager(this)
        binding.rvPlayers.adapter = adapter
    }

    private fun loadTeamFromAssets(): TeamResponse? {
        return try {
            val input = assets.open("team.json")
            val reader = InputStreamReader(input, "UTF-8")
            val gson = Gson()
            gson.fromJson(reader, TeamResponse::class.java).also { reader.close(); input.close() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
