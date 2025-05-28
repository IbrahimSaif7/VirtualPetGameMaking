package com.example.virtualpetgame;

public enum TaskType {
    FEED_PET("Feed a pet", 20),
    PLAY_WITH_PET("Play with a pet", 25),
    MAKE_PET_SLEEP("Put a pet to sleep", 15),
    EVOLVE_PET("Evolve a pet", 50);

    private final String description;
    private final int reward;

    TaskType(String description, int reward) {
        this.description = description;
        this.reward = reward;
    }

    public String getDescription() {
        return description;
    }

    public int getReward() {
        return reward;
    }
}

