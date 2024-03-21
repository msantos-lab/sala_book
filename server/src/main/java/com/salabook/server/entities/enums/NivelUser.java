package com.salabook.server.entities.enums;

public enum NivelUser {
    FUNCIONARIO(1),
    ADMIN(2);

    private int code;

    private NivelUser(int code){
        this.code = code;
    } 

    public int getCode(){
        return code;
    }
    

    public static NivelUser valueOf(int code){
        for(NivelUser nivel : NivelUser.values()){
            if(nivel.getCode() == code){
                return nivel;
            }
        }
        throw new IllegalArgumentException("Código Nível User errado.");
    }

}
