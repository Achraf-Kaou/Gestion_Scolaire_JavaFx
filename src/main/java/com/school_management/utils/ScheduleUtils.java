package com.school_management.utils;

import com.school_management.model.Seance;
import java.time.Duration;

/**
 * Utility class for schedule-related calculations
 */
public class ScheduleUtils {
    
    /**
     * Base height per hour in pixels for schedule blocks
     */
    public static final double BASE_HEIGHT_PER_HOUR = 60.0;
    
    /**
     * Calculate the duration of a session in hours
     * @param seance The session
     * @return Duration in hours, defaults to 1.0 if times are null or invalid
     */
    public static double getDurationInHours(Seance seance) {
        if (seance.getHeureDebut() == null || seance.getHeureFin() == null) {
            return 1.0;
        }
        
        long minutes = Duration.between(seance.getHeureDebut(), seance.getHeureFin()).toMinutes();
        
        // Validate that end time is after start time
        if (minutes < 0) {
            return 1.0; // Default to 1 hour for invalid data
        }
        
        return minutes / 60.0;
    }
    
    /**
     * Calculate how many columns a session should span in the schedule grid
     * @param seance The session
     * @return Number of columns (rounded up to nearest integer)
     */
    public static int calculateColumnSpan(Seance seance) {
        double durationInHours = getDurationInHours(seance);
        // Round up to the nearest hour
        // 1 hour = 1 column, 1.5 hours = 2 columns, 2 hours = 2 columns
        return (int) Math.ceil(durationInHours);
    }
    
    /**
     * Calculate the preferred height for a schedule block using the default base height
     * @param seance The session
     * @return Preferred height in pixels
     */
    public static double calculateBlockHeight(Seance seance) {
        return calculateBlockHeight(seance, BASE_HEIGHT_PER_HOUR);
    }
    
    /**
     * Calculate the preferred height for a schedule block
     * @param seance The session
     * @param baseHeight Base height per hour in pixels
     * @return Preferred height in pixels
     */
    public static double calculateBlockHeight(Seance seance, double baseHeight) {
        double durationInHours = getDurationInHours(seance);
        return baseHeight * durationInHours;
    }
}
