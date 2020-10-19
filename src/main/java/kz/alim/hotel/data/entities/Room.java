package kz.alim.hotel.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public String Number;
    public int Floor;

    @ManyToOne
    @JsonBackReference
    public Hotel Hotel;
    @ManyToOne
    public RoomType Type;
}
