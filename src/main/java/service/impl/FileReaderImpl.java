package service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import service.FileReader;

public class FileReaderImpl implements FileReader {
    @Override
    public List<String> readFromFile(String pathToFile) {
        try {
            List<String> lines = Files.readAllLines(Path.of(pathToFile));
            if (!validateReport(lines)) {
                throw new RuntimeException("Input data is not valid");
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + pathToFile, e);
        }
    }

    private boolean validateReport(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return false;
        }
        return Integer.parseInt(lines.get(0)) == lines.size() - 1;
    }
}
