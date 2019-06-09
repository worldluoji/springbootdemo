package ReflectDemo.springaop;

import ReflectDemo.springaop.constants.LogLevel;

import java.time.LocalDateTime;

public class Logger {
    public static void logging(LogLevel level, String content) {
        if (level.equals(LogLevel.INFO)) {
            System.out.println("[IFNO]:" + LocalDateTime.now().toString() + " " + content);
        } else if (level.equals(LogLevel.DEBUG)) {
            System.out.println("[DEBUG]:" + LocalDateTime.now().toString() + " " + content);
        }
    }
}
