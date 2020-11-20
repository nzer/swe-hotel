package kz.alim.hotel.data.entities;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Id == account.Id &&
                Login.equals(account.Login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Login);
    }
}
