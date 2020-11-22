package kz.alim.hotel.data.repositories;

import kz.alim.hotel.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
