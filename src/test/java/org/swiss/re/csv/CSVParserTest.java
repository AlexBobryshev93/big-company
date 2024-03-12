package org.swiss.re.csv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.swiss.re.config.ParseConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVParserTest {

    private static final String TEST_PATH = "src/test/resources";
    private static final String TEST_FILE = "test-data.csv";

    private final CSVParser subject = new CSVParser(new ParseConfig());

    @BeforeEach
    void init() {
        subject.getConfig().setPath(TEST_PATH);
        subject.getConfig().setFile(TEST_FILE);
    }

    @Test
    void testParseCSV() {
        var headers = List.of("Id", "firstName", "lastName", "salary", "managerId");
        var values1 = List.of("123", "Joe", "Doe", "60000");
        var values2 = List.of("124","Martin","Chekov","45000","123");

        var result = subject.parseCSV();

        assertEquals(3, result.size());
        assertEquals(headers, result.get(0));
        assertEquals(values1, result.get(1));
        assertEquals(values2, result.get(2));
    }
}
