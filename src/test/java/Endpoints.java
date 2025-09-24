package endpoits;

public class Endpoints {
    // Usuários
    public static final String TODOS_USUARIO = "api/users?page=2";
    public static final String USUARIO_ID = "/api/users/2";
    public static final String USUARIO_INEXISTENTE = "/api/users/23";
    // Recursos
    public static final String RECURSOS = "/api/unknown";
    public static final String RECURSO_ID = "/api/unknown/2";
    // OUTROS
    public static final String HEALTH = "/health";
    // UPDATE-DELETE
    public static final String ATUALIZAR_USUARIO = "/api/users/2";
    public static final String DELETA_USUARIO = "/api/users/2";
    // REGISTRO
    public static final String REGISTRANDO_USUARIO = "/api/user";
    public static final String REGISTRO_INVALIDO = "/api/register";
    public static final String REGISTRO_SUCESSO = "/api/register";
    // LOGIN
    public static final String LOGIN = "/api/login";
}