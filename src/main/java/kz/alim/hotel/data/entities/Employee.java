package kz.alim.hotel.data.entities;

import javax.persistence.*;

@Entity
public class Employee extends Account {
    @ManyToOne
    public Hotel Hotel;
    @Enumerated
    public EmployeeTypeEnum EmployeeType;

    public enum EmployeeTypeEnum {
        Manager, Admin, Cleaner
    }
}
