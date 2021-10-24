package com.example.IdentityService.otp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamUpdateRequest {
    @JsonProperty(value = "user_id")
    private long userId;

    @JsonProperty(value = "exam_name")
    private String examName;

    @JsonProperty(value = "exam_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate examDate;

    @JsonProperty(value = "admit_card_release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admitCardReleaseDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public LocalDate getAdmitCardReleaseDate() {
        return admitCardReleaseDate;
    }

    public void setAdmitCardReleaseDate(LocalDate admitCardReleaseDate) {
        this.admitCardReleaseDate = admitCardReleaseDate;
    }

    @Override
    public String toString() {
        return "ExamUpdateRequest{" +
                "userId=" + userId +
                ", examName='" + examName + '\'' +
                ", examDate=" + examDate +
                ", admitCardReleaseDate=" + admitCardReleaseDate +
                '}';
    }
}
