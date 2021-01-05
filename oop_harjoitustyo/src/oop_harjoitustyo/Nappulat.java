package oop_harjoitustyo;


import java.util.*;
import java.util.ArrayList;
/**
 * Luokka Nappulat sisältää luodut nappulat sekä niiden listat.
 * 
 * 
 *
 */

class Nappulat extends Alusta{
	
	static boolean peliKäynnissä;
	
	static Kuningas kuningas1 = new Kuningas(Pelaajat.VALKOINEN, 7, 4, "valkoinenkuningas");
	static Kuningas kuningas2 = new Kuningas (Pelaajat.MUSTA, 0, 4, "mustakuningas");
	static Kuningatar kuningatar1 = new Kuningatar(Pelaajat.VALKOINEN, 7, 3, "valkoinenkuningatar");
	static Kuningatar kuningatar2 = new Kuningatar (Pelaajat.MUSTA, 0, 3, "mustakuningatar");
	static Torni torni1 = new Torni(Pelaajat.VALKOINEN, 7,0, "valkoinentorni1");
	static Torni torni2 = new Torni(Pelaajat.VALKOINEN,7,7, "valkoinentorni2");
	static Torni torni3 = new Torni(Pelaajat.MUSTA,0,0, "mustatorni1");
	static Torni torni4 = new Torni(Pelaajat.MUSTA,0,7, "mustatorni2");
	static Ratsu ratsu1  = new Ratsu(Pelaajat.VALKOINEN,7,1,"valkoinenratsu1");
	static Ratsu ratsu2 = new Ratsu(Pelaajat.VALKOINEN,7,6, "valkoinenratsu2");
	static Ratsu ratsu3 = new Ratsu(Pelaajat.MUSTA,0,1, "mustaratsu1");
	static Ratsu ratsu4 = new Ratsu(Pelaajat.MUSTA,0,6, "mustaratsu2");
	static Lähetti lähetti1 = new Lähetti(Pelaajat.VALKOINEN,7,2, "valkoinenlähetti1");
	static Lähetti lähetti2 = new Lähetti(Pelaajat.VALKOINEN,7,5, "valkoinenlähetti2");
	static Lähetti lähetti3 = new Lähetti(Pelaajat.MUSTA,0,2, "mustalähetti1");
	static Lähetti lähetti4 = new Lähetti(Pelaajat.MUSTA,0,5, "mustalähetti2");
	static Sotilas sotilas1 = new Sotilas(Pelaajat.VALKOINEN,6,0, "valkoinensotilas1");
	static Sotilas sotilas2 = new Sotilas(Pelaajat.VALKOINEN,6,1, "valkoinensotilas2");
	static Sotilas sotilas3 = new Sotilas(Pelaajat.VALKOINEN,6,2, "valkoinensotilas3");
	static Sotilas sotilas4 = new Sotilas(Pelaajat.VALKOINEN,6,3, "valkoinensotilas4");
	static Sotilas sotilas5 = new Sotilas(Pelaajat.VALKOINEN,6,4, "valkoinensotilas5");
	static Sotilas sotilas6 = new Sotilas(Pelaajat.VALKOINEN,6,5, "valkoinensotilas6");
	static Sotilas sotilas7 = new Sotilas(Pelaajat.VALKOINEN,6,6, "valkoinensotilas7");
	static Sotilas sotilas8 = new Sotilas(Pelaajat.VALKOINEN,6,7, "valkoinensotilas8");
	static Sotilas sotilas9 = new Sotilas(Pelaajat.MUSTA,1,0, "mustasotilas1");
	static Sotilas sotilas10 = new Sotilas(Pelaajat.MUSTA,1,1, "mustasotilas2");
	static Sotilas sotilas11 = new Sotilas(Pelaajat.MUSTA,1,2, "mustasotilas3");
	static Sotilas sotilas12 = new Sotilas(Pelaajat.MUSTA,1,3, "mustasotilas4");
	static Sotilas sotilas13 = new Sotilas(Pelaajat.MUSTA,1,4, "mustasotilas5");
	static Sotilas sotilas14 = new Sotilas(Pelaajat.MUSTA,1,5, "mustasotilas6");
	static Sotilas sotilas15 = new Sotilas(Pelaajat.MUSTA,1,6, "mustasotilas7");
	static Sotilas sotilas16 = new Sotilas(Pelaajat.MUSTA,1,7, "mustasotilas8");
	
	
	static ArrayList<Kuningas> kuninkaat = new ArrayList<>();
	static ArrayList<Kuningatar> kurkot = new ArrayList<>();
	static ArrayList<Torni> tornit = new ArrayList<>();
	static ArrayList<Ratsu> ratsut = new ArrayList<> ();
	static ArrayList<Lähetti> lähetit = new ArrayList<>();
	static ArrayList<Sotilas> sotilaat = new ArrayList<>();
	
	static Map<String, Nappulat> valkoiset = new HashMap<>();
	static Map<String, Nappulat> mustat = new HashMap<>();

	
	Nappulat(Nappulat[][] a) {
		super(a);
		peliKäynnissä=true;
	}
	
	
	/**
	 * Metodi lisää nappulat niille kuuluviin listoihin
	 */
	public static void lisääNappulat() {
		Collections.addAll(kuninkaat, kuningas1, kuningas2);
		Collections.addAll(sotilaat, sotilas1,sotilas2,sotilas3,sotilas4,sotilas5,sotilas6,sotilas7,sotilas8,sotilas9,
				sotilas10,sotilas11,sotilas12,sotilas13,sotilas14,sotilas15,sotilas16);
		Collections.addAll(kurkot,kuningatar1, kuningatar2);
		Collections.addAll(tornit, torni1, torni2, torni3, torni4);
		Collections.addAll(ratsut,ratsu1, ratsu2, ratsu3, ratsu4);
		Collections.addAll(lähetit, lähetti1,lähetti2,lähetti3,lähetti4);	
	}
	
	/**
	 * Metodi päivittää alustan sekä tulostaa sen
	 */
	public static void päivitäAlusta(){
		Nappulat [][] a = new Nappulat [8][8];
		Alusta alusta = new Alusta(a);
		for(Kuningas k:kuninkaat) {
			if(k.palautaElossa()) {
			a [k.leveys][k.korkeus] = k;
			k.lisääIdentiteetteihin(k);}}
		for (Kuningatar kr:kurkot) {
			if (kr.palautaElossa()) {
			a[kr.leveys][kr.korkeus] = kr;
			kr.lisääIdentiteetteihin(kr);}}
		for (Torni t:tornit) {
			if(t.palautaElossa()) {
			a[t.leveys][t.korkeus] = t;
			t.lisääIdentiteetteihin(t);}}
		for (Ratsu r:ratsut) {
			if(r.palautaElossa()) {
			a[r.leveys][r.korkeus] = r;
			r.lisääIdentiteetteihin(r);}}
		for (Lähetti l:lähetit) {
			if(l.palautaElossa()) {
			a[l.leveys][l.korkeus] = l;
			l.lisääIdentiteetteihin(l);}}
		for (Sotilas s:sotilaat) {
			if (s.palautaElossa()) {
			a[s.leveys][s.korkeus]=s;
			s.lisääIdentiteetteihin(s);
			}
		}
		tulostaAlusta(a);
	}
	
	
	/**
	 * Metodi saa syötteenä Nappulan, sekä siirtojen määrän leveys/korkeus.
	 * Metodi tarkistaa ensin onko nappula elossa ja päivittää sitten sijainnin.
	 * Sijainnin päivityksen tarkistukset suoritetaan nappulan tyypin luokassa
	 * @param n
	 * @param i
	 * @param j
	 * @throws VirheellinenSiirto
	 * 
	 */
	static void päivitäNappula(Nappulat n, int i, int j) throws VirheellinenSiirto{
		if (n.palautaElossa()) {
		if (n instanceof Sotilas) {
			Sotilas s=(Sotilas)n;
			s.päivitäSotilaanSijainti(i,  j);
			}
		if (n instanceof Kuningas) {
			Kuningas k = (Kuningas)n;
			k.päivitäKuninkaanSijainti(i, j);
		}
		if (n instanceof Lähetti) {
			Lähetti l = (Lähetti)n;
			l.päivitäLähetinSijainti(i, j);
		}
		if (n instanceof Ratsu) {
			Ratsu r = (Ratsu)n;
			r.päivitäRatsunSijainti(i, j);
		}
		if (n instanceof Torni) {
			Torni t = (Torni)n;
			t.päivitäTorninSijainti(i,j);
		}
		if (n instanceof Kuningatar) {
			Kuningatar kr = (Kuningatar)n;
			kr.päivitäKurkonSijainti(i, j);
		}}
	}
	
	/**
	 * Metodi saa syötteenä käyttäjän syöttämän nappulan nimen, josta se tarkistaa löytyykö nappula
	 * listasta ja palauttaa halutun nappulan
	 * @param nappula
	 * @return Nappula n
	 * @throws VirheellinenSiirto
	 * 
	 */
	static Nappulat tarkistaValkoinenNappula(String nappula) throws VirheellinenSiirto{
		if (valkoiset.get(nappula)==null) {
			throw new VirheellinenSiirto("Väärä nappula. Syötä nappula samassa muodossa kuin se on laudalla.");
		} else {
			return valkoiset.get(nappula);
		}
	}
	static Nappulat tarkistaMustaNappula(String nappula) throws VirheellinenSiirto{
		if (mustat.get(nappula)==null) {
			throw new VirheellinenSiirto("Väärä nappula. Syötä nappula samassa muodossa kuin se on laudalla.");
		} else {
			return mustat.get(nappula);
		}
	}
	
	/**
	 * Metodi tarkistaa onko nappula elossa
	 * @param n
	 * @throws VirheellinenSiirto
	 * 
	 */
	
	static void tarkistaElossa (Nappulat n) throws VirheellinenSiirto{
		if (!(n.palautaElossa())) {
			throw new VirheellinenSiirto("Nappulasi ei ole enää elossa. Valitse toinen nappula");
		}
	}
	
	/**
	 * Metodi tarkistaa onko käyttäjän syöttämällä nappulalla mahdollisia siirtoja.
	 * Lähetin, Ratsun sekä Tornin tarkistukset suoritetaan niiden omissa luokissa,
	 * muiden tässä metodissa. Jos nappulalla ei ole mahdollisia siirtoja, heitetään virhe
	 * @param n
	 * @throws VirheellinenSiirto
	 * 
	 */
	
	static void tarkistaSiirrot(Nappulat n) throws VirheellinenSiirto{
		
		if (n instanceof Ratsu) {
			Ratsu r = (Ratsu) n;
			r.tarkistaRatsunSiirrot();
		} else if (n instanceof Torni){
			Torni t = (Torni)n;
			t.tarkistaTorninSiirrot();
		} else if (n instanceof Lähetti) {
			Lähetti l = (Lähetti)n;
			l.tarkistaLähetinSiirrot();
		} else {
		int leveys = n.palautaLeveys();
		int korkeus = n.palautaKorkeus();
		Pelaajat tyyppi = n.palautaTyyppi();
		int virheet=0;
		
		if (leveys+1>7) {virheet++;}  
		else if (n.tarkistaLeveysKorkeus(leveys+1, korkeus)==tyyppi) {
			virheet++;}
		if (leveys+1>7 || korkeus+1>7) {virheet++;}
		else if (n.tarkistaLeveysKorkeus(leveys+1, korkeus+1)==tyyppi) {
			virheet++;}
		if (korkeus+1>7) {virheet++;}
		else if (n.tarkistaLeveysKorkeus(leveys, korkeus+1)==tyyppi) {
			virheet++;}
		if (leveys-1<0 || korkeus+1 >7) {virheet++;}
		else if (n.tarkistaLeveysKorkeus(leveys-1, korkeus+1)==tyyppi) {
			virheet++;}
		if (leveys-1<0) {virheet++;} 
		else if (n.tarkistaLeveysKorkeus(leveys-1, korkeus)==tyyppi) {
			virheet++;}
		if (leveys-1<0 || korkeus-1<0) {virheet++;}
		else if (n.tarkistaLeveysKorkeus(leveys-1, korkeus-1)==tyyppi) {
			virheet++;}
		if (korkeus-1<0) {virheet++;}
		else if(n.tarkistaLeveysKorkeus(leveys, korkeus-1)==tyyppi) {
			virheet++;}
		if (korkeus-1<0 || leveys+1>7) {virheet++;}
		else if (n.tarkistaLeveysKorkeus(leveys+1, korkeus-1)==tyyppi) {
			virheet++;}
		
		if (virheet==8) {
			throw new VirheellinenSiirto("valitsemallasi nappulalla ei ole mahdollisia siirtoja. Valitse toinen nappula");
		}
		}

		}
	
	/**
	 * metodi tarkistaa onko syötetyllä leveydellä ja korkeudella jokin nappula,
	 * ja mitä tyyppiä nappula edustaa
	 * 
	 * @param leveys
	 * @param korkeus
	 * @return Pelaajat.MUSTA || Pelaajat.VALKOINEN || null
	 * 
	 * 
	 */
	
	Pelaajat tarkistaLeveysKorkeus(int leveys, int korkeus){
		
		Pelaajat tyyppi=null;
		
		for (Kuningas k:kuninkaat) {
			if (k.leveys == leveys && k.korkeus==korkeus)
				tyyppi= k.tyyppi;}
		for (Kuningatar k:kurkot) {
			if (k.leveys == leveys && k.korkeus==korkeus)
				tyyppi= k.tyyppi;}
		for (Lähetti k:lähetit) {
			if (k.leveys == leveys && k.korkeus==korkeus)
				tyyppi= k.tyyppi;}
		for (Ratsu k:ratsut) {
			if (k.leveys == leveys && k.korkeus==korkeus)
				tyyppi= k.tyyppi;}
		for(Torni k:tornit) {
			if (k.leveys == leveys && k.korkeus==korkeus)
				tyyppi= k.tyyppi;}
		for(Sotilas k:sotilaat) {
			if (k.leveys == leveys && k.korkeus==korkeus)
				tyyppi= k.tyyppi;}
		return tyyppi;
		}
	
	/**
	 * Metodi syö halutun nappulan
	 * @param n
	 * 
	 */
	
	void syöNappula(Nappulat n) {
		if (n instanceof Sotilas) {
			sotilaat.remove(n);
			n.muutaElossa();}
		if (n instanceof Kuningas) {
			kuninkaat.remove(n);
			n.muutaElossa();
			lopetaPeli();}
		if (n instanceof Lähetti) {
			lähetit.remove(n);
			n.muutaElossa();}
		if (n instanceof Ratsu) {
			ratsut.remove(n);
			n.muutaElossa();}
		if (n instanceof Torni) {
			tornit.remove(n);
			n.muutaElossa();}
		if (n instanceof Kuningatar) {
			kurkot.remove(n);
			n.muutaElossa();}
		}
	
	/**
	 * metodi lopettaa pelin
	 */
	void lopetaPeli() {
		peliKäynnissä=false;
	}
	
	/**
	 * @return true || false
	 */
	static boolean kuningasElossa(){
		return peliKäynnissä;
	}
	
	/**
	 * 
	 * @param l
	 * @param k
	 * @return alusta[l][k]==Nappula || alusta[l][k]==null
	 */
	Nappulat palautaNappula(int l, int k) {
		return alusta[l][k];
	}
	
	int palautaLeveys() {return 0;}
	int palautaKorkeus() {return 0;}
	void muutaElossa() {}
	Pelaajat palautaTyyppi() {return null;}
	boolean palautaElossa() {return true;}
	
}

