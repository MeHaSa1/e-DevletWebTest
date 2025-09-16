package tr.com.turksat.test;

import io.qameta.allure.Allure;
import org.apache.log4j.Logger;

public class Logging {
    private static final Logger logger = Logger.getLogger(Logging.class);

    public static void info(String message){
        Allure.step(message);
        logger.info(message);
    }
}
