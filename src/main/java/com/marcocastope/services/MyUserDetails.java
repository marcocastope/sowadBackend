package com.marcocastope.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.marcocastope.model.User;

public class MyUserDetails implements UserDetails {

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> grantedAuthorities;

	public static MyUserDetails fromUserEntityToMyUserDetails(User user) {
		MyUserDetails my = new MyUserDetails();
		my.username = user.getUsername();
		my.password = user.getPassword();
		my.grantedAuthorities = java.util.Collections
				.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
		return my;
	}				

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
