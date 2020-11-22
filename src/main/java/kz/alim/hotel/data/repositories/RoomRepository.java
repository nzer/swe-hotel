package kz.alim.hotel.data.repositories;

import kz.alim.hotel.data.entities.Hotel;
import kz.alim.hotel.data.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    @Query("select r1 from Room r1 where not exists (select r2 from Reservation r2 where r2.Room = r1 and r2.End <= ?3 and r2.Start >= ?2 and r1.Hotel = ?1)")
    List<Room> findFreeRooms(Hotel hotel, LocalDateTime start, LocalDateTime finish);
}
