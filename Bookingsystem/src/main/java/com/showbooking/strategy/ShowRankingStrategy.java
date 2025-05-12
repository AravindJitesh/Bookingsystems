package com.showbooking.strategy;

import com.showbooking.model.Slot;

import java.util.List;
import java.util.Map;

public interface ShowRankingStrategy {
    List<Map.Entry<String, Slot>> rankSlots(Map<String, Slot> slots);
}
