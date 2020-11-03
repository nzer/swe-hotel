package kz.alim.hotel.controllers;

import kz.alim.hotel.data.HotelRepository;
import kz.alim.hotel.data.ReservationRepository;
import kz.alim.hotel.data.RoomRepository;
import kz.alim.hotel.data.entities.Hotel;
import kz.alim.hotel.data.entities.Room;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationController(HotelRepository hotelRepository, ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    @PostMapping("/check")
    public List<Room> CheckAvailability(@RequestBody CheckAvailabilityDto request) {
        Hotel hotel = hotelRepository.findById(request.hotelId).orElseThrow();
        return roomRepository.findFreeRooms(hotel, request.start, request.finish);
    }

    public static class CheckAvailabilityDto implements Serializable {
        public Long hotelId;
        public LocalDateTime start;
        public LocalDateTime finish;
    }
}
