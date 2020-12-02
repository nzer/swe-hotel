package kz.alim.hotel.data.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RoomCleaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public LocalDateTime Datetime;
    public Boolean Completed;
    @ManyToOne
    public Employee Cleaner;
    @ManyToOne
    public Room Room;
}
