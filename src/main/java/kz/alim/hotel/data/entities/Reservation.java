package kz.alim.hotel.data.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long Id;
    public LocalDateTime Start;
    public LocalDateTime End;
    @ManyToOne
    public Guest PayingGuest;
    @ManyToMany
    public List<Guest> Occupants;
    @ManyToOne
    public Room Room;
}
