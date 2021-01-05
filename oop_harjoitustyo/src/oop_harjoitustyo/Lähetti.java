package oop_harjoitustyo;

/**
 * Lähetti-luokka edustaa shakkilaudan lähettejä ja on Nappulat-luokan aliluokka.
 * Jokaisella lähetillä on oma tyyppi(valkoinen/musta),
 * leveys, korkeys, nimi sekä boolean-arvo elossa
 *
 */
public class Lähetti extends Nappulat {
	
	Pelaajat tyyppi;
	Integer leveys;
	Integer korkeus;
	String nimi;
	boolean elossa;
	
	Lähetti (Pelaajat pelaaja, int l, int k, String n){
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
	 * metodi päivittää lähetin sijainnin. Metodi tarkistaa ensin onko annettu siirto sääntöjen
	 * mukainen, ylittääkä alustan rajat, siirtyykö oman nappulan päälle ja hyppääkö toisen nappulan
	 * yli sekä heittää virheen. Jos nämä ok, tarkistetaan syökö nappula toisen ja päivitetään sijainti
	 * @param l
	 * @param k
	 * @throws VirheellinenSiirto
	 */
	
	public void päivitäLähetinSijainti(int l, int k) throws VirheellinenSiirto{
		
		int uusileveys;
		int uusikorkeus;
		
		if(this.tyyppi==Pelaajat.VALKOINEN) {
			uusileveys=leveys-l;
			uusikorkeus=korkeus+k;
		} else {
			uusileveys=leveys+l;
			uusikorkeus=korkeus+k;}
		
		 if(tarkistaAskeleet(l, k) && tarkistaRajat(uusileveys, uusikorkeus) && 
				 tarkistaPäällekkäisyys(uusileveys, uusikorkeus) && tarkistaDiagonaalit(l,k, leveys, korkeus)) {
			syököNappulan(uusileveys, uusikorkeus);
			leveys=uusileveys;
			korkeus=uusikorkeus;
		}
	}
	
	/**
	 * metodi tarkistaa askeleiden oikeellisuuden
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaAskeleet(int l, int k) throws VirheellinenSiirto{
		if(l!=k && -l!=k) {
			throw new VirheellinenSiirto("Lähetti liikkuu ainoastaan diagonaaleja pitkin");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa ylittääkö askeleet alustan rajat
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	
	boolean tarkistaRajat (int l, int k) throws VirheellinenSiirto{
		if (l>7 || l<0 || k>7 || k<0 ) {
			throw new VirheellinenSiirto("Et voi liikuttaa lähettiä yli laidan");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa onko nappula siirtymässä toisen nappulan päälle
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaPäällekkäisyys (int l, int k) throws VirheellinenSiirto{
		if(tarkistaLeveysKorkeus(l, k)==this.tyyppi) {
			throw new VirheellinenSiirto("Et voi siirtyä ruutuun saman värisen kanssa");}
		else {return true;}
	}
	
	/**
	 * metodi tarkistaa syökö nappula toisen
	 * @param l
	 * @param k
	 */
	void syököNappulan (int l, int k) {
		if (this.tyyppi==Pelaajat.MUSTA && tarkistaLeveysKorkeus(l, k)==Pelaajat.VALKOINEN) {
			System.out.println("Söit vastapelaajan nappulan " + palautaNappula(l, k).toString());
			syöNappula(palautaNappula(l, k));
		}
		else if (this.tyyppi==Pelaajat.VALKOINEN && tarkistaLeveysKorkeus(l, k)==Pelaajat.MUSTA) {
			System.out.println("söit vastapelaajan nappulan " + palautaNappula(l, k).toString());
			syöNappula(palautaNappula(l, k));
		} 
	}
	
	/**
	 * metodi tarkistaa onko lähetillä mahdollisia siirtoja
	 * @throws VirheellinenSiirto
	 */
	void tarkistaLähetinSiirrot() throws VirheellinenSiirto{
			
			int virheet=0;
			if (tarkistaLeveysKorkeus(leveys+1, korkeus+1)== this.tyyppi ||
					leveys+1>7 || korkeus+1>7) {
				virheet++;}
			if (tarkistaLeveysKorkeus(leveys-1, korkeus+1) == this.tyyppi ||
					leveys-1<0 || korkeus+1>7) {
				virheet++;}
			if (tarkistaLeveysKorkeus(leveys-1, korkeus-1) == this.tyyppi ||
					korkeus-1<0 || leveys-1<0) {
				virheet++;}
			if (tarkistaLeveysKorkeus(leveys+1, korkeus-1) == this.tyyppi ||
					korkeus-1<0 || leveys+1>7) {
				virheet++;}
			if (virheet==4) {
				throw new VirheellinenSiirto("Valitsemmallasi nappulalla ei ole mahdollisia siirtoja."
						+ "Valitse toinen nappula");}
			}

	
	public String toString() {
		return nimi;
	}
	
	void lisääIdentiteetteihin(Lähetti k){
		if(k.tyyppi==Pelaajat.VALKOINEN)
		valkoiset.put(k.nimi, k);
		else {mustat.put(k.nimi, k);}
	}
	

}
