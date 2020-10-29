package kz.alim.hotel.data.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class Cleaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public LocalDateTime Date;
    public boolean Completed;
    @OneToOne
    public Employee Employee;
}
