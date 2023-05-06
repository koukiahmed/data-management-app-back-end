package com.attijariLeasing.appBackend.folder;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "folders")
public class Folder {

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
            name = "User_name",
            nullable = false
    )
    private String userName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @PrePersist
    private void onCreate(){
        date = new Date();
    }

    public Folder() {
    }

    public Folder(String title, String userName) {
        this.title = title;
        this.userName = userName;
    }
}
