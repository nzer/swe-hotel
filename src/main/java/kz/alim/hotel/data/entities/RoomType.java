package kz.alim.hotel.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public String Name;
    public float Size;
    public int Capacity;
    @ManyToOne
    @JsonBackReference
    public Hotel Hotel;
    @ManyToMany
    public List<RoomFeature> Features;
}
