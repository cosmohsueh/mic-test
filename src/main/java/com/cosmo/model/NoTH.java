package com.cosmo.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.cosmo.util.HexConvert;

public class NoTH extends No implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Measure temperature;
    private Measure humidity;
    private Measure voltage;

    public NoTH(String[] hexAry) {
        Integer tmp = HexConvert.hex2Int(hexAry[12] + hexAry[13]);
        this.temperature = new Measure(1, String.valueOf(tmp.floatValue() / 100), "°C");
        tmp = HexConvert.hex2Int(hexAry[14] + hexAry[15]);
        this.humidity = new Measure(2, String.valueOf(tmp.floatValue() / 100), "%RH");
        tmp = HexConvert.hex2Int(hexAry[16] + hexAry[17]);
        this.voltage = new Measure(3, String.valueOf(tmp.floatValue() / 1000), "V");
        tmp = HexConvert.hex2Int(hexAry[45]);
        super.lqi = new Measure(4, String.valueOf(tmp));
    }

    public Measure getTemperature() {
        return temperature;
    }

    public void setTemperature(Measure temperature) {
        this.temperature = temperature;
    }

    public Measure getHumidity() {
        return humidity;
    }

    public void setHumidity(Measure humidity) {
        this.humidity = humidity;
    }

    public Measure getVoltage() {
        return voltage;
    }

    public void setVoltage(Measure voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
