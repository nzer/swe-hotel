package kz.alim.hotel.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoomFeature {
    @Id
    public long Id;
    public String Name;
}
