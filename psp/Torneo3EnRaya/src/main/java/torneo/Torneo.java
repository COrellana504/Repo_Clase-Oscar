package torneo;

import java.io.Serializable;
import java.util.*;
import jakarta.servlet.http.HttpSession;

public class Torneo implements Serializable {
    private final Set<String> jugadores = new LinkedHashSet<>();
    private final List<Partida> partidas = new ArrayList<>();
    private Partida partidaActual;
    private int siguienteId = 1;
    private int llamadasFin = 0;


    public static Torneo de(HttpSession session) {
        Torneo t = (Torneo) session.getAttribute("torneo");
        if (t == null) {
            t = new Torneo();
            session.setAttribute("torneo", t);
        }
        return t;
    }

    public Partida nuevaPartida(String j1, char s1, String j2, char s2) {
        jugadores.add(j1);
        jugadores.add(j2);
        int turnoInicial = new Random().nextInt(2);
        partidaActual = new Partida(siguienteId++, j1, s1, j2, s2, turnoInicial);
        partidas.add(partidaActual);
        return partidaActual;
    }

    public int victorias(String nombre) {
        int n = 0;
        for (Partida p : partidas) if (nombre.equals(p.getGanador())) n++;
        return n;
    }


    public List<String> ranking() {
        List<String> r = new ArrayList<>(jugadores);
        r.sort((a, b) -> victorias(b) - victorias(a));
        return r;
    }

    public Map<String, Integer> getVictorias() {
        Map<String, Integer> m = new LinkedHashMap<>();
        for (String j : jugadores) m.put(j, victorias(j));
        return m;
    }

    public Partida buscarPartida(int id) {
        for (Partida p : partidas) if (p.getId() == id) return p;
        return null;
    }

    public void borrarJugador(String nombre) {
        jugadores.remove(nombre);
        partidas.removeIf(p -> p.participa(nombre));
        if (partidaActual != null && partidaActual.participa(nombre)) partidaActual = null;
    }

    public int siguienteLlamadaFin()   { return ++llamadasFin; }
    public void reiniciarLlamadasFin() { llamadasFin = 0; }

    public Set<String> getJugadores()  { return jugadores; }
    public List<Partida> getPartidas() { return partidas; }
    public Partida getPartidaActual()  { return partidaActual; }
}
