package com.example.test3.utils;

import com.example.test3.models.Game;

import java.util.ArrayList;
import java.util.List;

public class WishlistManager {
    private static WishlistManager instance;
    private List<Game> wishlistGames = new ArrayList<>();

    public static WishlistManager getInstance() {
        if (instance == null) {
            instance = new WishlistManager();
        }
        return instance;
    }

    public boolean isInWishlist(Game game) {
        for (Game wishlistGame : wishlistGames) {
            if (wishlistGame.getId().equals(game.getId())) {
                return true;
            }
        }
        return false;
    }

    public void addToWishlist(Game game) {
        if (!isInWishlist(game)) {
            wishlistGames.add(game);
        }
    }

    public void removeFromWishlist(Game game) {
        for (int i = 0; i < wishlistGames.size(); i++) {
            if (wishlistGames.get(i).getId().equals(game.getId())) {
                wishlistGames.remove(i);
                break;
            }
        }
    }

    public List<Game> getWishlist() {
        return new ArrayList<>(wishlistGames);
    }
}