package kz.alim.hotel.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class RoomType {
    @Id
    public long Id;
    public String Name;
    public float Size;
    public int Capacity;
    @ManyToOne
    public Hotel Hotel;
    @ManyToMany
    public List<RoomFeature> Features;
}
