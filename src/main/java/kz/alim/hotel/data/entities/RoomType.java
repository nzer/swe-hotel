package kz.alim.hotel.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long Id;
    public String Name;
    public float Size;
    public int Capacity;
    @ManyToOne
    public Hotel Hotel;
    @ManyToMany
    public List<RoomFeature> Features;
}
