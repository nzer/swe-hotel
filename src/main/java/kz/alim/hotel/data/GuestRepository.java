package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {
    public Guest findByLogin(String Login);
}
