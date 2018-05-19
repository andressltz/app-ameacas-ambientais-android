package feevale.br.ameaasambientais;

public enum RequestCode {

    VER_TODOS(1000),
    CADASTRAR(2000),
    ATUALIZAR(2001);

    int code;

    private RequestCode(int code) {
        this.code = code;
    }

}
