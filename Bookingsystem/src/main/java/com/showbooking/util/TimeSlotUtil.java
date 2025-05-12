package com.showbooking.util;

public class TimeSlotUtil {
  public static boolean isValidSlot(String slot) {
    try {
      String[] times = slot.split("-");
      if (times.length != 2) return false;

      String[] start = times[0].split(":");
      String[] end = times[1].split(":");

      if (start.length != 2 || end.length != 2) return false;

      int startHour = Integer.parseInt(start[0]);
      int startMinute = Integer.parseInt(start[1]);
      int endHour = Integer.parseInt(end[0]);
      int endMinute = Integer.parseInt(end[1]);

      return (startMinute == 0 && endMinute == 0) && (endHour - startHour == 1);
    } catch (Exception e) {
      System.out.println("Invalid slot format: " + slot);
      return false;
    }
  }

}
