package br.com.serviceordersystem.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> fieldMessages = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }

    public void addError(String fieldName, String message) {
        this.fieldMessages.add(new FieldMessage(fieldName, message));
    }
}
