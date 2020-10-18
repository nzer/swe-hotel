package kz.alim.hotel.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class RoomOfferDiscount {
    @Id
    public long Id;
    public String Name;
    public float Coefficient;
    @ManyToOne
    public GuestType OfferedTo;
}
