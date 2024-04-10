
public class MemoriaFisica {

    private int numMarcos;

    private Marco[] marcos;

    private int marcosDisponibles;
    

    public MemoriaFisica(int numMarcos) {
        this.numMarcos = numMarcos;

        marcos = new Marco[numMarcos];

        for (int i = 0; i < numMarcos; i++) {
            marcos[i] = new Marco(i, Memoria.tamanioPagina);
        }

        marcosDisponibles = numMarcos;

    }


    public int buscarPrimerMarcoDisponible() {

        for (Marco marco : marcos) {
            if (marco.getPaginaAlmacenada() == -1) {
                return marco.getId();
            }
        }

        return -1;

    }


    public int getNumMarcos() {
        return numMarcos;
    }


    public void setNumMarcos(int numMarcos) {
        this.numMarcos = numMarcos;
    }


    public Marco[] getMarcos() {
        return marcos;
    }


    public void setMarcos(Marco[] marcos) {
        this.marcos = marcos;
    }


    public int getMarcosDisponibles() {
        return marcosDisponibles;
    }


    public void setMarcosDisponibles(int marcosDisponibles) {
        this.marcosDisponibles = marcosDisponibles;
    }

}
