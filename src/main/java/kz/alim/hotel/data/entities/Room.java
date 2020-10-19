package kz.alim.hotel.data.entities;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long Id;
    public String Number;
    public int Floor;

    @ManyToOne
    public Hotel Hotel;
    @ManyToOne
    public RoomType Type;
}
