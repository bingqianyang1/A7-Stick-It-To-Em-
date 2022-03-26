package edu.neu.madcourse.a7_stick_it_to_em;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private Map<String,Integer> sent;
    private Map<String,Integer> received;
    User(String username){
        this.username = username;
        this.sent = new HashMap<>();
        this.received = new HashMap<>();
        sent.put("1",100);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Integer> getSent() {
        return sent;
    }

    public Map<String, Integer> getReceived() {
        return received;
    }
}
