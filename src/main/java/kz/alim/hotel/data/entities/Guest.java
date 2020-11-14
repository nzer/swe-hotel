package kz.alim.hotel.data.entities;

import javax.persistence.*;

@Entity
public class Guest extends Account {
    public String Name;
    public String Address;
    public String HomePhone;
    public String MobilePhone;
    public IdentityType IdentityType;
    public String IdentityData;
    @ManyToOne
    public GuestType GuestType;

    public enum IdentityType {
        Passport, DrivingLicense
    }
}


