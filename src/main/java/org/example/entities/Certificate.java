package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student_certificate")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long certificateId;

    private String title;

    private String about;

    public long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    private String link;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
