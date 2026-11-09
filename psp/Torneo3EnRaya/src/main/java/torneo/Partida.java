package torneo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Partida implements Serializable {
    private final int id;
    private final String[] jugadores = new String[2];
    private final char[] simbolos = new char[2];
    private final char[][] tablero = new char[3][3];
    private final List<String> movimientos = new ArrayList<>();
    private int turno;            // 0 o 1: índice del jugador al que le toca
    private String ganador;       // null si empate o en curso
    private boolean terminada;

    public Partida(int id, String j1, char s1, String j2, char s2, int turnoInicial) {
        this.id = id;
        jugadores[0] = j1; jugadores[1] = j2;
        simbolos[0] = s1;  simbolos[1] = s2;
        this.turno = turnoInicial;
        for (char[] fila : tablero) Arrays.fill(fila, ' ');
    }

    /** Devuelve false si el movimiento no es válido. */
    public boolean mover(int fila, int col) {
        if (terminada || fila < 0 || fila > 2 || col < 0 || col > 2) return false;
        if (tablero[fila][col] != ' ') return false;

        tablero[fila][col] = simbolos[turno];
        movimientos.add(jugadores[turno] + " (" + simbolos[turno] + ") -> fila "
                + fila + ", columna " + col);

        if (hayGanador(simbolos[turno])) {
            ganador = jugadores[turno];
            terminada = true;
        } else if (movimientos.size() == 9) {
            terminada = true;     // empate
        } else {
            turno = 1 - turno;
        }
        return true;
    }

    private boolean hayGanador(char s) {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == s && tablero[i][1] == s && tablero[i][2] == s) return true;
            if (tablero[0][i] == s && tablero[1][i] == s && tablero[2][i] == s) return true;
        }
        return (tablero[0][0] == s && tablero[1][1] == s && tablero[2][2] == s)
                || (tablero[0][2] == s && tablero[1][1] == s && tablero[2][0] == s);
    }

    public boolean participa(String nombre) {
        return jugadores[0].equals(nombre) || jugadores[1].equals(nombre);
    }

    public String rival(String nombre) {
        return jugadores[0].equals(nombre) ? jugadores[1] : jugadores[0];
    }

    public char simboloDe(String nombre) {
        return jugadores[0].equals(nombre) ? simbolos[0] : simbolos[1];
    }

    /** Tablero como texto para la plantilla: las casillas vacías son "". */
    public String[][] getTableroTexto() {
        String[][] r = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                r[i][j] = tablero[i][j] == ' ' ? "" : String.valueOf(tablero[i][j]);
        return r;
    }

    public String getTurnoActual()       { return jugadores[turno]; }
    public String getPerdedor()          { return ganador == null ? null : rival(ganador); }
    public int getNumMovimientos()       { return movimientos.size(); }
    public int getId()                   { return id; }
    public String getGanador()           { return ganador; }
    public boolean isTerminada()         { return terminada; }
    public List<String> getMovimientos() { return movimientos; }
    public String[] getJugadores()       { return jugadores; }
}