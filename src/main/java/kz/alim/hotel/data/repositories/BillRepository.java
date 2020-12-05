package kz.alim.hotel.data.repositories;

import kz.alim.hotel.data.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
