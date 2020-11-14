package kz.alim.hotel;

import kz.alim.hotel.data.GuestRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    private final GuestRepository guestRepository;
    private final PasswordEncoder encoder;

    public MyUserDetailsService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
        encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var guest = guestRepository.findByLogin(s);
        if (guest == null)
            throw new UsernameNotFoundException("Guest not found");
        return User
                .builder()
                .username(s)
                .password(encoder.encode(guest.Password))
                .authorities("guest")
                .roles("guest")
                .build();
    }
}
