package com.showbooking.strategy;

import com.showbooking.model.Slot;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class StartTimeRankingStrategy implements ShowRankingStrategy {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    @Override
    public List<Entry<String, Slot>> rankSlots(Map<String, Slot> slots) {
        return slots.entrySet().stream()
                .sorted(Comparator.comparing(e -> parseStartTime(e.getKey())))
                .collect(Collectors.toList());
    }

    private LocalTime parseStartTime(String timeRange) {
        String start = timeRange.split("-")[0];
        return LocalTime.parse(start, formatter);
    }
}
