package com.cosmo.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Measure implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int number;
    private String value;
    private String unit;

    public Measure(int number, String value) {
        this.number = number;
        this.value = value;
    }

    public Measure(int number, String value, String unit) {
        this.number = number;
        this.value = value;
        this.unit = unit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
