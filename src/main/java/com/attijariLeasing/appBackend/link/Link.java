package com.attijariLeasing.appBackend.link;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "links")
public class Link {

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
            name = "path",
            nullable = false
    )
    private String path;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @PrePersist
    private void onCreate(){
        date = new Date();
    }

    public Link() {
    }

    public Link(String title, String path) {
        this.title = title;
        this.path = path;
    }
}
