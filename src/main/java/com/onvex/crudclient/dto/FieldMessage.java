package com.onvex.crudclient.dto;

public class FieldMessage {
    private String fieldName;
    private String messsage;

    public FieldMessage(String fieldName, String messsage) {
        this.fieldName = fieldName;
        this.messsage = messsage;
    }

    public String getMesssage() {
        return messsage;
    }

    public String getFieldName() {
        return fieldName;
    }
}
