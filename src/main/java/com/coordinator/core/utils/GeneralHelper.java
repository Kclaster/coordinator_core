package com.coordinator.core.utils;

public class GeneralHelper {
    public static String FirstOrDefault(String firstChoice, String secondChoice) {
        return firstChoice == null ? secondChoice : firstChoice;
    }

    public static Integer FirstOrDefault(Integer firstChoice, Integer secondChoice) {
        return firstChoice == null ? secondChoice : firstChoice;
    }
}
