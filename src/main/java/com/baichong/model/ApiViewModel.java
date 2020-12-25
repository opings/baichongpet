package com.baichong.model;

import lombok.Data;

import java.util.List;

@Data
public class ApiViewModel {
    private int code;
    private String msg;
    private List<AnimalViewModel> newslist;
}
