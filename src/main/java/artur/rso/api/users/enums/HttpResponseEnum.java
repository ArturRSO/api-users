package artur.rso.api.users.enums;

public enum HttpResponseEnum {
	
	GENERIC_ERROR(1, "Um erro ocorreu e a opera��o n�o p�de ser conclu�da.", "500 Internal Server Error"),
	GENERIC_NOT_FOUND(2, "A busca n�o encontrou nenhum registro.", "200 OK"),	
	CREATION_SUCCESS(3, "Cadastrado com sucesso!", "200 OK"),
	UPDATE_SUCCESS(4, " Atualizado com sucesso!", "200 OK"),
	DELETION_SUCCESS(5, "Registro exclu�do com sucesso!", "200 OK"),
	DELETION_SUCCESS_PLURAL(6, "Registros exclu�dos com sucesso", "200 OK"),
	ALREADY_DELETED(7, "Este registro j� se encontra inativo.", "200 OK"),
	ALREADY_DELETED_PLURAL(8, "Estes registros j� se encontram inativos", "200 OK"),
	LOGOUT(9, "Voc� saiu com sucesso.", "200 OK"),
	LOGIN(10, "Voc� entrou no sistema com sucesso!", "200 OK");
	
	private final int id;
	private final String message;
	private final String httpCode;
	
	private HttpResponseEnum(int id, String message, String httpCode) {
		this.id = id;
		this.message = message;
		this.httpCode = httpCode;
	}

	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public String getHttpCode() {
		return httpCode;
	}
	
	public static String getHttpCodeById(int id) {
		for (HttpResponseEnum e : HttpResponseEnum.values()) {
			int test = e.getId();
			if (test == id) {
				return e.getHttpCode();
			}
		}
		
		return null;
	}
	
	public static String getMessageById(int id) {
		for (HttpResponseEnum e : HttpResponseEnum.values()) {
			int test = e.getId();
			if (test == id) {
				return e.getMessage();
			}
		}
		
		return null;
	}
}
