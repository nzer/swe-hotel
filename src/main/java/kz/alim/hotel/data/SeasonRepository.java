package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.Season;
import org.springframework.data.repository.CrudRepository;

public interface SeasonRepository extends CrudRepository<Season, Long> {
}
