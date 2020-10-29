package kz.alim.hotel.data.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public String Name;
    public String Address;
    @ElementCollection
    public List<String> Phones;
    @OneToMany(mappedBy = "Hotel")
    @JsonManagedReference
    public List<Room> Rooms;
    @OneToMany(mappedBy = "Hotel")
    @JsonManagedReference
    public List<RoomType> RoomTypes;
    @OneToMany
    public List<HotelService> Services;
}
