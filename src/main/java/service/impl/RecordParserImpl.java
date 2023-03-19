package service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import model.Record;
import service.RecordParser;

public class RecordParserImpl implements RecordParser {
    private static final String PARAMETERS_SEPARATOR = " ";
    private static final String SUB_PARAMETERS_SEPARATOR = "\\.";
    private static final String DATE_SEPARATOR = "-";
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final int RESPONSE_TYPE_INDEX = 0;
    private static final int SERVICE_INDEX = 1;
    private static final int QUESTION_INDEX = 2;
    private static final int RESPONSE_TIME_INDEX = 3;
    private static final int DATE_INDEX = 4;
    private static final int WAITING_TIME_INDEX = 5;

    @Override
    public List<Record> parse(List<String> lines) {
        return lines.stream()
                .skip(1)
                .map(this::parseLine)
                .collect(Collectors.toList());
    }

    private Record parseLine(String line) {
        Record record = new Record();
        String[] params = line.split(PARAMETERS_SEPARATOR);
        String[] service = params[SERVICE_INDEX].split(SUB_PARAMETERS_SEPARATOR);
        String[] question = params[QUESTION_INDEX].split(SUB_PARAMETERS_SEPARATOR);
        String[] dates = params[DATE_INDEX].split(DATE_SEPARATOR);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        record.setResponseType(Record.getResponseTypeByLetter(params[RESPONSE_TYPE_INDEX]));
        record.setServiceId(service[0]);
        record.setServiceVariationId(service.length > 1 ? service[1] : null);
        record.setQuestionId(question[0]);
        record.setCategoryId(question.length > 1 ? question[1] : null);
        record.setSubcategoryId(question.length > 2 ? question[2] : null);
        record.setResponseTime(Record.getResponseTimeByLetter(params[RESPONSE_TIME_INDEX]));
        record.setDateFrom(checkFormat(dates[0], formatter));
        record.setDateTo(dates.length > 1 ? checkFormat(dates[1], formatter) : null);
        if (params.length > 5) {
            record.setWaitingTime(Integer.parseInt(params[WAITING_TIME_INDEX]));
        }
        return record;
    }

    private LocalDate checkFormat(String date, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Date " + date + " must be in format: " + DATE_PATTERN, e);
        }
    }
}
