package org.dissys;



import org.dissys.messages.Message;

import java.util.*;

public class Room {
    private String roomId;
    private List<User> users;
    private List<Message> messageLog;
    private Map<String, Map<String, Integer>> vectorClocks; // Per-user vector clocks
    private Set<Message> deliveredMessages; // Track delivered messages by their IDs

    public Room() {
        this.roomId = UUID.randomUUID().toString();
    this.users = new ArrayList<>(); // Initialize list of users
        this.messageLog = new ArrayList<>();
        this.vectorClocks = new HashMap<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Message> getMessageLog() {
        return messageLog;
    }

    public void joinRoom(User user) {
        users.add(user);
        vectorClocks.put(user.getUserId(), new HashMap<>()); // Initialize vector clock for user
        for (User u : users) {
            vectorClocks.get(user.getUserId()).put(u.getUserId(), 0); // Initialize clock for each user
        }
        System.out.println(user.getUsername() + " joined the room.");
    }

    public void leaveRoom(User user) {
        users.remove(user);
        vectorClocks.remove(user.getUserId());
        System.out.println(user.getUsername() + " left the room.");
    }


    public void sendMessage(User sender, String content) {
        Map<String, Integer> senderClock = vectorClocks.get(sender.getUserId());
        senderClock.put(sender.getUserId(), senderClock.get(sender.getUserId()) + 1); // Increment sender's clock

        Message message = new Message(sender.getUserId(), content, new HashMap<>(senderClock));
        messageLog.add(message);
        deliverMessages();
    }

    private void deliverMessages() {
        // Sort messages by causal order (simple implementation)
        messageLog.sort(Comparator.comparing(m -> m.getVectorClock().get(m.getSenderId())));

        for (Message message : messageLog) {
            if (!deliveredMessages.contains(message)) {
                System.out.println("Message from " + message.getSenderId() + ": " + message.getContent());
                deliveredMessages.add(message);
            }
        }
    }
}
