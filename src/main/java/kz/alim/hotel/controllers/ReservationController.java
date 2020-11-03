package kz.alim.hotel.controllers;

import kz.alim.hotel.data.HotelRepository;
import kz.alim.hotel.data.ReservationRepository;
import kz.alim.hotel.data.RoomRepository;
import kz.alim.hotel.data.entities.Hotel;
import kz.alim.hotel.data.entities.Room;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("check")
    public List<Room> CheckAvailability(LocalDateTime start, LocalDateTime finish, Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();
        return roomRepository.findFreeRooms(hotel, start, finish);
    }
}
