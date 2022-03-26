package edu.neu.madcourse.a7_stick_it_to_em;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private Map<Integer,Integer> sent;
    private Map <Integer,Integer> received;
    User(String username){
        this.username = username;
        this.sent = new HashMap<>();
        this.received = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Integer, Integer> getSent() {
        return sent;
    }

    public Map<Integer, Integer> getReceived() {
        return received;
    }
}
