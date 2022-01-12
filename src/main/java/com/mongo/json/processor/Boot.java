package com.mongo.json.processor;

import java.util.logging.Logger;

public class Boot {

    private static final Logger LOGGER = Logger.getLogger(Boot.class.getSimpleName());

    public static void main(String[] args) {
        try {
            JsonProcessor jsonProcessor = new JsonProcessor();
            String flattenedJson = jsonProcessor.processJsonCommand(args);
            LOGGER.info(String.format("Flattened json is: \n %s", flattenedJson));
        } catch (Exception e) {
            LOGGER.severe( e.getMessage());
        }
    }
}
