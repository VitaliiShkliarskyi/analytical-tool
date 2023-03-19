package service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Record;
import service.RecordService;

public class RecordServiceImpl implements RecordService {
    private static final String ALL_TYPES_MATCHER = "*";
    private static final String ZERO_RESULT = "-";
    private static final String DELIMITER = "\n";

    @Override
    public String doAnalytic(List<Record> records) {
        return process(groupRecords(records));
    }

    private Map<Record, List<Record>> groupRecords(List<Record> records) {
        Map<Record, List<Record>> processingRecords = new LinkedHashMap<>();
        List<Record> waitingQueries = new ArrayList<>();
        for (Record record : records) {
            if (record.getResponseType() == Record.ResponseType.C) {
                waitingQueries.add(record);
            }
            if (record.getResponseType() == Record.ResponseType.D) {
                processingRecords.put(record, waitingQueries);
            }
        }
        return processingRecords;
    }

    private String process(Map<Record, List<Record>> records) {
        return records.entrySet().stream()
                .map(entry -> validateRecords(entry.getKey(), entry.getValue()))
                .map(this::calculateTime)
                .collect(Collectors.joining(DELIMITER));
    }

    private List<Record> validateRecords(Record record, List<Record> records) {
        return records.stream()
                .filter(c -> (record.getServiceId().equals(ALL_TYPES_MATCHER)
                        || record.getServiceId().equals(c.getServiceId()))
                        && (record.getQuestionId().equals(ALL_TYPES_MATCHER)
                        || record.getQuestionId().equals(c.getQuestionId()))
                        && ((record.getDateTo() == null
                        && c.getDateFrom().isAfter(record.getDateFrom()))
                        || (c.getDateFrom().isAfter(record.getDateFrom())
                        && c.getDateFrom().isBefore(record.getDateTo()))))
                .collect(Collectors.toList());
    }

    private String calculateTime(List<Record> validQueries) {
        int avg = (int) validQueries.stream()
                .mapToInt(Record::getWaitingTime)
                .average()
                .orElse(0);
        return avg > 0 ? String.valueOf(avg) : ZERO_RESULT;
    }
}
