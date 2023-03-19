package model;

import java.time.LocalDate;
import java.util.Arrays;

public class Record {
    private String serviceId;
    private String serviceVariationId;
    private String questionId;
    private String categoryId;
    private String subcategoryId;
    private ResponseType responseType;
    private ResponseTime responseTime;
    private int waitingTime;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceVariationId() {
        return serviceVariationId;
    }

    public void setServiceVariationId(String serviceVariationId) {
        this.serviceVariationId = serviceVariationId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public ResponseTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(ResponseTime responseTime) {
        this.responseTime = responseTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public static ResponseType getResponseTypeByLetter(String letter) {
        return Arrays.stream(ResponseType.values())
                .filter(o -> o.getLetter().equals(letter))
                .findFirst().get();
    }

    public static ResponseTime getResponseTimeByLetter(String letter) {
        return Arrays.stream(ResponseTime.values())
                .filter(o -> o.getLetter().equals(letter))
                .findFirst().get();
    }

    public enum ResponseType {
        C("C"),
        D("D");

        private final String letter;

        ResponseType(String letter) {
            this.letter = letter;
        }

        public String getLetter() {
            return letter;
        }

    }

    public enum ResponseTime {
        P("P"),
        N("N");

        private final String letter;

        ResponseTime(String letter) {
            this.letter = letter;
        }

        public String getLetter() {
            return letter;
        }

    }
}
