package com.attijariLeasing.appBackend.actuality;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "actualities")
public class Actuality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "link",
            nullable = false
    )
    private String link;

    @Column(
            name = "body"
    )
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @PrePersist
    private void onCreate(){
        date = new Date();
    }

    public Actuality() {
    }

    public Actuality(String title, String link, String body) {
        this.title = title;
        this.link = link;
        this.body = body;
    }
}
