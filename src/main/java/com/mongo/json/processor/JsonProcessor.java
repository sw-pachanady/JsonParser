package com.mongo.json.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.mongo.json.exceptions.InvalidArgumentException;
import com.mongo.json.util.CommandParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry;

public class JsonProcessor {

    CommandParser commamdParser = new CommandParser();

    public String processJsonCommand(String[] args) throws IOException, InvalidArgumentException, URISyntaxException {
        String inputJson = commamdParser.validateReadFile(args);
        Map<String, String> jsonKeyMap = new HashMap<String, String>();
        JsonNode node = (new ObjectMapper()).readTree(inputJson);
        flattenJson("", node, jsonKeyMap);
        return jsonConverter(jsonKeyMap);
    }

    private String jsonConverter(Map<String, String> jsonKeyMap) {
        var jsonList = jsonKeyMap.entrySet().stream().map(entry -> "  \"" + entry.getKey() + "\":\"" + entry.getValue() + "\"").collect(Collectors.toList());
        return "{ \n" + String.join(",\n", jsonList) + " \n}";
    }


    private void flattenJson(String currentPath, JsonNode node, Map<String, String> jsonKeyMap) {
        if (node.isObject()) {
            Iterator<Entry<String, JsonNode>> fields = node.fields();
            String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";
            while (fields.hasNext()) {
                Entry<String, JsonNode> nodeEntry = fields.next();
                flattenJson(pathPrefix + nodeEntry.getKey(), nodeEntry.getValue(), jsonKeyMap);
            }
        } else if (node.isArray()) {
            // TO DO Handle repeated  entries
        } else if (node.isValueNode()) {
            ValueNode valueNode = (ValueNode) node;
            jsonKeyMap.put(currentPath, valueNode.asText());
        }
    }
}
