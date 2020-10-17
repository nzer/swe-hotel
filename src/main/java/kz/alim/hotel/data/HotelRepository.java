package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
