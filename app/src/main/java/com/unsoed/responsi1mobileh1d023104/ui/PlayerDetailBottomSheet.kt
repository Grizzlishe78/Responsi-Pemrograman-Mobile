package com.unsoed.responsi1mobile1d023104.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.unsoed.responsi1mobile1d023104.databinding.FragmentPlayerDetailBinding
import com.unsoed.responsi1mobile1d023104.model.SquadMember

class PlayerDetailBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    private var player: SquadMember? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = arguments?.getString("player_json")
        if (!json.isNullOrEmpty()) {
            try {
                player = Gson().fromJson(json, SquadMember::class.java)
                Log.d("PlayerDetail", "Data parsed: ${player?.name}")
            } catch (e: Exception) {
                Log.e("PlayerDetail", "JSON parse error: ${e.message}")
            }
        } else {
            Log.w("PlayerDetail", "Player JSON is null or empty")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (player == null) {
            Toast.makeText(requireContext(), "Player data not available", Toast.LENGTH_SHORT).show()
            dismiss()
            return
        }

        binding.apply {
            tvPlayerName.text = player?.name ?: "-"
            tvPlayerDob.text = player?.dateOfBirth ?: "-"
            tvPlayerNationality.text = player?.nationality ?: "-"
            tvPlayerPosition.text = player?.position ?: "-"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(playerJson: String): PlayerDetailBottomSheet {
            val fragment = PlayerDetailBottomSheet()
            fragment.arguments = Bundle().apply {
                putString("player_json", playerJson)
            }
            return fragment
        }
    }
}
