package kz.alim.hotel.controllers;

import kz.alim.hotel.data.entities.Guest;
import kz.alim.hotel.data.entities.Hotel;
import kz.alim.hotel.data.entities.Reservation;
import kz.alim.hotel.data.entities.Room;
import kz.alim.hotel.data.repositories.*;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final RoomOfferRepository roomOfferRepository;

    public ReservationController(HotelRepository hotelRepository, ReservationRepository reservationRepository, RoomRepository roomRepository, GuestRepository guestRepository, RoomOfferRepository roomOfferRepository) {
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.roomOfferRepository = roomOfferRepository;
    }

    @PostMapping("/check")
    public List<Room> CheckAvailability(@RequestBody CheckAvailabilityRequestDto request) {
        Hotel hotel = hotelRepository.findById(request.hotelId).orElseThrow();
        return roomRepository.findFreeRooms(hotel, request.start, request.finish);
    }

    @PostMapping("/create")
    public boolean MakeReservation(Principal principal, @RequestBody MakeReservationRequestDto request) {
        if (request == null || request.start == null || request.finish == null || request.roomId == null || request.roomOfferId == null) {return false;}
        Reservation reservation = new Reservation();
        reservation.Room = roomRepository.findById(request.roomId).orElseThrow();
        reservation.RoomOffer = roomOfferRepository.findById(request.roomOfferId).orElseThrow();
        reservation.Start = request.start;
        reservation.End = request.finish;
        reservation.PayingGuest = guestRepository.findByLogin(principal.getName());
        reservationRepository.save(reservation);
        return true;
    }

    @GetMapping("/list")
    public List<Reservation> GetGuestsReservations(Principal principal) {
        try {
            Guest guest = guestRepository.findByLogin(principal.getName());
            return reservationRepository.findByPayingGuestId(guest.Id);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
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
        public Long roomOfferId;
        public LocalDateTime start;
        public LocalDateTime finish;
    }
}
