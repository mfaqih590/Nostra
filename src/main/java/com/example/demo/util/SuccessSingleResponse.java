package com.example.demo.util;

import lombok.Data;

@Data
public class SuccessSingleResponse<T> {
    String status;
    String message;
    T item;
}
