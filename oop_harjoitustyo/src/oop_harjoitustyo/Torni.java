package oop_harjoitustyo;

/**
 * Torni-luokka edustaa shakkilaudan torneja ja on Nappulat-luokan aliluokka.
 * Jokaisella tornilla on oma tyyppi(valkoinen/musta),
 * leveys, korkeys, nimi sekä boolean-arvo elossa
 *
 */

public class Torni extends Nappulat {
	
	Pelaajat tyyppi;
	Integer leveys;
	Integer korkeus;
	String nimi;
	boolean elossa;
	
	Torni (Pelaajat pelaaja, int l, int k, String n){
		super(alusta);
		this.tyyppi = pelaaja;
		leveys=l;
		korkeus=k;
		nimi=n;
		elossa=true;
	}
	
	@Override
	int palautaLeveys() {
		return leveys;
	}
	
	@Override
	int palautaKorkeus() {
		return korkeus;
	}
	
	@Override
	Pelaajat palautaTyyppi() {
		return tyyppi;
	}
	
	@Override
	boolean palautaElossa() {
		return elossa;
	}
	
	@Override
	void muutaElossa() {
		elossa=false;
	}
	
	/**
	 * Metodi päivittää Tornin sijainnin käyttäjän syöttämään leveyteen ja korkeuteen.
	 * Metodi tarkistaa ensin onko siirto sallittu, ylittääkä se alustan rajat, onko 
	 * halutussa paikassa jo jokin käyttäjän omista nappuloista, sekä hyppääkö nappula 
	 * jonkin toisen nappulan yli ja heittää mahdollisen virheen. 
	 * Jos nämä ovat ok, metodi tarkistaa syökö torni vastapelaajan
	 * nappulan ja päivittää sijainnin
	 * 
	 * @param l
	 * @param k
	 * @throws VirheellinenSiirto
	 */
	public void päivitäTorninSijainti(int l, int k) throws VirheellinenSiirto{
		
		int uusileveys;
		int uusikorkeus;
		if(this.tyyppi==Pelaajat.VALKOINEN) {
			uusileveys=leveys-l;
			uusikorkeus=korkeus+k;
		} else {
			uusileveys=leveys+l;
			uusikorkeus=korkeus+k;
		}
		
		if (tarkistaSiirrot(l, k) && tarkistaRajat(uusileveys, uusikorkeus)
				&& tarkistaPäällekkäisyys(uusileveys, uusikorkeus) && tarkistaSuorat(l, k, leveys, korkeus)) {
			syököNappulan(uusileveys, uusikorkeus);
			leveys=uusileveys;
			korkeus=uusikorkeus;
		}
	}
	
	/**
	 * Metodi tarkistaa onko siirto sallittu
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaSiirrot(int l, int k) throws VirheellinenSiirto{
		if (l!=0 && k!=0) {
			throw new VirheellinenSiirto("Torni liikkuu ainoastaan vakaan/pystyyn");
		} else {return true;}
	}
	
	/**
	 * Metodi tarkistaa onko siirto alusta sisällä
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaRajat(int l, int k) throws VirheellinenSiirto{
		if (l>7 || l<0 || k>7 || k<0 ) {
			throw new VirheellinenSiirto("Et voi liikuttaa tornia yli laidan");
		}else {return true;}
	}
	
	/**
	 * metodi tarkistaa ettei siirto osu päällekkäin oman nappulan kanssa
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaPäällekkäisyys(int l, int k) throws VirheellinenSiirto{
		if(tarkistaLeveysKorkeus(l, k)==tyyppi) {
			throw new VirheellinenSiirto("Et voi siirtyä ruutuun saman värisen kanssa");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa syökö siirto toisen nappulan
	 * @param l
	 * @param k
	 */
	
	void syököNappulan(int l, int k) {
		if (this.tyyppi==Pelaajat.MUSTA && tarkistaLeveysKorkeus(l, k)==Pelaajat.VALKOINEN) {
			System.out.println("Söit vastapelaajan nappulan " + palautaNappula(l, k).toString());
			syöNappula(palautaNappula(l, k));}
		if (this.tyyppi==Pelaajat.VALKOINEN && tarkistaLeveysKorkeus(l, k)==Pelaajat.MUSTA) {
			System.out.println("söit vastapelaajan nappulan " + palautaNappula(l, k).toString());
			syöNappula(palautaNappula(l, k));} 
	}
	
	
	/**
	 * metodi tarkistaa onko tornilla mahdollisia siirtoja
	 * @throws VirheellinenSiirto
	 */
	
	void tarkistaTorninSiirrot() throws VirheellinenSiirto{
		
		int virheet=0;
		if (tarkistaLeveysKorkeus(leveys+1, korkeus)== this.tyyppi ||
				leveys+1>7) {
			virheet++;}
		if (tarkistaLeveysKorkeus(leveys-1, korkeus) == this.tyyppi ||
				leveys-1<0) {
			virheet++;}
		if (tarkistaLeveysKorkeus(leveys, korkeus+1) == this.tyyppi ||
				korkeus+1>7) {
			virheet++;}
		if (tarkistaLeveysKorkeus(leveys, korkeus-1) == this.tyyppi ||
				korkeus-1<0) {
			virheet++;}
		if (virheet==4) {
			throw new VirheellinenSiirto("Valitsemmallasi nappulalla ei ole mahdollisia siirtoja."
					+ "Valitse toinen nappula");}
		}
	
	/**
	 * metodi lisää tornin hashmappiin, jolla tarkistetaan nappuloiden oikeellisuus
	 * @param k
	 */
	
	void lisääIdentiteetteihin(Torni k){
		if(k.tyyppi==Pelaajat.VALKOINEN)
		valkoiset.put(k.nimi, k);
		else {mustat.put(k.nimi, k);}
	}
	
	public String toString() {
		return nimi;
	}
}
