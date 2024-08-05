package src.main.java.messages;

import java.util.Map;

public class Message {
    private String senderId;
    private User sender;
    private User receiver;
    private String content;
    private Map<String, Integer> vectorClock;
    //private Map<User, Integer> vectorClock;

    public Message(String senderId, User sender, User receiver, String content, Map<String, Integer> vectorClock) {
        this.senderId = senderId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.vectorClock = vectorClock;
    }

    public String getSenderId() {
        return senderId;
    }



    public String getContent() {
        return content;
    }

    public Map<String, Integer> getVectorClock() {
        return vectorClock;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    @Override
    public String toString() {
        return "src.main.java.messsages.Message{" +
                "senderId='" + senderId + '\'' +
                ", content='" + content + '\'' +
                ", vectorClock=" + vectorClock +
                '}';
    }
}
