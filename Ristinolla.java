package ristinolla;
import java.util.Scanner;

public class Ristinolla {

    public static void main(String[] args) {
        Ruudukko ristinolla = new Ruudukko();
        int rivi, sarake;
        ristinolla.tulostaRuudukko(); //tulostetaan ruudukko naytolle kunnes ruudukko on taynna
        do {
        Scanner lukija = new Scanner(System.in);       
            do { //kysytaan pelaajan merkit ruudukolle
                System.out.println("Anna rivi:");
                rivi = (int) Integer.parseInt(lukija.nextLine());
                System.out.println("Anna sarake:");
                sarake = Integer.parseInt(lukija.nextLine());
                    if (!Ruudukko.indeksitOK(rivi, sarake)) //virheellinen arvo ruudukolle
                        System.out.println("Virhe! Yrita uudelleen.");                  
            } while (!ristinolla.setMerkki(rivi, sarake, 'X'));
                if (ristinolla.voitto(rivi, sarake) == true){
                    System.out.println("Voitit pelin!");
                    ristinolla.tulostaRuudukko();
                    return; //lopetetaan peli kayttajan voittoon
                }
            do { //arvotaan koneen merkit ruudukolle
                rivi = (int) (Math.random() * 6);
                sarake = (int) (Math.random() * 6);
                System.out.println("Kone antoi rivin " + rivi + ", ja sarakkeen " + sarake);
            } while (!ristinolla.setMerkki(rivi, sarake, '0'));  
                if (ristinolla.voitto(rivi, sarake) == true){
                    System.out.println("Havisit pelin!");
                    ristinolla.tulostaRuudukko();
                    return; //lopetetaan peli koneen voittoon
                }
        System.out.print("\n");
        ristinolla.tulostaRuudukko(); //tulostetaan ruudukko naytolle kierroksen jalkeen
        } while (!ristinolla.taynna());
            System.out.println("Peli loppui!");
    } //main   
}