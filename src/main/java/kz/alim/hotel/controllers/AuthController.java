package kz.alim.hotel.controllers;

import kz.alim.hotel.data.repositories.EmployeeRepository;
import kz.alim.hotel.data.repositories.GuestRepository;
import kz.alim.hotel.data.repositories.HotelRepository;
import kz.alim.hotel.data.entities.Account;
import kz.alim.hotel.data.entities.Employee;
import kz.alim.hotel.data.entities.Guest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final GuestRepository guestRepository;
    private final EmployeeRepository employeeRepository;
    private final HotelRepository hotelRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(GuestRepository guestRepository, EmployeeRepository employeeRepository, HotelRepository hotelRepository) {
        this.guestRepository = guestRepository;
        this.employeeRepository = employeeRepository;
        this.hotelRepository = hotelRepository;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
        guest.Password = passwordEncoder.encode(request.Password);
        guest.Role = Account.AccountRole.GUEST;
        guestRepository.save(guest);
        return true;
    }

    @PostMapping("/register_manager")
    public void RegisterManager(@RequestParam String login, @RequestParam String password) {
        Employee e = new Employee();
        e.Hotel = hotelRepository.findById(1L).orElseThrow();
        e.EmployeeType = Employee.EmployeeTypeEnum.Manager;
        e.Role = Account.AccountRole.MANAGER;
        e.Login = login;
        e.Password = passwordEncoder.encode(password);
        employeeRepository.save(e);
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
