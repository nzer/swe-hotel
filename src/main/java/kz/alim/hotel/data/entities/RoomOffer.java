package kz.alim.hotel.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoomOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public float PricePerDay;
    @ElementCollection
    public List<DayOfWeek> Days;
    @ManyToOne
    @JsonBackReference
    public RoomType RoomType;
    @ManyToOne
    public Season Season;
    @ManyToMany
    public List<RoomOfferDiscount> Discounts;

    public enum DayOfWeek {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }
}
