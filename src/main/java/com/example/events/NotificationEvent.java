package com.example.events;


import com.example.constants.Channel;

import java.util.List;
import java.util.Map;

public class NotificationEvent {
    private String eventType;
    private List<Channel> channelList;
    private Map<String, Object> eventDetails;

    public NotificationEvent() {}

    public NotificationEvent(String eventType, List<Channel> channelList, Map<String, Object> eventDetails) {
        this.eventType = eventType;
        this.channelList = channelList;
        this.eventDetails = eventDetails;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public Map<String, Object> getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(Map<String, Object> eventDetails) {
        this.eventDetails = eventDetails;
    }
}
