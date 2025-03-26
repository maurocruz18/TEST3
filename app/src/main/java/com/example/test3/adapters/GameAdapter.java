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
import android.content.Intent;
import com.example.test3.GameDetailActivity;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> games;

    public GameAdapter(List<Game> games) {
        this.games = games;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = games.get(position);
        holder.gameName.setText(game.getName());
        holder.gameStudio.setText(game.getStudio());
        holder.gameRating.setText(String.format("Rating: %.1f/5", game.getRating()));

        // Using placeholder for all images
        holder.gameImage.setImageResource(R.drawable.ic_game_placeholder);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), GameDetailActivity.class);
            intent.putExtra("game", game);  // Make sure Game implements Serializable
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView gameImage;
        TextView gameName;
        TextView gameStudio;
        TextView gameRating;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameImage = itemView.findViewById(R.id.game_image);
            gameName = itemView.findViewById(R.id.game_name);
            gameStudio = itemView.findViewById(R.id.game_studio);
            gameRating = itemView.findViewById(R.id.game_rating);
        }
    }
    public void addGame(Game game) {
        if (!games.contains(game)) {
            games.add(game);
            notifyItemInserted(games.size() - 1);
        }
    }

    public void removeGame(Game game) {
        int position = games.indexOf(game);
        if (position != -1) {
            games.remove(position);
            notifyItemRemoved(position);
        }
    }
}