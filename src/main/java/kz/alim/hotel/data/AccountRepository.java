package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.Account;
import kz.alim.hotel.data.entities.Guest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("select g from Account g where g.Login = ?1")
    Account findByLogin(String login);
}
