package com.unsoed.responsi1mobile1d023104.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobile1d023104.databinding.ItemPlayerBinding
import com.unsoed.responsi1mobile1d023104.model.SquadMember

class PlayerAdapter(
    private val items: List<SquadMember>,
    private val onClick: (SquadMember) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.VH>() {

    inner class VH(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(p: SquadMember) {
            binding.tvName.text = p.name ?: "-"
            binding.tvPosition.text = p.position ?: "-"
            binding.tvNationality.text = p.nationality ?: "-"
            binding.tvDob.text = p.dateOfBirth ?: "-"

            val pos = p.position?.lowercase() ?: ""
            val bg = when {
                "goal" in pos -> android.R.color.holo_orange_light
                "defend" in pos -> android.R.color.holo_blue_light
                "mid" in pos -> android.R.color.holo_green_light
                "forw" in pos -> android.R.color.holo_red_light
                else -> android.R.color.darker_gray
            }
            binding.cardPlayer.setCardBackgroundColor(
                binding.root.context.resources.getColor(bg, null)
            )

            binding.root.setOnClickListener { onClick(p) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount(): Int = items.size
}

