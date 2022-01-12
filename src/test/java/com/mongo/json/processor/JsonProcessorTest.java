package com.mongo.json.processor;

import com.fasterxml.jackson.core.JsonParseException;
import com.mongo.json.exceptions.InvalidArgumentException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class JsonProcessorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testProcessJsonCommand() throws Exception {
        String expectedData = """
                {\s
                  "a":"1",
                  "b":"true",
                  "c.d":"3",
                  "c.e":"test"\s
                }
                """;
        String[] args = new String[]{"-f", "/data.json"};
        JsonProcessor jsonProcessor = new JsonProcessor();
        String result = jsonProcessor.processJsonCommand(args);
        assertEquals(expectedData.trim(), result.trim());
    }

    @Test
    public void testProcessJsonCommandWithInvalidJson() throws Exception {
        exception.expect(JsonParseException.class);
        String[] args = new String[]{"-f", "/baddata.json"};
        JsonProcessor jsonProcessor = new JsonProcessor();
        jsonProcessor.processJsonCommand(args);
    }

    @Test
    public void testInvalidArguments() throws Exception {
        exception.expect(InvalidArgumentException.class);
        String[] args = new String[]{"-c", "xyze.json"};
        JsonProcessor jsonProcessor = new JsonProcessor();
        jsonProcessor.processJsonCommand(args);
    }

    @Test
    public void testInvalidArgumentsMessage() throws Exception {
        exception.expect(InvalidArgumentException.class);
        exception.expectMessage("Missing arguments. Expected format is: runParser -f|-file filePath");
        String[] args = new String[]{"-c", "wxyz.json"};
        JsonProcessor jsonProcessor = new JsonProcessor();
        jsonProcessor.processJsonCommand(args);
    }

    @Test
    public void testMissingArguments() throws Exception {
        exception.expect(InvalidArgumentException.class);
        exception.expectMessage("Invalid arguments. Expected format is: runParser -f|-file filePath");
        String[] args = new String[]{"abc.json", "xyz.json", "mde.json"};
        JsonProcessor jsonProcessor = new JsonProcessor();
        jsonProcessor.processJsonCommand(args);
    }

    @Test
    public void testOrderJsonFile() throws Exception {
        String expectedData = """
                {\s
                  "Order.Date":"2011-05-31T00:00:00",
                  "Product.SubProduct.type":"grain",
                  "Order.Number":"SO43659",
                  "Product.SubProduct.datepacked":"12/12/21",
                  "Product.source":"India",
                  "Product.SubProduct.price":"12.3"\s
                }
                """;
        String[] args = new String[]{"-f", "/order.json"};
        JsonProcessor jsonProcessor = new JsonProcessor();

        String result = jsonProcessor.processJsonCommand(args);
        assertEquals(expectedData.trim(), result.trim());
    }
}
