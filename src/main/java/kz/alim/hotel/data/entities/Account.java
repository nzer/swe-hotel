package kz.alim.hotel.data.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    @Column(unique=true)
    public String Login;
    public String Password;
    public AccountRole Role;

    public enum AccountRole {
        GUEST, CLERK
    }
}
