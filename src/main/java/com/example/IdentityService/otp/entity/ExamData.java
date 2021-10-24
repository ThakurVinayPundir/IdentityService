package com.example.IdentityService.otp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exam_data")
public class ExamData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private long userId;

    @Column(name = "exam_name")
    @JsonProperty("exam_name")
    private String examName;

    @Column(name = "exam_date")
    @JsonProperty("exam_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate examDate;

    @Column(name = "admit_card_release_date")
    @JsonProperty("admit_card_release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admitCardReleaseDate;

    public ExamData() {
    }

    public ExamData(long id, long userId, String examName, LocalDate examDate, LocalDate admitCardReleaseDate) {
        this.id = id;
        this.userId = userId;
        this.examName = examName;
        this.examDate = examDate;
        this.admitCardReleaseDate = admitCardReleaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
