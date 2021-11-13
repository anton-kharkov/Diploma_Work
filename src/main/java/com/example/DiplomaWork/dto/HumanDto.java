package com.example.DiplomaWork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HumanDto {

    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String gender;
    private String city_name;
    private int yearOfBirth;
    private int inn;
    private int city_id;

    public HumanDto() {
    }

    public HumanDto(String firstName, String lastName, String patronymic, String gender,
                    int yearOfBirth, int inn, int city_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.inn = inn;
        this.city_id = city_id;
    }

    public HumanDto(int id, String firstName, String lastName, String patronymic, String gender, String city_name,
                    int yearOfBirth, int inn, int city_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.gender = gender;
        this.city_name = city_name;
        this.yearOfBirth = yearOfBirth;
        this.inn = inn;
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}
