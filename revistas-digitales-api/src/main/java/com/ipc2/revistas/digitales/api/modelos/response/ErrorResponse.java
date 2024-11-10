/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.modelos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author melvin
 */
public class ErrorResponse {

    // Atributo que contiene el mensaje de exito en formato JSON
    @JsonProperty("nombre")
    private String message;

    public ErrorResponse() {
        this.message = "Hubo un error al realizar la operacion";
    }
}
