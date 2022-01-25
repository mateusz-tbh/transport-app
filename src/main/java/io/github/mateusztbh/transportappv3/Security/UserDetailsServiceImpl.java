package io.github.mateusztbh.transportappv3.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Nie ma takiego użytkownika");
        }
        return new MyUserDetails(user);
    }
}
