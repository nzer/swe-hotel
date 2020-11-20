package kz.alim.hotel.controllers;

import kz.alim.hotel.data.GuestRepository;
import kz.alim.hotel.data.HotelRepository;
import kz.alim.hotel.data.ReservationRepository;
import kz.alim.hotel.data.RoomRepository;
import kz.alim.hotel.data.entities.Hotel;
import kz.alim.hotel.data.entities.Reservation;
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
    private final GuestRepository guestRepository;

    public ReservationController(HotelRepository hotelRepository, ReservationRepository reservationRepository, RoomRepository roomRepository, GuestRepository guestRepository) {
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    @PostMapping("/check")
    public List<Room> CheckAvailability(@RequestBody CheckAvailabilityRequestDto request) {
        Hotel hotel = hotelRepository.findById(request.hotelId).orElseThrow();
        return roomRepository.findFreeRooms(hotel, request.start, request.finish);
    }

    @PostMapping("/create")
    public boolean MakeReservation(@RequestBody MakeReservationRequestDto request) {
        Reservation reservation = new Reservation();
        reservation.Room = roomRepository.findById(request.roomId).orElseThrow();
        reservation.Start = request.start;
        reservation.End = request.finish;
        reservation.PayingGuest = guestRepository.findById(1L).orElseThrow();
        reservationRepository.save(reservation);
        return true;
    }

    @GetMapping("/list")
    public List<Reservation> GetGuestsReservations(@RequestParam Long guestId) {
        return reservationRepository.findByPayingGuestId(guestId);
    }

    @GetMapping("/delete")
    public boolean DeleteReservation(Long reservationId) {
        try {
            reservationRepository.deleteById(reservationId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static class CheckAvailabilityRequestDto implements Serializable {
        public Long hotelId;
        public LocalDateTime start;
        public LocalDateTime finish;
    }

    public static class MakeReservationRequestDto implements Serializable {
        public Long roomId;
        public LocalDateTime start;
        public LocalDateTime finish;
    }
}
