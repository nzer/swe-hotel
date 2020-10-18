package kz.alim.hotel.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Season {
    @Id
    public long Id;
    public String Name;
    public LocalDateTime Start;
    public LocalDateTime End;
}
