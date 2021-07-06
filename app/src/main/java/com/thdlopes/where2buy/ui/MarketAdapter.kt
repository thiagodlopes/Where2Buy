package com.thdlopes.where2buy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thdlopes.where2buy.data.Market
import com.thdlopes.where2buy.databinding.RecyclerViewMarketBinding

class MarketAdapter: RecyclerView.Adapter<MarketAdapter.ViewHolder>() {

    var markets = mutableListOf<Market>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewMarketBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return markets.size
    }

    inner class ViewHolder(val binding: RecyclerViewMarketBinding): RecyclerView.ViewHolder(binding.root)
}