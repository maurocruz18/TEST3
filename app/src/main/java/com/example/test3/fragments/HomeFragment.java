package com.example.test3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test3.R;
import com.example.test3.adapters.RecommendedGamesAdapter;
import com.example.test3.models.Game;
import com.example.test3.utils.DataLoader;
import java.io.InputStream;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recommendedRecyclerView;
    private RecyclerView newReleasesRecyclerView;
    private RecyclerView popularRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Load games data
        InputStream inputStream = getResources().openRawResource(R.raw.games);
        List<Game> games = DataLoader.loadGames(inputStream);

        // Set up recommended games horizontal list
        recommendedRecyclerView = view.findViewById(R.id.recommended_recycler);
        recommendedRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recommendedRecyclerView.setAdapter(new RecommendedGamesAdapter(games.subList(0, 5)));

        // Set up new releases horizontal list
        newReleasesRecyclerView = view.findViewById(R.id.new_releases_recycler);
        newReleasesRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newReleasesRecyclerView.setAdapter(new RecommendedGamesAdapter(games.subList(5, 10)));

        // Set up popular games horizontal list
        popularRecyclerView = view.findViewById(R.id.popular_recycler);
        popularRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularRecyclerView.setAdapter(new RecommendedGamesAdapter(games.subList(10, 15)));

        // Footer text
        TextView footerText = view.findViewById(R.id.footer_text);
        footerText.setText(R.string.shake_for_surprise);

        return view;
    }
}