package com.cosmo.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.cosmo.util.HexConvert;

public class NoDo extends No implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Measure do1;
    private Measure do2;
    private Measure do3;
    private Measure do4;

    public NoDo(String[] hexAry) {
        Integer tmp = HexConvert.hex2Int(hexAry[12]);
        this.do1 = new Measure(1, String.valueOf(tmp));
        tmp = HexConvert.hex2Int(hexAry[13]);
        this.do2 = new Measure(2, String.valueOf(tmp));
        tmp = HexConvert.hex2Int(hexAry[14]);
        this.do3 = new Measure(3, String.valueOf(tmp));
        tmp = HexConvert.hex2Int(hexAry[15]);
        this.do4 = new Measure(3, String.valueOf(tmp));
        tmp = HexConvert.hex2Int(hexAry[45]);
        super.lqi = new Measure(4, String.valueOf(tmp));
    }

    public Measure getDo1() {
        return do1;
    }

    public void setDo1(Measure do1) {
        this.do1 = do1;
    }

    public Measure getDo2() {
        return do2;
    }

    public void setDo2(Measure do2) {
        this.do2 = do2;
    }

    public Measure getDo3() {
        return do3;
    }

    public void setDo3(Measure do3) {
        this.do3 = do3;
    }

    public Measure getDo4() {
        return do4;
    }

    public void setDo4(Measure do4) {
        this.do4 = do4;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
