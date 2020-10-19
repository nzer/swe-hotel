package kz.alim.hotel.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoomOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long Id;
    public float PricePerDay;
    @ElementCollection
    public List<DayOfWeek> Days;
    @ManyToOne
    public Room Room;
    @ManyToOne
    public Season Season;
    @ManyToMany
    public List<RoomOfferDiscount> Discounts;

    public enum DayOfWeek {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }
}
