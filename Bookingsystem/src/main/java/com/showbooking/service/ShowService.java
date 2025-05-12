package com.showbooking.service;

import com.showbooking.model.Genre;

import java.util.List;

public interface ShowService {
    void registerShow(String name, Genre genre);
    void onboardSlots(String name, List<String> slotInfos);
    void showByGenre(Genre genre);
}
