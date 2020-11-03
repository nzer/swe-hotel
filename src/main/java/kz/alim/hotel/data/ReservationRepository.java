package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.Guest;
import kz.alim.hotel.data.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("select Reservation from Reservation where Reservation.PayingGuest.Id = ?1")
    List<Reservation> findByPayingGuestId(Long guestId);
}
