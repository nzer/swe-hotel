package kz.alim.hotel.data.repositories;

import kz.alim.hotel.data.entities.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomServiceRepository extends JpaRepository<HotelService, Long> {
}
