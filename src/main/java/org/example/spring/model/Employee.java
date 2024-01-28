package org.example.spring.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "ban phai nhap ten")
    private String name;
    @Min(value = 0, message = "ban phai nhap luong")
    private int salary;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    public void setOffice(Office office) {
        this.office = office;
    }

    public Office getOffice() {
        return office;
    }

    public Employee() {
    }

    public Employee(@NotEmpty @Size(min = 2, max = 30) int id, String name, @Min(0) int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
