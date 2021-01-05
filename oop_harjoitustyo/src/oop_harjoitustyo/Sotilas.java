package oop_harjoitustyo;

/**
 * Sotilas-luokka edustaa shakkilaudan sotilaita ja on Nappulat-luokan aliluokka.
 * Jokaisella sotilaalla on oma tyyppi(valkoinen/musta),
 * leveys, korkeys, nimi, siirtojen lukumäärä sekä boolean-arvo elossa
 *
 */

public class Sotilas extends Nappulat {
	
	Pelaajat tyyppi;
	Integer leveys;
	Integer korkeus;
	String nimi;
	int siirto;
	boolean elossa;
	
	Sotilas (Pelaajat pelaaja, int l, int k, String n){
		super(alusta);
		this.tyyppi = pelaaja;
		leveys=l;
		korkeus=k;
		nimi=n;
		siirto=0;
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
	
	/**
	 * Metodi tarkistaa siirron oikeellisuuden, ylittääkö siirto alusta rajat, siirtyykö
	 * nappula toisen päälle sekä heittää virheen. Jos siirto ok, tarkistetaan syökö nappula
	 * toisen ja päivitetään sijainti 
	 * @param l
	 * @param k
	 * @throws VirheellinenSiirto
	 */

	public void päivitäSotilaanSijainti(int l, int k) throws VirheellinenSiirto{
		
		if (elossa=true) {
		int uusileveys;
		int uusikorkeus;
		if(this.tyyppi==Pelaajat.VALKOINEN) {
			uusileveys=leveys-l;
			uusikorkeus=korkeus+k;
		} else {
			uusileveys=leveys+l;
			uusikorkeus=korkeus+k;
		}
		
		if (tarkistaAskeleet(l, k) && tarkistaYliLaidan(uusileveys, uusikorkeus) 
				&& tarkistaPäällekkäisyys(uusileveys, uusikorkeus)) {
			syököNappulan(uusileveys, uusikorkeus);
			leveys=uusileveys;
			korkeus=uusikorkeus;
			siirto++;
		}
		}
	}
	
	/**
	 * metodi tarkistaa siirron oikeellisuuden
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaAskeleet(int l, int k) throws VirheellinenSiirto{
		if (siirto==0 && l<3 && l>-3 && k<3 && k>-3) {
			return true;}
		if (siirto>0 && l<2 && l>-2 && k<2 && k>-2) {
			return true;}
		else {
			throw new VirheellinenSiirto("Virheellinen siirto: sotilaan sallittu liikkuminen ensimmäisellä"
			+ " siirrolla on kaksi askelta, sen jälkeen yksi askel.");}
	}
	
	/**
	 * metodi tarkistaa liikkuuko siirto yli alustan rajojen
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaYliLaidan (int l, int k) throws VirheellinenSiirto{
		if (l<0 || l>7 || k<0 || k>7) {
			throw new VirheellinenSiirto("Virheellinen siirto: et voi liikuttaa nappulaa yli rajojen");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa ettei nappula siirry toisen nappulan päälle
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaPäällekkäisyys (int l, int k) throws VirheellinenSiirto{
		if (tarkistaLeveysKorkeus(l, k)==tyyppi) {
			throw new VirheellinenSiirto("et voi siirtyä rutuun samanvärisen kanssa");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa syökö nappula toisen nappulan
	 * @param l
	 * @param k
	 */
	void syököNappulan (int l, int k) {
		if (this.tyyppi==Pelaajat.MUSTA && tarkistaLeveysKorkeus(l, k)==Pelaajat.VALKOINEN) {
			System.out.println("Söit vastapelaajan nappulan " + palautaNappula(l, k).toString());
			syöNappula(palautaNappula(l, k));
		}
		if (this.tyyppi==Pelaajat.VALKOINEN && tarkistaLeveysKorkeus(l, k)==Pelaajat.MUSTA) {
			System.out.println("söit vastapelaajan nappulan " + palautaNappula(l, k).toString());
			syöNappula(palautaNappula(l, k));
		}
	}
	
	
	void lisääIdentiteetteihin(Sotilas k){
		if(k.tyyppi==Pelaajat.VALKOINEN)
		valkoiset.put(k.nimi, k);
		else {mustat.put(k.nimi, k);}
	}
	
	@Override
	public String toString() {
		return nimi;
	}
	
}
