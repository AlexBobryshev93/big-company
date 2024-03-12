package org.swiss.re.csv;

import org.swiss.re.config.ParseConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVParser {

    private final ParseConfig config;

    public CSVParser(ParseConfig config) {
        this.config = config;
    }

    public ParseConfig getConfig() {
        return config;
    }

    public List<List<String>> parseCSV() {
        var records = new ArrayList<List<String>>();
        var pathToFile = config.getPath() + config.getPathDelimiter() + config.getFile();
        try (var scanner = new Scanner(new File(pathToFile))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            // log.error imitation
            System.out.println("Error occurred while trying to parse from CSV: " + e.getMessage());
        }
        return records;
    }

    private List<String> getRecordFromLine(String line) {
        var values = new ArrayList<String>();
        try (var rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(config.getCommaDelimiter());
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
