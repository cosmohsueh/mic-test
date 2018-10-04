package com.cosmo.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.cosmo.util.HexConvert;

public class NoDi extends No implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Measure di1;
    private Measure di2;
    private Measure di3;
    private Measure di4;

    public NoDi(String[] hexAry) {
        Integer tmp = HexConvert.hex2Int(hexAry[12]);
        this.di1 = new Measure(1, String.valueOf(tmp));
        tmp = HexConvert.hex2Int(hexAry[13]);
        this.di2 = new Measure(2, String.valueOf(tmp));
        tmp = HexConvert.hex2Int(hexAry[14]);
        this.di3 = new Measure(3, String.valueOf(tmp));
        tmp = HexConvert.hex2Int(hexAry[15]);
        this.di4 = new Measure(3, String.valueOf(tmp));
        tmp = HexConvert.hex2Int(hexAry[45]);
        super.lqi = new Measure(4, String.valueOf(tmp));
    }

    public Measure getDi1() {
        return di1;
    }

    public void setDi1(Measure di1) {
        this.di1 = di1;
    }

    public Measure getDi2() {
        return di2;
    }

    public void setDi2(Measure di2) {
        this.di2 = di2;
    }

    public Measure getDi3() {
        return di3;
    }

    public void setDi3(Measure di3) {
        this.di3 = di3;
    }

    public Measure getDi4() {
        return di4;
    }

    public void setDi4(Measure di4) {
        this.di4 = di4;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
