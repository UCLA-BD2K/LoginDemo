package demo;

import demo.database.userManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DanielY on 6/25/2015.
 */
@Repository
public class AuthUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        String userPassword = null;

        try {
            userPassword = userManager.getUserPassword(username);
        } catch (Exception e) {
            //Error getting user's password
        }

        //getAuthorities(1) is hardcoded for now. Should be changed if Roles becomes important in the future
        UserDetails userdetails = new User(username, userPassword, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(1));

        return userdetails;
    }

    public List<GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if(role.intValue() == 1)
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }
}
