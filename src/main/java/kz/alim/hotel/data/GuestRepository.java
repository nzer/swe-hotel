package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.Guest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {
    @Query("select g from Guest g where g.Login = ?1")
    Guest findByLogin(String login);
}
