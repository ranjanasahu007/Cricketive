package com.coderbinotechworld.cricketive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.coderbinotechworld.cricketive.R
import com.coderbinotechworld.cricketive.models.Match
import com.google.android.material.snackbar.Snackbar

class MatchAdapter(private val context: Context, private val list: List<Match>): RecyclerView.Adapter<MatchAdapter.MatchViewHOlder>() {

    var onItemClick: ((Match) -> Unit)? = null

    class MatchViewHOlder(private val view: View): RecyclerView.ViewHolder(view){

        val matchType: TextView = view.findViewById(R.id.matchType)
        var matchTitle: TextView = view.findViewById(R.id.matchTitle)
        var matchTeams: TextView = view.findViewById(R.id.teams)
        val matchStatus: TextView = view.findViewById(R.id.status)
        val matchLocation: TextView = view.findViewById(R.id.location)
        val matchDate: TextView = view.findViewById(R.id.date)
        val txtCardView: CardView = view.findViewById(R.id.textCardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHOlder {
        return MatchViewHOlder(LayoutInflater.from(context).inflate(R.layout.series_info_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MatchViewHOlder, position: Int) {

        val item = list[position]

        holder.matchTitle.text = item.name
        holder.matchStatus.text = item.status
        holder.matchType.text = item.matchType
        holder.matchDate.text = item.date
        holder.matchLocation.text = item.venue

        var teamName = ""
        var count = 0
        for(team in item.teams) {
            count += 1
            if (count == 1){
                teamName += "$team vs "
            }else
                teamName += "$team"
        }

        holder.matchTeams.text = teamName

        holder.txtCardView.setOnClickListener {
            Snackbar.make(it, "Item ${position + 1} is Clicked", Snackbar.LENGTH_SHORT).show()
        }

        onItemClick?.invoke(item)


    }

}