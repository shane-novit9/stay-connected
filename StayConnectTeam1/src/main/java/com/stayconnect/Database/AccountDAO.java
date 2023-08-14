package com.stayconnect.Database;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stayconnect.model.UserAccount;

@Repository
public interface AccountDAO {
	public UserAccount addAccount(UserAccount account);
	public UserAccount getAccountByEmail(String email);
	public UserAccount deleteAccount(UserAccount account);
	public int updateActive(String email, boolean active);
	public List<String> getAuthorities();
}
