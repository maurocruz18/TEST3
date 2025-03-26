package com.example.test3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test3.R;
import com.example.test3.adapters.GameAdapter;
import com.example.test3.models.Game;
import java.util.ArrayList;
import java.util.List;
import com.example.test3.R;
import android.widget.LinearLayout;

public class WishlistFragment extends Fragment {
    private RecyclerView wishlistRecycler;
    private LinearLayout emptyState;
    private GameAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        // Initialize views
        wishlistRecycler = view.findViewById(R.id.wishlist_recycler);
        emptyState = view.findViewById(R.id.empty_state);

        // Setup RecyclerView
        adapter = new GameAdapter(new ArrayList<>()); // Empty initial list
        wishlistRecycler.setAdapter(adapter);

        // Load wishlist data and update UI
        updateWishlistVisibility();

        return view;
    }

    private void updateWishlistVisibility() {
        if (adapter.getItemCount() == 0) {
            wishlistRecycler.setVisibility(View.GONE);
            emptyState.setVisibility(View.VISIBLE);
        } else {
            wishlistRecycler.setVisibility(View.VISIBLE);
            emptyState.setVisibility(View.GONE);
        }
    }

    // Your existing updateWishlist method
    public void updateWishlist(Game game, boolean add) {
        if (add) {
            adapter.addGame(game);
        } else {
            adapter.removeGame(game);
        }
        updateWishlistVisibility();
    }
}