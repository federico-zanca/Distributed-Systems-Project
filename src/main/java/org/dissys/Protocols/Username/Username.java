package org.dissys.Protocols.Username;

import java.io.Serializable;
import java.time.Instant;
import java.util.Random;

public class Username implements Serializable {
    private transient final Random random = new Random();
    private static final int CODE_DIGITS = 4;
    private final String name;
    private transient final Instant creationTimestamp; // TODO forse è necessario serializzarla
    private final String code;

    public Username(String name){
        this.name = name;
        creationTimestamp = Instant.now();
        code = generateRandomCode(CODE_DIGITS);
    }

    private String generateRandomCode(int digits){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<digits; i++){
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }
    @Override
    public String toString(){
        return name + "#" + code;
    }
}
