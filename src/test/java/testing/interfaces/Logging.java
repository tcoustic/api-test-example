package testing.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Logging {

    private Logger log() {
        return LoggerFactory.getLogger(getClass());
    }

    default void info(String message) {
        log().info(message);
    }
}
