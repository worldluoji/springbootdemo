package com.example.springbootimportdemo.importannotation;

public abstract class Pokemon {
    protected String name;
    protected int age;
    protected PkemonAttrEnum pkemonAttrEnum;

    public void say() {
        System.out.println("我是"  + name + "，今年" + age + "岁，属性：" + pkemonAttrEnum.value());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PkemonAttrEnum getPkemonAttrEnum() {
        return pkemonAttrEnum;
    }

    public void setPkemonAttrEnum(PkemonAttrEnum pkemonAttrEnum) {
        this.pkemonAttrEnum = pkemonAttrEnum;
    }
}
