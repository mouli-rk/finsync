package in.syncuser.config;

public class RoleContext {
	
	private static final ThreadLocal<String> currentRole = new ThreadLocal<>();

	public static String getCurrentrole() {
		return currentRole.get();
	}
	
	public static void setCurrentRole(String role) {
		currentRole.set(role);
	}
	
	public static void clear() {
		currentRole.remove();
	}
	
}
