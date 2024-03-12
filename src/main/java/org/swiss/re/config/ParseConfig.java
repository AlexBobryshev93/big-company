package org.swiss.re.config;

public class ParseConfig {

    private String commaDelimiter = ",";
    private String pathDelimiter = "/";
    private String path = "src/main/resources";
    private String file = "data.csv";
    private Long skipLines = 1L;

    public String getCommaDelimiter() {
        return commaDelimiter;
    }

    public void setCommaDelimiter(String commaDelimiter) {
        this.commaDelimiter = commaDelimiter;
    }

    public String getPathDelimiter() {
        return pathDelimiter;
    }

    public void setPathDelimiter(String pathDelimiter) {
        this.pathDelimiter = pathDelimiter;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getSkipLines() {
        return skipLines;
    }

    public void setSkipLines(Long skipLines) {
        this.skipLines = skipLines;
    }
}
