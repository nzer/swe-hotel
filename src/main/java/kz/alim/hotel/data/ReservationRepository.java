package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.Guest;
import kz.alim.hotel.data.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    //List<Reservation> findByPayingGuest(Guest guest);
}
