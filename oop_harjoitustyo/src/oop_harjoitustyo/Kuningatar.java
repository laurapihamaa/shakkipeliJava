package oop_harjoitustyo;

/**
 * Kuningatar-luokka edustaa shakkilaudan kurkoja ja on Nappulat-luokan aliluokka.
 * Jokaisella kurkolla on oma tyyppi(valkoinen/musta),
 * leveys, korkeys, nimi sekä boolean-arvo elossa
 *
 */

public class Kuningatar extends Nappulat {
	
	Pelaajat tyyppi;
	Integer leveys;
	Integer korkeus;
	String nimi;
	boolean elossa;
	
	Kuningatar (Pelaajat pelaaja, int l, int k, String n){
		super(alusta);
		this.tyyppi = pelaaja;
		this.leveys=l;
		this.korkeus=k;
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
	
	void muutaElossa() {
		elossa=false;
	}
	
	void lisääIdentiteetteihin(Kuningatar k){
		if(k.tyyppi==Pelaajat.VALKOINEN)
		valkoiset.put(k.nimi, k);
		else {mustat.put(k.nimi, k);}
	}
	
	
	/**
	 * metodi päivittää kurkon sijainnin. Metodi tarkistaa ensin onko siirto sallittu,
	 * ylittääkö se alustan rajat, siirtyykä nappula toisen päälle sekä hyppääkö siirto 
	 * toisen nappulan yli sekä heittää virheen.
	 * Jos em. ovat ok, metodi tarkistaa syökö nappula toisen ja päivittää sijainnin
	 * @param l
	 * @param k
	 * @throws VirheellinenSiirto
	 */
	public void päivitäKurkonSijainti(int l, int k) throws VirheellinenSiirto{
		
		int uusileveys;
		int uusikorkeus;
		if(this.tyyppi==Pelaajat.VALKOINEN) {
			uusileveys=leveys-l;
			uusikorkeus=korkeus+k;
		} else {
			uusileveys=leveys+l;
			uusikorkeus=korkeus+k;}
		
		if (tarkistaAskeleet(l, k) && tarkistaRajat(uusileveys, uusikorkeus)
				&& tarkistaPäällekkäisyys(uusileveys, uusikorkeus) 
				&& tarkistaSuorat(l, k, leveys, korkeus)
				&& tarkistaDiagonaalit(l, k, leveys, korkeus)) {
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
	boolean tarkistaAskeleet(int l, int k) throws VirheellinenSiirto {
		if (l==k || l==0|| k==0) {
		return true;
		}else{throw new VirheellinenSiirto("Voit liikuttaa kuningatarta vain pysty/vaaka/vinoittain");}
	}
	
	/**
	 * metodi tarkistaa onko siirto alustan sisällä
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaRajat (int l, int k) throws VirheellinenSiirto{
		if (l>7 || l<0 || k>7 || k<0 ) {
			throw new VirheellinenSiirto("Et voi liikuttaa kuningatarta yli laidan");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa siirtyykö nappula toisen päälle
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaPäällekkäisyys(int l, int k) throws VirheellinenSiirto{
		if(tarkistaLeveysKorkeus(l, k)==tyyppi) {
			throw new VirheellinenSiirto("Et voi siirtyä ruutuun saman värisen kanssa");}
		else {return true;}
	}
	
	/**
	 * metodi tarkistaa syökö nappula toisen
	 * @param l
	 * @param k
	 */
	
	void syököNappulan(int l, int k) {
		if (this.tyyppi==Pelaajat.MUSTA && tarkistaLeveysKorkeus(l, k)==Pelaajat.VALKOINEN) {
			System.out.println("Söit vastapelaajan nappulan " + palautaNappula(l, k).toString());
			syöNappula(palautaNappula(l, k));
		}
		else if (this.tyyppi==Pelaajat.VALKOINEN && tarkistaLeveysKorkeus(l, k)==Pelaajat.MUSTA) {
			System.out.println("söit vastapelaajan nappulan " + palautaNappula(l, k).toString());
			syöNappula(palautaNappula(l, k));
		}
	}
	
	@Override
	public String toString() {
		return nimi;
	}

}
