package br.com.gabriel.ecommerce.security;

public class ECtoken {
    private String token;

    public ECtoken(){

    }

    public ECtoken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
