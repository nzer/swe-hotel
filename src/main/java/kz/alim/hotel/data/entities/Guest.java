package kz.alim.hotel.data.entities;

import javax.persistence.*;

@Entity
public class Guest extends Account {
    public String Name;
    public String Address;
    public String HomePhone;
    public String MobilePhone;
    @Enumerated(EnumType.STRING)
    public IdentityType IdentityType;
    @ManyToOne
    public GuestType Type;

    public enum IdentityType {
        Passport, DrivingLicense
    }
}


