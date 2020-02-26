package com.example.springbootimportdemo.importannotation;

public class Pikachu extends Pokemon {
    public Pikachu() {
        this.name = "皮卡丘";
        this.pkemonAttrEnum = PkemonAttrEnum.ELECTRICITY;
        this.age = 0;
    }

    public Pikachu(int age) {
        this.name = "皮卡丘";
        this.pkemonAttrEnum = PkemonAttrEnum.ELECTRICITY;
        this.age = age;
    }
}
