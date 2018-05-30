package ristinolla;

public class Ruudukko {
    /*Ristinollan 6*6 -kokoinen peliruudukko ja muutama valine pelisovelluksen
     toteuttamiseen (harjoitustehtava). */

    private char[][] ruudukko;

    public Ruudukko() {
        /*parametriton konstruktori. Alussa kaikki ruudut ovat tyhjia, ts. 
         sisaltavat valilyonnin. Vasemman ylakulman ruutu on rivilla 0 ja 
         sarakkeessa 0, oikean alakulman ruutu rivilla 5 ja sarakkeessa 5.*/
        this.ruudukko = new char[6][6];
        for (int rivi = 0; rivi < 6; rivi++) {
            for (int sarake = 0; sarake < 6; sarake++) {
                this.ruudukko[rivi][sarake] = ' ';
            }
        }
    }

    public boolean setMerkki(int rivi, int sarake, char merkki) {
        /*sijoittaa parametrin merkki sisaltaman merkin ruutuun (rivi, sarake) 
         ja palauttaa arvon true. Kuitenkin, jos rivi ja sarake eivat kelpaa 
         tai mikali kyseinen ruutu ei ole tyhja, metodi ei muuta ruudukkoa vaan
         palauttaa arvon false.*/
        if (indeksitOK(rivi, sarake)) {
            if (this.ruudukko[rivi][sarake] == ' ') {
                this.ruudukko[rivi][sarake] = merkki;
                return true;
            }
        }
        return false;
    }

    public static boolean indeksitOK(int rivi, int sarake) {
        //jos indeksit kelpaavat, palauttaa arvon true.
        return (rivi >= 0 && rivi <= 5 && sarake >= 0 && sarake <= 5);
    }

    public char getMerkki(int rivi, int sarake) {
        /*palauttaa sen merkin, joka on ruudussa (rivi, sarake). Kuitenkin, jos 
         rivi ja sarake eivat kelpaa, palauttaa metodi kysymysmerkin.*/
        if (!indeksitOK(rivi, sarake)) {
            return '?';
        }
        return this.ruudukko[rivi][sarake];
    }

    public void tulostaRuudukko() {
        //tulostaa ruudukon sisallon (aika alkeellisella) merkkigrafiikalla.
        System.out.println(" | 0 | 1 | 2 | 3 | 4 | 5 |\n-+---+---+---+---+---+---+");
        for (int rivi = 0; rivi < 6; rivi++) {
            System.out.print(rivi + "|");
            for (int sarake = 0; sarake < 6; sarake++) {
                System.out.print(" " + this.ruudukko[rivi][sarake] + " |");
            }
            System.out.println("\n-+---+---+---+---+---+---+");
        }
    }

    public boolean taynna() {
        //palauttaa arvon true, jos ruudukko on jo taynna, falsen muuten.
        for (int rivi = 0; rivi < 6; rivi++) {
            for (int sarake = 0; sarake < 6; sarake++) {
                if (this.ruudukko[rivi][sarake] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public int montakoPerakkain(int rivi, int sarake, int suunta) {
        /*palauttaa tiedon siita, kuinka monta kertaa parametrien rivi ja sarake
         maaraaman ruudun sisaltama merkki esiintyy ruudukossa perakkain 
         parametrin suunta ilmaisemassa suunnassa. Perakkain esiintyminen 
         tarkoittaa sita, ettei valissa ole mitaan muita merkkeja. Jos rivi tai
         sarake eivat kelpaa, palauttaa nollan. Suuntaparametri on tassa 
         koodattu seuraavasti:
         - 0: perakkaisten merkkien maara samalla rivilla vasemmalla puolella
         - 1: vastaavasti oikealla puolella
         - 2: perakkaisten merkkien maara samassa sarakkeessa ylapuolella
         - 3: vastaavasti alapuolella
         - 4: perakkaisten merkkien maara vinottain ylos ja vasemmalle katsoen
         - 5: vastaavasti vinottain oikealle alhaalle katsoen
         - 6: perakkaisten merkkien maara vinottain ylos ja oikealle katsoen
         - 7: vastaavasti vinottain vasemmalle alhaalle katsoen */

        if (!indeksitOK(rivi, sarake)) {
            return 0;
        }
        int lkm = 0;
        char merkki = this.ruudukko[rivi][sarake];
        /*miten riveja ja sarakkeita pitaa muuttaa suuntaan suunta edettaessa?
         seur. taulukkoja indeksoidaan suunnalla:        */
        int[] rivimuutos = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] sarakemuutos = {-1, 1, 0, 0, -1, 1, 1, -1};
        for (int i = 0; i < 10; i++) {
            rivi = rivi + rivimuutos[suunta];
            sarake = sarake + sarakemuutos[suunta];
            if (!indeksitOK(rivi, sarake)) {
                break;//paadyttiin ruudukon ulkopuolelle
            }
            if (this.ruudukko[rivi][sarake] == merkki) {
                lkm++;
            } else {//toinen merkki katkaisee sarjan
                break;
            }
        }
        return lkm;
    }

    public boolean voitto(int rivi, int sarake) {
        /*palauttaa arvon true, jos ruudun (rivi, sarake) merkki on osa viiden 
         saman merkin sarjaa pysty-, vaaka- tai vinosuunnissa, falsen muuten.*/
        if (!indeksitOK(rivi, sarake)) {
            return false;
        }
        int suunta = 0;
        /*tutkitaan merkin molemmin puolin. (Vastakkaiset suunnat ovat
         perakkain.)        */
        while (suunta < 7) {
            if (montakoPerakkain(rivi, sarake, suunta) + montakoPerakkain(rivi, sarake, suunta + 1) > 3) {
                return true;
            }
            suunta += 2;
        }
        return false;
    }

    public static void main(String[] args) {
        //pieni esimerkki havainnollistamaan Ruudukon kayttoa:
        Ruudukko ristinolla = new Ruudukko();  //ruudukko-olion luonti
        if (ristinolla.getMerkki(2, 2) == ' ') //jos ruutu (2,2) on tyhja..
            ristinolla.setMerkki(2, 2, 'X');    //..viedaan sinne risti
        System.out.println(ristinolla.setMerkki(1, 1, 'O')); //tul. true
        System.out.println(ristinolla.setMerkki(1, 1, 'X')); //ruutu ei tyhja, tul. false
        System.out.println(ristinolla.getMerkki(1, 1)); //ruutua ei muutettu, tul. O
        System.out.println(ristinolla.setMerkki(-1, -1, 'O')); //indeksit eivat sovi, tul. false
        System.out.println(ristinolla.getMerkki(-1, -1)); //indeksit eivat sovi, tul. ?
        ristinolla.setMerkki(2, 3, 'X');
        ristinolla.setMerkki(2, 4, 'X');
        ristinolla.setMerkki(2, 5, 'X');
        ristinolla.setMerkki(2, 1, 'X');
        ristinolla.tulostaRuudukko();
        System.out.println(ristinolla.taynna()); //tilaa on, tulostuu false
        //voittosuoralla tulostuu true jokaisen sen ruudun kohdalla: 
        System.out.println(ristinolla.voitto(2, 1));
        System.out.println(ristinolla.voitto(2, 3));
        System.out.println(ristinolla.voitto(2, 5));
        //arvotaan tyhja ruutu ja viedaan sinne risti:
        int rivi, sarake;
        do {
            rivi = (int) (Math.random() * 6);
            sarake = (int) (Math.random() * 6);
        } while (!ristinolla.setMerkki(rivi, sarake, 'X'));
        ristinolla.tulostaRuudukko();

    }

}
