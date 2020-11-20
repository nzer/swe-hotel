package kz.alim.hotel.controllers;

import kz.alim.hotel.data.GuestRepository;
import kz.alim.hotel.data.ReservationRepository;
import kz.alim.hotel.data.RoomOfferRepository;
import kz.alim.hotel.data.RoomRepository;
import kz.alim.hotel.data.entities.Guest;
import kz.alim.hotel.data.entities.Reservation;
import kz.alim.hotel.data.entities.Room;
import kz.alim.hotel.data.entities.RoomOffer;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

// As a desk clerk, I want to cancel, create, and change, bookings so that I can fulfill guest requests

@RestController
@RequestMapping("/api/clerk")
public class ClerkController {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final RoomOfferRepository roomOfferRepository;

    public ClerkController(ReservationRepository reservationRepository, RoomRepository roomRepository, GuestRepository guestRepository, RoomOfferRepository roomOfferRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.roomOfferRepository = roomOfferRepository;
    }

    @PostMapping
    public boolean Create(@RequestBody ClerkCreateReservationDto request) {
        Reservation reservation = new Reservation();
        reservation.PayingGuest = guestRepository.findById(request.guestId).orElseThrow();
        reservation.RoomOffer = roomOfferRepository.findById(request.roomOfferId).orElseThrow();
        reservation.Room = roomRepository.findById(request.roomId).orElseThrow();
        reservation.Start = request.start;
        reservation.End = request.finish;
        reservationRepository.save(reservation);
        return true;
    }

    @PostMapping
    public boolean Update(@RequestBody ClerkUpdateReservationDto request) {
        Reservation reservation = reservationRepository.findById(request.reservationId).orElseThrow();
        reservation.Start = request.start;
        reservation.End = request.finish;
        reservationRepository.save(reservation);
        return true;
    }

    @PostMapping
    public boolean Cancel(@RequestBody ClerkCancelReservationDto request) {
        try {
            reservationRepository.deleteById(request.reservationId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping
    public boolean AddGuest(@RequestBody ClerkModifyGuestsAtReservationDto request) {
        Reservation reservation = reservationRepository.findById(request.reservationId).orElseThrow();
        Guest guest = guestRepository.findById(request.guestId).orElseThrow();
        reservation.Occupants.add(guest);
        reservationRepository.save(reservation);
        return true;
    }

    @PostMapping
    public boolean DeleteGuest(@RequestBody ClerkModifyGuestsAtReservationDto request) {
        Reservation reservation = reservationRepository.findById(request.reservationId).orElseThrow();
        Guest guest = guestRepository.findById(request.guestId).orElseThrow();
        reservation.Occupants.remove(guest);
        reservationRepository.save(reservation);
        return true;
    }

    public static class ClerkCreateReservationDto {
        public long guestId;
        public long roomId;
        public Long roomOfferId;
        public LocalDateTime start;
        public LocalDateTime finish;
    }

    public static class ClerkModifyGuestsAtReservationDto {
        public long guestId;
        public long reservationId;
    }

    public static class ClerkUpdateReservationDto {
        public long reservationId;
        public LocalDateTime start;
        public LocalDateTime finish;
    }

    public static class ClerkCancelReservationDto {
        public long reservationId;
    }
}
