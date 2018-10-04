package com.cosmo.model;

public enum TYPE {

    NO_TH(132), NO_CO2(152), NO_DI(135), NO_DO(138);

    private int typeId;

    private TYPE(int typeId) {
        this.typeId = typeId;
    }

    public int toInt() {
        return this.typeId;
    }

    public static TYPE fromInt(int value) {
        switch (value) {
        case 132:
            return NO_TH;
        case 152:
            return NO_CO2;
        case 135:
            return NO_DI;
        case 138:
            return NO_DO;
        default:
            return null;
        }
    }
}
