package com.example.test3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test3.R;
import com.example.test3.adapters.GameAdapter;
import com.example.test3.models.Game;
import com.example.test3.utils.DataLoader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrowseFragment extends Fragment {

    private RecyclerView gamesRecyclerView;
    private Spinner genreSpinner, platformSpinner, storeSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse, container, false);

        // Load dados jogos
        InputStream inputStream = getResources().openRawResource(R.raw.games);
        List<Game> games = DataLoader.loadGames(inputStream);

        // Spinners dos filtros
        genreSpinner = view.findViewById(R.id.genre_spinner);
        platformSpinner = view.findViewById(R.id.platform_spinner);
        storeSpinner = view.findViewById(R.id.store_spinner);

        // Get valores unicos para os filtros
        Set<String> genres = new HashSet<>();
        Set<String> platforms = new HashSet<>();
        Set<String> stores = new HashSet<>();

        for (Game game : games) {
            genres.addAll(game.getGenres());
            platforms.addAll(game.getPlatforms());
            stores.addAll(game.getStores());
        }

        // Set up dos adapters para os spinners
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, new ArrayList<>(genres));
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        ArrayAdapter<String> platformAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, new ArrayList<>(platforms));
        platformAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        platformSpinner.setAdapter(platformAdapter);

        ArrayAdapter<String> storeAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, new ArrayList<>(stores));
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        storeSpinner.setAdapter(storeAdapter);

        // Set up games list
        gamesRecyclerView = view.findViewById(R.id.games_recycler);
        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gamesRecyclerView.setAdapter(new GameAdapter(games));

        return view;
    }
}