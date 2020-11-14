package kz.alim.hotel.data.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    public static final String ROLE_GUEST = "GUEST";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public String Login;
    public String Password;
    public String Role;
}
