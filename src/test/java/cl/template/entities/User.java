package cl.template.entities;

public class User {

	private String rut;
	private String password;

	public String getRut() {
		return rut;
	}

	public void setUsername(String newRut) {
		rut = newRut;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		password = newPassword;
	}
}