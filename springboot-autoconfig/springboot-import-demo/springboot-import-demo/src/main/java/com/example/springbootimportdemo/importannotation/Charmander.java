package com.example.springbootimportdemo.importannotation;

public class Charmander extends  Pokemon {
    public Charmander() {
        this.name = "小火龙";
        this.pkemonAttrEnum = PkemonAttrEnum.FIRE;
        this.age = 0;
    }

    public Charmander(int age) {
        this.name = "小火龙";
        this.pkemonAttrEnum = PkemonAttrEnum.FIRE;
        this.age = age;
    }

}
