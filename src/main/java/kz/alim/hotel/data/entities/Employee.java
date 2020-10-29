package kz.alim.hotel.data.entities;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    @ManyToOne
    public Hotel Hotel;
    @Enumerated(EnumType.STRING)
    public EmployeeType EmployeeType;

    public enum EmployeeType {
        Manager, Admin, Cleaner
    }
}
