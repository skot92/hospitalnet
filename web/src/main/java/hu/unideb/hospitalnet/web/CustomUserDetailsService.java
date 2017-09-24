package hu.unideb.hospitalnet.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.unideb.hospitalnet.service.WorkerManager;
import hu.unideb.hospitalnet.vo.RoleVo;
import hu.unideb.hospitalnet.vo.WorkerVo;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private WorkerManager workerManager;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		WorkerVo user;
		try {
			user = workerManager.getWorkerByUsername(username);

			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
			GrantedAuthority authorities = buildUserAuthority(user.getRole());

			return buildUserForAuthentication(user, Arrays.asList(authorities));
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}

	}

	private User buildUserForAuthentication(WorkerVo user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
	}

	private GrantedAuthority buildUserAuthority(RoleVo userRole) {
		return new SimpleGrantedAuthority(userRole.getName());
	}

}