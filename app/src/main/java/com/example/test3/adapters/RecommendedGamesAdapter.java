package com.example.test3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test3.R;
import com.example.test3.models.Game;
import java.util.List;
import android.widget.RatingBar;
import android.content.Intent;
import com.example.test3.GameDetailActivity;

public class RecommendedGamesAdapter extends RecyclerView.Adapter<RecommendedGamesAdapter.RecommendedGameViewHolder> {

    private List<Game> recommendedGames;

    public RecommendedGamesAdapter(List<Game> recommendedGames) {
        this.recommendedGames = recommendedGames;
    }

    @NonNull
    @Override
    public RecommendedGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommended_game_item, parent, false);
        return new RecommendedGameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedGameViewHolder holder, int position) {
        Game game = recommendedGames.get(position);
        holder.gameName.setText(game.getName());
        holder.ratingBar.setRating(game.getRating());

        // Using placeholder for all images
        holder.gameImage.setImageResource(R.drawable.ic_game_placeholder);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), GameDetailActivity.class);
            intent.putExtra("game", game);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recommendedGames.size();
    }

    static class RecommendedGameViewHolder extends RecyclerView.ViewHolder {
        ImageView gameImage;
        TextView gameName;
        RatingBar ratingBar;

        public RecommendedGameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameImage = itemView.findViewById(R.id.game_image);
            gameName = itemView.findViewById(R.id.game_name);
            ratingBar = itemView.findViewById(R.id.game_rating_bar);
        }
    }
}