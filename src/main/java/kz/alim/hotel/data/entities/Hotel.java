package kz.alim.hotel.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long Id;
    public String Name;
    public String Address;
    @ElementCollection
    public List<String> Phones;
    @OneToMany(mappedBy = "Hotel")
    public List<Room> Rooms;
    @OneToMany(mappedBy = "Hotel")
    public List<RoomType> RoomTypes;
}
