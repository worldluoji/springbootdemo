package com.example.eslearn.sender;

import com.example.eslearn.models.TestRequestInfo;

public interface Sender {
    String send(TestRequestInfo requestInfo);
}
