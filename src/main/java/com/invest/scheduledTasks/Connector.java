package com.invest.scheduledTasks;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Configuration
@EnableScheduling
public class Connector {

    private final static Logger LOGGER = Logger.getLogger(Connector.class);

    @Scheduled(cron = "* */10 * * * *")
    public void sendRequest() {
        try {
            maintainConnector();
        } catch (IOException e) {
            LOGGER.error("Can not connect to server, " + e.getMessage());
        }
    }

    private void maintainConnector() throws IOException {
        String path = "https://ip-maintain-app.herokuapp.com/main/maintain";
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            LOGGER.info("Connector works");
        }
        connection.disconnect();
    }

}

