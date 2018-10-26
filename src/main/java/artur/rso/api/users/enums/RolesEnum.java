package artur.rso.api.users.enums;

public enum RolesEnum {
	
	ADMIN(1, "admin", "All"),
	EMPLOYEE(2, "employee", "Own information, customer information, designed cockpit"),
	CUSTOMER(3, "customer", "Own information, designed cockpit");
	
	private final int id;
	private final String name;
	private final String permissions;
	
	private RolesEnum(int id, String name, String permissions) {
		this.id = id;
		this.name = name;
		this.permissions = permissions;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPermissions() {
		return permissions;
	}
	
	public static String getNameById(int id) {
		for (RolesEnum r : RolesEnum.values()) {
			int test = r.getId();
			if (test == id) {
				return r.getName();
			}
		}
		
		return null;
	}
	
	public static String getPermissionsById(int id) {
		for (RolesEnum r : RolesEnum.values()) {
			int test = r.getId();
			if (test == id) {
				return r.getPermissions();
			}
		}
		
		return null;
	}
}
