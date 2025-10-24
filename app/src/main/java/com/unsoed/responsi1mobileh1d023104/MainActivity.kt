package com.unsoed.responsi1mobile1d023104

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.unsoed.responsi1mobile1d023104.databinding.ActivityMainBinding
import com.unsoed.responsi1mobile1d023104.model.TeamResponse
import com.unsoed.responsi1mobile1d023104.ui.CoachActivity
import com.unsoed.responsi1mobile1d023104.ui.HistoryActivity
import com.unsoed.responsi1mobile1d023104.ui.SquadActivity
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var teamData: TeamResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        teamData = loadTeamFromAssets()

        binding.tvTeamName.text = teamData?.name ?: "-"
        binding.tvDescription.text = """
            Venue: ${teamData?.venue ?: "-"}
            Founded: ${teamData?.founded ?: "-"}
            Club Colors: ${teamData?.clubColors ?: "-"}
        """.trimIndent()

        Glide.with(this)
            .load(teamData?.crest)
            .into(binding.ivCrest)

        binding.btnCoach.setOnClickListener {
            val intent = Intent(this, CoachActivity::class.java)
            intent.putExtra("team_json", Gson().toJson(teamData))
            startActivity(intent)
        }

        binding.btnSquad.setOnClickListener {
            val intent = Intent(this, SquadActivity::class.java)
            intent.putExtra("team_json", Gson().toJson(teamData))
            startActivity(intent)
        }

        binding.btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("team_json", Gson().toJson(teamData))
            startActivity(intent)
        }

    }

    private fun loadTeamFromAssets(): TeamResponse? {
        return try {
            val input = assets.open("team.json")
            val reader = InputStreamReader(input, "UTF-8")
            val gson = Gson()
            gson.fromJson(reader, TeamResponse::class.java).also {
                reader.close()
                input.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
