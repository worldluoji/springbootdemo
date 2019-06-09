package ReflectDemo.model;

import java.util.List;

public class User {
    private String name;
    private int age;
    private short gender;
    private String address;
    private List<String> hobbies;

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

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    private String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public User(String name, int age, short gender, String address, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.hobbies = hobbies;
    }

    public User() {
    }

    public User(String name, int age, short gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

}
