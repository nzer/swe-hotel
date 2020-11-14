package kz.alim.hotel.data.entities;

import javax.persistence.*;

@Entity
public class Employee extends Account {
    @ManyToOne
    public Hotel Hotel;
    @Enumerated(EnumType.STRING)
    public EmployeeType EmployeeType;

    public enum EmployeeType {
        Manager, Admin, Cleaner
    }
}
