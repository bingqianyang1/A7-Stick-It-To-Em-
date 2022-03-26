package edu.neu.madcourse.a7_stick_it_to_em;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String username;
    public Map<String,Integer> sent;
    public Map<String,Integer> received;
    public int received_Number;
    public Map<String, Object> received_history;
//    public ArrayList<String> sent_history;
    public User(){};

    public User(String username){
        this.username = username;
        this.sent = new HashMap<>();
        this.received = new HashMap<>();
        sent.put("Sticker1", 0);
        sent.put("Sticker2", 0);
        sent.put("Sticker3", 0);
        sent.put("Sticker4", 0);
        received.put("Sticker1", 0);
        received.put("Sticker2", 0);
        received.put("Sticker3", 0);
        received.put("Sticker4", 0);
//        this.sent_history = new ArrayList<>();
        this.received_history = new HashMap<>();
//        received_history.put("History_Number", "History");
        received_Number = 0;
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

    public void setReceived(){
        this.received = new HashMap<>();
    }
}
