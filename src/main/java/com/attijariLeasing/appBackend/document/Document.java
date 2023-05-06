package com.attijariLeasing.appBackend.document;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "type"
    )
    private String type;

    @Lob
    private byte[] data;

    @Column(
            name = "Folder_name",
            nullable = false
    )
    private String folder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @PrePersist
    private void onCreate(){
        date = new Date();
    }

    public Document() {
    }

    public Document(String title, String type, byte[] data, String folder) {
        this.title = title;
        this.type = type;
        this.data = data;
        this.folder= folder;
    }

    public Document(Long id, String title, String type, String folder, Date date) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.folder = folder;
        this.date = date;
    }


}
