package kz.alim.hotel.data.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Hotel {
    @Id
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
