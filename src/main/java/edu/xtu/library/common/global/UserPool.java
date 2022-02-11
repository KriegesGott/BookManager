package edu.xtu.library.common.global;

import edu.xtu.library.entity.User;

public class UserPool {

	private static final ThreadLocal<User> local = new ThreadLocal<>();

	public static void put(User s) {
		local.set(s);
	}

	public static User get() {
		return local.get();
	}

	public static void remove() {
		User user = local.get();
		if (user != null) {
			local.remove();
		}
	}
}
