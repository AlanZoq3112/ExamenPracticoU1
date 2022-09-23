package Server;

import java.util.Random;

public class Metodos {
    public static String GenerateCURP(String nombre, String apellido1, String apellido2,String sexo, String edo ,String anio, String mes, String dia) {

        char digitoCero = apellido1.charAt(0);
        char primerDigito = apellido1.charAt(1);
        char segundoDigito = apellido2.charAt(0);
        char tercerDigito = nombre.charAt(0);
        char cuartoDigito = anio.charAt(2);
        char quintoDigito = anio.charAt(3);
        char sextoDigito = mes.charAt(0);
        char septimoDigito = mes.charAt(1);
        char octavoDigito = dia.charAt(0);
        char novenoDigito = dia.charAt(1);
        char decimoDigito = sexo.charAt(0);
        char onceavoDigito = edo.charAt(0);
        char doceavoDigito = edo.charAt(1);
        char treceDigito = ' ';
        char catorceDigito = ' ';
        char quinceDigito = ' ';

        char[] ap1 = apellido1.toCharArray();
        for (int i = 1; i < ap1.length; i++) {
            String aux = ap1[i] + "";
            if (!aux.equals("a") && !aux.equals("e") && !aux.equals("i") && !aux.equals("o") && !aux.equals("u")){
                treceDigito = aux.charAt(0);
                break;
            }
        }

        char[] ap2 = apellido2.toCharArray();
        for (int i = 1; i < ap2.length; i++) {
            String aux = ap2[i] + "";
            if (!aux.equals("a") && !aux.equals("e") && !aux.equals("i") && !aux.equals("o") && !aux.equals("u")){
                catorceDigito = aux.charAt(0);
                break;
            }
        }
        char[] nom = nombre.toCharArray();
        for (int i = 1; i < nom.length; i++) {
            String aux = nom[i] + "";
            if (!aux.equals("a") && !aux.equals("e") && !aux.equals("i") && !aux.equals("o") && !aux.equals("u")){
                quinceDigito = aux.charAt(0);
                break;
            }
        }

        Random random = new Random();
        String aleatorio = "";
        String alfa = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String alfa2 = "0123456789";
        aleatorio = alfa.charAt(random.nextInt(alfa.length())) + "" + alfa2.charAt(random.nextInt(alfa2.length()));

        String CURP = ""+ Character.toUpperCase(digitoCero) + Character.toUpperCase(primerDigito) + Character.toUpperCase(segundoDigito) + Character.toUpperCase(tercerDigito) + Character.toUpperCase(cuartoDigito) + Character.toUpperCase(quintoDigito) +
                Character.toUpperCase(sextoDigito) + Character.toUpperCase(septimoDigito) + Character.toUpperCase(octavoDigito)
                + Character.toUpperCase(novenoDigito) + Character.toUpperCase(decimoDigito) + Character.toUpperCase(onceavoDigito)+ Character.toUpperCase(doceavoDigito)
                + Character.toUpperCase(treceDigito) + Character.toUpperCase(catorceDigito) + Character.toUpperCase(quinceDigito) + aleatorio;

        return CURP;
    }

}
