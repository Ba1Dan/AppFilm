package ru.baiganov.appfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.features.data.Actor
import com.bumptech.glide.Glide
import ru.baiganov.appfilm.R

class ActorsAdapter: RecyclerView.Adapter<ActorsViewHolder>() {

    private var actors = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        val currentItem = actors[position]
        holder.onBind(currentItem)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    fun onBindActors(newActors:List<Actor>) {
        actors = newActors
    }
}

class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatar:ImageView = itemView.findViewById(R.id.iv_avatar)
    private val name:TextView = itemView.findViewById(R.id.tv_full_name)

    fun onBind(actor: Actor) {
        name.text = actor.name
        Glide.with(context)
            .load(actor.picture)
            .into(avatar)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context