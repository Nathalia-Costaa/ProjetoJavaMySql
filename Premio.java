public class Premio {

    private int idPremio;
    private String tipo;

    public int getIdPremio() {
        return idPremio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void mostraInfo(){

        System.out.println("ID Premio: " + getIdPremio());
        System.out.println("Tipo do Premio: " + getTipo());
        System.out.println("\n------------------");

    }
}
