package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Koala {

    private Integer id;
    private String name;
    private Double sleepHour;
    private Double weight;
    private String gender;



}