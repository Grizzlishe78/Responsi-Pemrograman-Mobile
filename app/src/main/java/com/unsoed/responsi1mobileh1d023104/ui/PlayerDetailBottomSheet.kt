package com.unsoed.responsi1mobile1d023104.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.unsoed.responsi1mobile1d023104.databinding.FragmentPlayerDetailBinding
import com.unsoed.responsi1mobile1d023104.model.SquadMember

class PlayerDetailBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = arguments?.getString("player_json")
        val player = Gson().fromJson(json, SquadMember::class.java)

        binding.tvPlayerName.text = player.name ?: "-"
        binding.tvPlayerDob.text = player.dateOfBirth ?: "-"
        binding.tvPlayerNationality.text = player.nationality ?: "-"
        binding.tvPlayerPosition.text = player.position ?: "-"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(playerJson: String): PlayerDetailBottomSheet {
            val fragment = PlayerDetailBottomSheet()
            val args = Bundle()
            args.putString("player_json", playerJson)
            fragment.arguments = args
            return fragment
        }
    }
}
