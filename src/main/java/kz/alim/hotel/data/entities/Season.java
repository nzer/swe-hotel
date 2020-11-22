package kz.alim.hotel.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    public String Name;
    public LocalDateTime Start;
    public LocalDateTime End;

    public long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStart(LocalDateTime start) {
        Start = start;
    }

    public void setEnd(LocalDateTime end) {
        End = end;
    }
}
