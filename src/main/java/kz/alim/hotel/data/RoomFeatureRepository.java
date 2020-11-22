package kz.alim.hotel.data;

import kz.alim.hotel.data.entities.RoomFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomFeatureRepository extends JpaRepository<RoomFeature, Long> {
}
