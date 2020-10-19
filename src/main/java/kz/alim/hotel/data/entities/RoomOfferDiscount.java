package kz.alim.hotel.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoomOfferDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long Id;
    public String Name;
    public float Coefficient;
    @ManyToOne
    public GuestType OfferedTo;
}
