package com.example.eslearn.models;

import org.elasticsearch.geometry.Point;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("address")
public class Address {
    String city, street;
    Point location;
}
