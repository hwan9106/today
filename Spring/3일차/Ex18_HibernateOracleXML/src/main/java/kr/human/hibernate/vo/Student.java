package kr.human.hibernate.vo;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Student implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String section;
}
