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
    public static final String ROLE_CLERK = "CLERK";
    public static final String ROLE_MANAGER = "MANAGER";
    private final AccountRepository accountRepository;

    public MyUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(s);
        if (account == null)
            throw new UsernameNotFoundException("Account not found");
        User.UserBuilder userDetails = User.builder()
                .username(s)
                .password(account.Password);
        if (account.Role == Account.AccountRole.GUEST) {
            userDetails = userDetails
                    .authorities(ROLE_GUEST)
                    .roles(ROLE_GUEST);
        }
        if (account.Role == Account.AccountRole.CLERK) {
            userDetails = userDetails
                    .authorities(ROLE_CLERK)
                    .roles(ROLE_CLERK);
        }
        if (account.Role == Account.AccountRole.MANAGER) {
            userDetails = userDetails
                    .authorities(ROLE_MANAGER)
                    .roles(ROLE_MANAGER);
        }
        return userDetails.build();
    }
}
