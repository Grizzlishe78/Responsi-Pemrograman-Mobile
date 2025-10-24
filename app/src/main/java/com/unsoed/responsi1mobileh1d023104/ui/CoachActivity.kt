package com.unsoed.responsi1mobile1d023104.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.unsoed.responsi1mobile1d023104.R
import com.unsoed.responsi1mobile1d023104.databinding.ActivityCoachBinding
import com.unsoed.responsi1mobile1d023104.model.TeamResponse

class CoachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarCoach)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarCoach.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val json = intent.getStringExtra("team_json")
        val team = Gson().fromJson(json, TeamResponse::class.java)
        val coach = team?.coach

        if (coach != null) {
            binding.tvCoachName.text = coach.name ?: "-"
            binding.tvCoachDob.text = coach.dateOfBirth ?: "-"
            binding.tvCoachNationality.text = coach.nationality ?: "-"
            binding.tvCoachContract.text =
                "Contract: ${coach.contract?.start ?: "-"} — ${coach.contract?.until ?: "-"}"
        }

        Glide.with(this)
            .load(R.drawable.michel)
            .into(binding.ivCoach)
    }
}
