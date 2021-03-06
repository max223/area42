package com.rogiss.project.area42.service;


import java.util.HashSet;
import java.util.Set;

import com.rogiss.project.area42.model.UserInfos;
import com.rogiss.project.area42.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserInfos user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("LOGGED_USER"));
        return new User(user.getEmail(), user.getPassword(), grantedAuthorities);

    }

    public Long getIdByEmail(String email) {
        UserInfos userInfos = userRepository.findByEmail(email);
        return userInfos.getId();
    }

    public Long getIdByFacebookId(Long FbId) {
        UserInfos usr = userRepository.findByUserIdFacebook(FbId);
        UserInfos usr2;
        return  usr.getId();
    }

    public UserInfos getUserByFacebookId(Long FbId){
        return  userRepository.findByUserIdFacebook(FbId);
    }


    public UserInfos getUserByTwitterId(Long TwtId){
        return  userRepository.findByUserIdTwitter(TwtId);
    }


    public UserInfos getUserByLinkedin(Long LkId){
        return  userRepository.findByUserIdLinkedin(LkId);
    }

    public UserInfos getUserByDropboxId(String id) {
        return  userRepository.findByUserIdDropbox(id);
    }
}