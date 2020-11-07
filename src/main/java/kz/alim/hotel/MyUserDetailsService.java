package kz.alim.hotel;

import kz.alim.hotel.data.GuestRepository;
import kz.alim.hotel.data.entities.Guest;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    private final GuestRepository guestRepository;

    public MyUserDetailsService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Guest guest = guestRepository.findByLogin(s);
        return User
                .withUsername(guest.Login)
                .password(guest.Password)
                .build();
    }
}
