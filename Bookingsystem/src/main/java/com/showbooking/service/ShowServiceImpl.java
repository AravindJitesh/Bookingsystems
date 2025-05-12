package com.showbooking.service;

import com.showbooking.model.*;
import com.showbooking.strategy.ShowRankingStrategy;
import com.showbooking.strategy.StartTimeRankingStrategy;
import com.showbooking.util.TimeSlotUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowServiceImpl implements ShowService {

    private final Map<String, Show> shows;
    private ShowRankingStrategy rankingStrategy;

    public ShowServiceImpl(Map<String, Show> shows) {
        this.shows = shows;
        this.rankingStrategy = new StartTimeRankingStrategy();
    }
    public void setRankingStrategy(ShowRankingStrategy strategy) {
        this.rankingStrategy = strategy;
    }


    @Override
    public void registerShow(String name, Genre genre) {
        shows.put(name, new Show(name, genre));
        System.out.println(name + " show is registered !!");
    }

    @Override
    public void onboardSlots(String name, List<String> slotInfos) {
        Show show = shows.get(name);
        if (show == null) return;

        for (String slotInfo : slotInfos) {
            String[] parts = slotInfo.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid slot info: " + slotInfo);
                continue;
            }

            String slotTime = parts[0];
            int capacity;
            try {
                capacity = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid capacity: " + parts[1]);
                continue;
            }

            if (!TimeSlotUtil.isValidSlot(slotTime)) {
                System.out.println("Sorry, show timings are of 1 hour only");
                continue;
            }

            Slot slot = new Slot(slotTime, capacity);
            show.addSlot(slot);
        }

        System.out.println("Done!");
    }


    @Override
    public void showByGenre(Genre genre) {
        shows.values().stream()
                .filter(s -> s.getGenre() == genre)
                .forEach(s -> {
                    List<Map.Entry<String, Slot>> rankedSlots = rankingStrategy.rankSlots(s.getSlots());

                    rankedSlots.forEach(e -> {
                        if (e.getValue().getAvailableCapacity() > 0) {
                            System.out.println(s.getName() + ": (" + e.getKey() + ") " + e.getValue().getAvailableCapacity());
                        }
                    });
                });
    }


    public Map<String, Show> getShows() {
        return shows;
    }
}
