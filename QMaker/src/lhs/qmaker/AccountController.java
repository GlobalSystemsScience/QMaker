package lhs.qmaker;

public class AccountController {
	private static int ACCOUNT_ID;
	private static String EMAIL;
	
	public static void signInView() {
		//TODO
	}
	public static void signIn(String email, String password) {
		//TODO
	}
	public static void signUpView() {
		//TODO
	}
	public static void signUp(String email, String password1, String password2) {
		//TODO
		if (!password1.equals(password2)) {
			//TODO notify user that passwords do not match
			return;
		}
	}
	public static void accountView() {
		//TODO
	}
}
