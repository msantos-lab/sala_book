package com.salabook.server.entities.enums;

public enum StatusSala {
    DISPONIVEL(1),
    OCUPADA(2),
    RESERVADA(3);
    
    private int code;

    private StatusSala(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static StatusSala valueOf(int code){
        for(StatusSala status : StatusSala.values())
            if(status.getCode() == code){
                return status;
            } 
        throw new IllegalArgumentException("Código Status Sala errado.");    
    }

}
