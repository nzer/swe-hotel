package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.Hotel;
import kz.alim.hotel.data.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    @Query("select Room from Room join Reservation on Reservation.Room = Room " +
            "where Reservation.End < ?2 and Reservation.Start > ?3 and Room.Hotel = ?1")
    List<Room> findFreeRooms(Hotel hotel, LocalDateTime start, LocalDateTime finish);
}
