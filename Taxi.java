import java.util.ArrayList;
import java.util.Scanner;

public class Taxi
{
    Scanner scan=new Scanner(System.in);
    private String placa;
    private String nameDriver;
    private String mensajeConductor=" ";
    ArrayList<Denuncia>denunciasTaxi=new ArrayList<>();
    public Taxi(String p, String n){
        this.placa=p;
        this.nameDriver=n;
    }
    
    public String getPlaca(){
        return this.placa;
    }
    
    public String getDriver(){
        return this.nameDriver;
    }
    
    public void enviarMensaje(String m){
        this.mensajeConductor=m;
    }
    
    public String getMensaje(){
        return this.mensajeConductor;
    }
    
    public void addDenun(Denuncia d){
        denunciasTaxi.add(d);
    }
    
    public int numDenun(){
       return denunciasTaxi.size();
    }
    
    public ArrayList<Denuncia> getDenunciasTaxi(){
        return denunciasTaxi;
    }

    public static Taxi crearTaxi(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Escriba la placa del taxi");
        String pla=scan.next();
        String tax=capturaNameDriver();
        Taxi nuevo=new Taxi(pla, tax);
        return nuevo;
    }
    
    public static String capturaNameDriver(){
        Scanner sn=new Scanner(System.in);
        System.out.println("Escriba nombre de taxista");
        String taxista=sn.nextLine();
        return taxista;
    }
    
    public static void loginTaxista(ArrayList<Propietario>flotasPlataforma){
        Scanner scan=new Scanner(System.in);
        System.out.println("Escriba la placa del taxi");
        String pla=scan.next();
        int[] hallar=new int[2];
        hallar=Procesamiento.buscarEnPlataforma(pla, flotasPlataforma);
        int prop=hallar[0];
        int tax=hallar[1];
        if(prop<0){
                System.out.println("Intente de nuevo");
                loginTaxista(flotasPlataforma);
        }
        else{
            inTaxista(flotasPlataforma, prop, tax);
        }
    }
    
    public static void inTaxista(ArrayList<Propietario>flotasPlataforma,int prop,int tax){
        Scanner scan=new Scanner(System.in);
        Propietario esteProp=flotasPlataforma.get(prop);
        Taxi esteTax=flotasPlataforma.get(prop).getTaxi(tax);
        System.out.println("Bienvenid@ señor(a) "+esteTax.getDriver()+" a Denuncia Tax");
        System.out.println("Que desea hacer?");
        System.out.println("1. Ver denuncias recibidas");
        System.out.println("2. Ver mensaje de "+esteProp.getPropietario());
        //System.out.println("3. Enviar mensaje a "+esteProp.getPropietario());
        System.out.println("3. Regresar a menu de Usuarios");
        int a=scan.nextInt();
        switch(a){
            case 1:{
                Salidas.printDenunForTax(flotasPlataforma, prop, tax);
                inTaxista(flotasPlataforma, prop, tax);
                break;
            }
            case 2:{
                Salidas.printMensaje(flotasPlataforma, prop, tax);
                inTaxista(flotasPlataforma, prop, tax);
                break;
            }
            default:{
                Principal.main();
                break;
            }
        }
    }
}
