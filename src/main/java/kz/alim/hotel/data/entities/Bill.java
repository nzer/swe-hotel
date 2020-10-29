package kz.alim.hotel.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    @ManyToOne
    public Guest Guest;
    @ManyToOne
    public Hotel Hotel;
    @ManyToMany
    public List<Reservation> Reservations;
    @ManyToMany
    public List<HotelService> Services;
}
