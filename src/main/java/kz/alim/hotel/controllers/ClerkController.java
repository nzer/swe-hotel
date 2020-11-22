package kz.alim.hotel.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.alim.hotel.data.GuestRepository;
import kz.alim.hotel.data.ReservationRepository;
import kz.alim.hotel.data.RoomOfferRepository;
import kz.alim.hotel.data.RoomRepository;
import kz.alim.hotel.data.entities.Account;
import kz.alim.hotel.data.entities.Guest;
import kz.alim.hotel.data.entities.Reservation;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

    @Operation(security = { @SecurityRequirement(name = "BasicAuth") })
    @GetMapping("/reservation/list")
    public Iterable<Reservation> List() {
        return reservationRepository.findAll();
    }

    @PostMapping("/reservation/create")
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

    @PostMapping("/reservation/update")
    public boolean Update(@RequestBody ClerkUpdateReservationDto request) {
        Reservation reservation = reservationRepository.findById(request.reservationId).orElseThrow();
        reservation.Start = request.start;
        reservation.End = request.finish;
        reservationRepository.save(reservation);
        return true;
    }

    @PostMapping("/reservation/cancel")
    public boolean Cancel(@RequestBody ClerkCancelReservationDto request) {
        try {
            reservationRepository.deleteById(request.reservationId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/reservation/guests/add")
    public boolean AddGuest(@RequestBody ClerkModifyGuestsAtReservationDto request) {
        Reservation reservation = reservationRepository.findById(request.reservationId).orElseThrow();
        Guest guest = guestRepository.findById(request.guestId).orElseThrow();
        reservation.Occupants.add(guest);
        reservationRepository.save(reservation);
        return true;
    }

    @PostMapping("/reservation/guests/delete")
    public boolean DeleteGuest(@RequestBody ClerkModifyGuestsAtReservationDto request) {
        Reservation reservation = reservationRepository.findById(request.reservationId).orElseThrow();
        Guest guest = guestRepository.findById(request.guestId).orElseThrow();
        reservation.Occupants.remove(guest);
        reservationRepository.save(reservation);
        return true;
    }

    @GetMapping("/guests/list")
    public Iterable<Guest> GetAllGuests() {
        return guestRepository.findAll();
    }

    @PostMapping("/guests/register")
    public Guest RegisterGuest(@RequestBody ClerkRegisterGuestDto request) {
        Guest guest = new Guest();
        guest.Name = request.Name;
        guest.Address = request.Address;
        guest.HomePhone = request.HomePhone;
        guest.MobilePhone = request.MobilePhone;
        guest.Role = Account.AccountRole.GUEST;
        guest.Login = request.Login;
        guest.Password = "test";
        guestRepository.save(guest);
        return guest;
    }

    public static class ClerkCreateReservationDto implements Serializable {
        public long guestId;
        public long roomId;
        public Long roomOfferId;
        public LocalDateTime start;
        public LocalDateTime finish;
    }

    public static class ClerkModifyGuestsAtReservationDto implements Serializable {
        public long guestId;
        public long reservationId;
    }

    public static class ClerkUpdateReservationDto implements Serializable {
        public long reservationId;
        public LocalDateTime start;
        public LocalDateTime finish;
    }

    public static class ClerkCancelReservationDto implements Serializable {
        public long reservationId;
    }

    public static class ClerkRegisterGuestDto {
        public String Name;
        public String Address;
        public String HomePhone;
        public String MobilePhone;
        public String Login;
    }
}
