package kz.alim.hotel.controllers;

import kz.alim.hotel.data.GuestRepository;
import kz.alim.hotel.data.entities.Guest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final GuestRepository guestRepository;

    public AuthController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping("/signin")
    public Guest Auth() {
        return guestRepository.findById(1L).orElseThrow();
    }

    @PostMapping("/register")
    public boolean Register(RegisterGuestDto request) {
        Guest guest = new Guest();
        guest.Name = request.Name;
        guest.Address = request.Address;
        guest.HomePhone = request.HomePhone;
        guest.MobilePhone = request.MobilePhone;
        guest.Login = request.Login;
        guest.Password = request.Password;
        guestRepository.save(guest);
        return true;
    }

    public static class RegisterGuestDto {
        public String Name;
        public String Address;
        public String HomePhone;
        public String MobilePhone;
        public String Login;
        public String Password;
    }
}
