package ru.baiganov.appfilm.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.data.Movie
import ru.baiganov.appfilm.databinding.ViewHolderMovieBinding

class MoviesAdapter (
    private val listener: ItemClickListener
        ) : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem = movies[position]
        holder.onBind(currentItem)
        holder.itemView.setOnClickListener {
            listener.onItemClick(movies[position])
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

class MoviesViewHolder(private val binding: ViewHolderMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(movie: Movie) {
        Glide.with(context)
            .load(movie.poster)
            .into(binding.ivPosterItem)

        binding.tvNameItem.text = movie.title
        binding.tvTime.text = movie.runtime.toString() + " " + context.getString(R.string.min)
        binding.tvReviewsItem.text = movie.numberOfRatings.toString() + " " + context.getString(R.string.reviews)
        binding.tvPgItem.text = movie.minimumAge.toString() + context.getString(R.string.plus)
        var temp:String = ""
        movie.genres.forEach {
            temp = if (temp == "") {
                it.name
            } else {
                "$temp,${it.name}"
            }
        }
        binding.tvTagItem.text = temp
        binding.ratingBar.rating = movie.ratings / 2
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

fun interface ItemClickListener {
    fun onItemClick(movie: Movie)
}

