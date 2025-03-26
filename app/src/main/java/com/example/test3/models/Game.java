package com.example.test3.models;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {
    private String id;
    private String name;
    private String description;
    private String studio;
    private List<String> platforms;
    private List<String> genres;
    private List<String> stores;
    private float rating;
    private String imageUrl;
    private List<String> screenshots;

    // Construtor
    public Game(String id, String name, String description, String studio,
                List<String> platforms, List<String> genres, List<String> stores,
                float rating, String imageUrl, List<String> screenshots) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.studio = studio;
        this.platforms = platforms;
        this.genres = genres;
        this.stores = stores;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.screenshots = screenshots;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStudio() {
        return studio;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getStores() {
        return stores;
    }

    public float getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setStores(List<String> stores) {
        this.stores = stores;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }

    // Helper method to add a single platform
    public void addPlatform(String platform) {
        this.platforms.add(platform);
    }

    // Helper method to add a single genre
    public void addGenre(String genre) {
        this.genres.add(genre);
    }

    // Helper method to add a single store
    public void addStore(String store) {
        this.stores.add(store);
    }

    // Helper method to add a single screenshot
    public void addScreenshot(String screenshot) {
        this.screenshots.add(screenshot);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}