package com.test.entity;

import com.test.annotion.Entity;
import lombok.Data;

@Data
@Entity("hello")
public class HelloEntity {

    private String id;

    private String name;
}
