/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.reportes.RevistasTop;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.util.List;

/**
 *
 * @author melvin
 */
public class ReporteAdministradorService {

    private RevistasTop revistasTop = new RevistasTop();

    public List<Revista> revistasMasComentadas() {
        return revistasTop.revistasMasComentadas();

    }
}
