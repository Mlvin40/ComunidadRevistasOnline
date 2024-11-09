package com.ipc2.revistas.digitales.api.dabase.reportes;

import com.ipc2.revistas.digitales.api.dabase.AdministradorDB;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.validadores.ValidadorComentarioLike;
import java.util.List;

public class RevistasTop {

    private final AdministradorDB administradorDB = new AdministradorDB();
    private final ValidadorComentarioLike validadorComentarioLike = new ValidadorComentarioLike();
    //esta clase debe de obtener una lista de 5 revistas mas populares, esto basado en el numero de likes

    public List<Revista> revistasMasGustadas() {
        List<Revista> revistas = administradorDB.obtenerTodasLasRevistas();
        revistas = validadorComentarioLike.agregarComentariosYLikes(revistas);
        // Ordenar la lista de revistas por número de likes
        revistas = ordenarRevistasPorLikes(revistas);

        // Devolver las 5 primeras revistas o menos si hay menos de 5
        return revistas.size() > 5 ? revistas.subList(0, 5) : revistas;
    }

    private List<Revista> ordenarRevistasPorLikes(List<Revista> revistas) {
        revistas.sort((revista1, revista2) -> revista2.getLikes() - revista1.getLikes());
        return revistas;
    }
    
    public List<Revista> revistasMasComentadas() {
        List<Revista> revistas = administradorDB.obtenerTodasLasRevistas();
        revistas = validadorComentarioLike.agregarComentariosYLikes(revistas);
        // Ordenar la lista de revistas por número de comentarios
        revistas = ordenarRevistasPorComentarios(revistas);

        // Devolver las 5 primeras revistas o menos si hay menos de 5
        return revistas.size() > 5 ? revistas.subList(0, 5) : revistas;
    }

    private List<Revista> ordenarRevistasPorComentarios(List<Revista> revistas) {
        revistas.sort((revista1, revista2) -> revista2.getComentarios().size() - revista1.getComentarios().size());
        return revistas;
    }
}
