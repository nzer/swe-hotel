package kz.alim.hotel.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @OneToMany(mappedBy = "RoomType")
    @JsonManagedReference
    public List<RoomOffer> RoomOffers;

    public long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public float getSize() {
        return Size;
    }

    public int getCapacity() {
        return Capacity;
    }
}
