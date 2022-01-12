package com.mongo.json.util;

import com.mongo.json.exceptions.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommandParser {

    public String validateReadFile(String[] args) throws InvalidArgumentException, IOException, URISyntaxException {
        if (args == null)
            throw new InvalidArgumentException("Missing arguments. Expected format is: runParser -f|-file filePath");

        var result = switch (args.length) {
            case 0 -> throw new InvalidArgumentException("Missing arguments. Expected format is: runParser -f|-file filePath");
            case 1 -> throw new InvalidArgumentException("Missing arguments. Expected format is: runParser -f|-file filePath");
            case 2 -> readFile(args);
            default -> throw new InvalidArgumentException("Invalid arguments. Expected format is: runParser -f|-file filePath");
        };
        return result;
    }

    public String readFile(String[] args) throws IOException, InvalidArgumentException, URISyntaxException {
        if (args[0].equals("-f") || args[0].equals("-file")) {
            try {
                return Files.readString(Paths.get(args[1]));
            } catch (Exception e) {
                try {
                    //try to read from relative Path
                    return readRelativePath(args[1]);
                } catch (IOException e1) {
                    throw new IOException("Error processing file: please check if path is correct." + e1.getMessage());
                }
            }
        } else
            throw new InvalidArgumentException("Missing arguments. Expected format is: runParser -f|-file filePath");
    }

    private String readRelativePath(String relativePath) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        InputStream in = this.getClass().getResourceAsStream(relativePath);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }
}
