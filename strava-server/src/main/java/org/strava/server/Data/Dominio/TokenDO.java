package org.strava.server.Data.Dominio;

import java.util.Objects;

public class TokenDO {
    private String valor;

    public TokenDO(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        TokenDO tokenDO = (TokenDO) obj;
        return Objects.equals(valor, tokenDO.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
