package kz.alim.hotel.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Room {
    @Id
    public long Id;
    public String Number;
    public int Floor;

    @ManyToOne
    public Hotel Hotel;
    @ManyToOne
    public RoomType Type;
}