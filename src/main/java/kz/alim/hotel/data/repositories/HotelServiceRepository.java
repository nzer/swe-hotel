package kz.alim.hotel.data.repositories;

import kz.alim.hotel.data.entities.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServiceRepository extends JpaRepository<HotelService, Long> {
}
