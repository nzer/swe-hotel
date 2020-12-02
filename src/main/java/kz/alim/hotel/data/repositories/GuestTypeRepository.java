package kz.alim.hotel.data.repositories;

import kz.alim.hotel.data.entities.GuestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestTypeRepository extends JpaRepository<GuestType, Long> {
}
