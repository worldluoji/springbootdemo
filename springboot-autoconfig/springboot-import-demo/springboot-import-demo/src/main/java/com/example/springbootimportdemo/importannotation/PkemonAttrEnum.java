package com.example.springbootimportdemo.importannotation;

public enum PkemonAttrEnum {
    FIRE(0),
    WATER(1),
    ELECTRICITY(2),
    GRASS(3),
    SUPERENERGY(4);

    private int value;
    private PkemonAttrEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
