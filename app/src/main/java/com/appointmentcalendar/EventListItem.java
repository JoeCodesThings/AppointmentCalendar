package com.appointmentcalendar;

/**
 * Created by Blacklotis on 12/1/2015.
 */
public class EventListItem {

    private String itemTitle;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public EventListItem(String title){
        this.itemTitle = title;
    }
}
