import java.util.ArrayList;
import java.util.Scanner;

public class Propietario
{
    String nameProp;
    ArrayList<Taxi>taxisFlota=new ArrayList<>();
 
    
    public Propietario(String n){
        this.nameProp=n;
    }
    
    public String getPropietario(){
        return this.nameProp;
    }
    
    public int buscarTaxi(String s){
        for(int i=0;i<taxisFlota.size();i++){
            if(taxisFlota.get(i).getPlaca().equalsIgnoreCase(s)){
                System.out.println("coincide");
                return i;
            }
        }
        return -1;
    }
    
    public int sizeFlota(){
        return taxisFlota.size();
    }
    
    public Taxi getTaxi(int i){
        return taxisFlota.get(i);
    }
    
    public void addTaxi(Taxi t){
        taxisFlota.add(t);
    }
    
    public int numDenunFlota(){
        int cont=0;
        for(int i=0;i<taxisFlota.size();i++){
            cont=cont+taxisFlota.get(i).numDenun();
        }
        return cont;
    }
    
    
    public static ArrayList<Propietario> loginPropietario(ArrayList<Propietario>flotasPlataforma){
        System.out.println("Escriba el nombre de su flota");
        Scanner scan=new Scanner(System.in);
        String nom=scan.nextLine();
        if(Procesamiento.posEnFlota(nom, flotasPlataforma)>=0){
            int posc=Procesamiento.posEnFlota(nom, flotasPlataforma);
            Propietario estePropietario=flotasPlataforma.get(posc);
            
            System.out.println("Bienvenido "+estePropietario.getPropietario()+" a DenunciaTax");
            int elige=Entradas.opcionPropietario();
            
            if(elige==1){
                Taxi nuevo=Taxi.crearTaxi();
                flotasPlataforma.get(posc).addTaxi(nuevo); 
                System.out.println("Taxi añadido");
                Persistencia.registroVehiculo(estePropietario.getPropietario(), nuevo.getPlaca(), nuevo.getDriver());
                logeadoPropietario(flotasPlataforma,nom);
                return flotasPlataforma;
                
            }
            else if(elige==2){
                System.out.println("Digite la placa del taxi:");
                String p=scan.next();
                int posPlaca=flotasPlataforma.get(posc).buscarTaxi(p);
                if(posPlaca>=0){
                    Salidas.printDenunTaxi(flotasPlataforma,posc, posPlaca);
                }
                else{
                    System.out.println("Vehiculo no registrado");
                }
                logeadoPropietario(flotasPlataforma,nom);
            }
            else if(elige==3){
                Salidas.printDenunFlota(flotasPlataforma, posc);
                logeadoPropietario(flotasPlataforma,nom);
            }
            else if(elige==4){
                System.out.println("Escriba la placa del taxi:");
                String pla=scan.next();
                int halla=flotasPlataforma.get(posc).buscarTaxi(pla);
                if(halla<0){
                    System.out.println("Taxi no esta registrado en su flota");
                }
                else{
                    String men=Entradas.capturaMensaje();
                    flotasPlataforma.get(posc).getTaxi(halla).enviarMensaje(men);
                    Persistencia.mensaje(men, pla, estePropietario.getPropietario());
                    System.out.println("Mensaje enviado con éxito");
                }
                logeadoPropietario(flotasPlataforma,nom);
            }
            else if(elige==5){
               Principal.main();
            }
            else{
                System.out.println("Opcion no existe, vuelve a intentarlo");
                logeadoPropietario(flotasPlataforma,nom);
            }
        }
        else{
            System.out.println("No se encuentra, registraremos su flota");
            Propietario nuevo=new Propietario(nom);
            flotasPlataforma.add(nuevo);
            Persistencia.registroFlota(nom);
            System.out.println("Listo!!, quedó registrada como "+nom);
            logeadoPropietario(flotasPlataforma,nom);
        }
        
        return flotasPlataforma;
        
    }
    public static ArrayList<Propietario> logeadoPropietario(ArrayList<Propietario>flotasPlataforma, String nom){
        Scanner scan= new Scanner(System.in);
        if(Procesamiento.posEnFlota(nom, flotasPlataforma)>=0){
            int posc=Procesamiento.posEnFlota(nom, flotasPlataforma);
            Propietario estePropietario=flotasPlataforma.get(posc);
            
            System.out.println("Bienvenido "+estePropietario.getPropietario()+" a DenunciaTax");
            int elige=Entradas.opcionPropietario();
            
            if(elige==1){
                Taxi nuevo=Taxi.crearTaxi();
                flotasPlataforma.get(posc).addTaxi(nuevo); 
                System.out.println("Taxi añadido");
                Persistencia.registroVehiculo(estePropietario.getPropietario(), nuevo.getPlaca(), nuevo.getDriver());
                logeadoPropietario(flotasPlataforma,nom);
                return flotasPlataforma;
                
            }
            else if(elige==2){
                System.out.println("Digite la placa del taxi:");
                String p=scan.next();
                int posPlaca=flotasPlataforma.get(posc).buscarTaxi(p);
                if(posPlaca>=0){
                    Salidas.printDenunTaxi(flotasPlataforma,posc, posPlaca);
                }
                else{
                    System.out.println("Vehiculo no registrado");
                }
                logeadoPropietario(flotasPlataforma,nom);
            }
            else if(elige==3){
                Salidas.printDenunFlota(flotasPlataforma, posc);
                logeadoPropietario(flotasPlataforma,nom);
            }
            else if(elige==4){
                System.out.println("Escriba la placa del taxi:");
                String pla=scan.next();
                int halla=flotasPlataforma.get(posc).buscarTaxi(pla);
                if(halla<0){
                    System.out.println("Taxi no esta registrado en su flota");
                }
                else{
                    String men=Entradas.capturaMensaje();
                    flotasPlataforma.get(posc).getTaxi(halla).enviarMensaje(men);
                    Persistencia.mensaje(men, pla, estePropietario.getPropietario());
                    System.out.println("Mensaje enviado con éxito");
                }
                logeadoPropietario(flotasPlataforma,nom);
            }
            else if(elige==5){
               Principal.main();
            }
            else{
                System.out.println("Opcion no existe, vuelve a intentarlo");
                logeadoPropietario(flotasPlataforma,nom);
            }
        }
        else{
            System.out.println("No se encuentra, registraremos su flota");
            System.out.println("Digite nombre de flota:");    
            String flot=scan.nextLine();
            Propietario nuevo=new Propietario(flot);
            flotasPlataforma.add(nuevo);
            Persistencia.registroFlota(flot);
            System.out.println("De nuevo:");
            Principal.main();
        }
        
        return flotasPlataforma;
        
    }
    
    
 
    
}
