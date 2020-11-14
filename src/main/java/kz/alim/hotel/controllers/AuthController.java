package kz.alim.hotel.controllers;

import kz.alim.hotel.data.GuestRepository;
import kz.alim.hotel.data.entities.Account;
import kz.alim.hotel.data.entities.Guest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final GuestRepository guestRepository;

    public AuthController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping("/signin")
    public Guest Auth(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return guestRepository.findByLogin(user.getUsername());
    }

    @PostMapping("/register")
    public boolean Register(@RequestBody RegisterGuestDto request) {
        Guest guest = new Guest();
        guest.Name = request.Name;
        guest.Address = request.Address;
        guest.HomePhone = request.HomePhone;
        guest.MobilePhone = request.MobilePhone;
        guest.Login = request.Login;
        guest.Password = request.Password;
        guest.Role = Account.AccountRole.GUEST;
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
