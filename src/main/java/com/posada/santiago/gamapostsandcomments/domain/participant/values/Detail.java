package com.posada.santiago.gamapostsandcomments.domain.participant.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Detail implements ValueObject<String> {

    private final String value;

    public Detail(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String value() {
        return value;
    }
}
