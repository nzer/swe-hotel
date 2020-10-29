package kz.alim.hotel.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class HotelService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public String Name;
    public double Price;
    @ManyToOne
    @JsonBackReference
    public Hotel Hotel;
}
