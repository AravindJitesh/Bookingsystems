package com.showbooking.model;

import java.util.*;

public class Show {
    private String name;
    private Genre genre;
    private Map<String, Slot> slots = new HashMap<>();

    public Show(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }

    public void addSlot(Slot slot) {
        slots.put(slot.getStartTime(), slot);
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Map<String, Slot> getSlots() {
        return slots;
    }
}
