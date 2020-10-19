package kz.alim.hotel.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long Id;
    public String Name;
    public LocalDateTime Start;
    public LocalDateTime End;
}
