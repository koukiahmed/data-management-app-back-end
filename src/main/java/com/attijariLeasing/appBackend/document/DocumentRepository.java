package com.attijariLeasing.appBackend.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    @Query("SELECT new Document (d.id, d.title, d.type, d.folder, d.date) FROM Document d")
    List<Document> findAll();
}
