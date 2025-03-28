package com.example.test3;
import com.example.test3.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.test3.utils.WishlistManager;
import com.example.test3.models.Game;
import com.example.test3.utils.WishlistManager;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import android.widget.RatingBar;
import androidx.core.content.ContextCompat;

public class GameDetailActivity extends AppCompatActivity {

    private boolean isInWishlist = false;
    private Game currentGame;
    Button wishlistButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        wishlistButton = findViewById(R.id.wishlist_button);

        currentGame = (Game) getIntent().getSerializableExtra("game");
        if (currentGame == null) {
            finish(); // Close activity if no game data
            return;
        }

        // Initialize views
        ImageView gameImage = findViewById(R.id.game_image);
        TextView gameName = findViewById(R.id.game_name);
        TextView gameStudio = findViewById(R.id.game_studio);
        RatingBar ratingBar = findViewById(R.id.game_rating_bar);
        TextView gameDescription = findViewById(R.id.game_description);
        ChipGroup platformsGroup = findViewById(R.id.platforms_group);
        ChipGroup genresGroup = findViewById(R.id.genres_group);


        // Set game data
        gameName.setText(currentGame.getName());
        gameStudio.setText(currentGame.getStudio());
        ratingBar.setRating(currentGame.getRating());
        gameDescription.setText(currentGame.getDescription());

        // Add platform chips
        for (String platform : currentGame.getPlatforms()) {
            Chip chip = new Chip(this);
            chip.setText(platform);
            chip.setChipBackgroundColorResource(R.color.purple_200);
            chip.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            platformsGroup.addView(chip);
        }

        // Add genre chips with click listeners
        for (String genre : currentGame.getGenres()) {
            Chip chip = new Chip(this);
            chip.setText(genre);
            chip.setClickable(true);
            chip.setChipBackgroundColorResource(R.color.purple_500);
            chip.setTextColor(ContextCompat.getColor(this, android.R.color.white));

            chip.setOnClickListener(v -> {
                // Launch BrowseFragment filtered by this genre
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("filter_type", "genre");
                intent.putExtra("filter_value", genre);
                startActivity(intent);
                finish(); // Close detail activity
            });

            genresGroup.addView(chip);
        }

        // Check if game is in wishlist
        isInWishlist = WishlistManager.getInstance().isInWishlist(currentGame);
        updateWishlistButton();

        wishlistButton.setOnClickListener(v -> {
            isInWishlist = !isInWishlist;

            if (isInWishlist) {
                WishlistManager.getInstance().addToWishlist(currentGame);
            } else {
                WishlistManager.getInstance().removeFromWishlist(currentGame);
            }

            updateWishlistButton();
        });
    }

private void updateWishlistButton() {
    if (isInWishlist) {
        wishlistButton.setText(R.string.remove_from_wishlist);
        wishlistButton.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite),
                null, null, null);
    } else {
        wishlistButton.setText(R.string.add_to_wishlist);
        wishlistButton.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border),
                null, null, null);
    }
}
}