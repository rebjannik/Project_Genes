package Default;

import java.time.Instant;
import java.time.Duration;

public class Debug {

    static Instant startInstant;
    static Instant previousInstant;

    static {
        startInstant = Instant.now();
        previousInstant = startInstant;
    }

    public static void Log(String logEntry) {

        Instant currentInstant = Instant.now();

        ;

        System.out.println(
            String.format("%1$7s", Duration.between(startInstant, currentInstant).toMillis()) +  
            " " + 
            String.format("%1$5s", Duration.between(previousInstant, currentInstant).toMillis()) + 
            "  " +
            logEntry);

        previousInstant = currentInstant;
    }
}