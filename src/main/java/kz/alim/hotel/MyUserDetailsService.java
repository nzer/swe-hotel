package kz.alim.hotel;

import kz.alim.hotel.data.AccountRepository;
import kz.alim.hotel.data.entities.Account;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    public static final String ROLE_GUEST = "GUEST";
    private final AccountRepository accountRepository;

    public MyUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(s);
        if (account == null)
            throw new UsernameNotFoundException("Guest not found");
        User.UserBuilder userDetails = User.builder()
                .username(s)
                .password(account.Password);
        if (account.Role == Account.AccountRole.GUEST) {
            userDetails = userDetails
                    .authorities(ROLE_GUEST)
                    .roles(ROLE_GUEST);
        }
        return userDetails.build();
    }
}
