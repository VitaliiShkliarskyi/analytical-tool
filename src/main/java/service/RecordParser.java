package service;

import java.util.List;
import model.Record;

public interface RecordParser {
    List<Record> parse(List<String> lines);
}
