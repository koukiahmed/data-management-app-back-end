package com.attijariLeasing.appBackend.document;

import lombok.Data;

@Data
public class ModifyObject {

    //modification object for documents title and folder
    private String folder;

    public ModifyObject() {
    }

    public ModifyObject(String folder) {
        this.folder = folder;
    }
}
