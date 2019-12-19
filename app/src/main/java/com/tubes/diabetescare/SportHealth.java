package com.tubes.diabetescare;

class SportHealth {
    // Member variables representing the title and information about the sport.
    private String exerciseTitle;
    private final int iconResource;
    private String exerciseTime;

    SportHealth(String exerciseTitle, int iconResource, String exerciseTime) {
        this.exerciseTitle = exerciseTitle;
        this.iconResource = iconResource;
        this.exerciseTime = exerciseTime;
    }

    String getExerciseTitle() {
        return exerciseTitle;
    }

    int getImageResource() {
        return iconResource;
    }

    String getExerciseTime() {
        return exerciseTime;
    }
}
