/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.reportes;

import com.ipc2.revistas.digitales.api.dabase.AdministradorDB;
import com.ipc2.revistas.digitales.api.dabase.MeGustaDB;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.util.List;

/**
 *
 * @author melvin
 */
public class RevistasMasGustadas {

    private final AdministradorDB administradorDB = new AdministradorDB();

    public List<Revista> revistasConLikes() {
        List<Revista> revistas = administradorDB.obtenerTodasLasRevistas();
        MeGustaDB meGustaDB = new MeGustaDB();
        for (Revista revista : revistas) {
            revista.setLikes(meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre()));
        }
        // Ordenar la lista de revistas por número de likes
        revistas = ordenarRevistasPorLikes(revistas);

        // Devolver las 5 primeras revistas o menos si hay menos de 5
        return revistas.size() > 5 ? revistas.subList(0, 5) : revistas;
    }
    
    private List<Revista> ordenarRevistasPorLikes(List<Revista> revistas) {
        revistas.sort((revista1, revista2) -> revista2.getLikes() - revista1.getLikes());
        return revistas;
    }
}
