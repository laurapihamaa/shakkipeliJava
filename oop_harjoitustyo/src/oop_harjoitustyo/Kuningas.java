package oop_harjoitustyo;

/**
 * Kuningas-luokka edustaa shakkilaudan kuninkaita ja on Nappulat-luokan aliluokka.
 * Jokaisella kuninkaalla on oma tyyppi(valkoinen/musta),
 * leveys, korkeys, nimi sekä boolean-arvo elossa
 *
 */

class Kuningas extends Nappulat {
	
	Pelaajat tyyppi;
	Integer leveys;
	Integer korkeus;
	String nimi;
	boolean elossa;
	
	Kuningas(Pelaajat pelaajat, int l, int k, String n){
		super(alusta);
		nimi=n;
		tyyppi=pelaajat;
		leveys=l;
		korkeus=k;
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
	
	
	void lisääIdentiteetteihin(Kuningas k){
		if(k.tyyppi==Pelaajat.VALKOINEN)
		valkoiset.put(k.nimi, k);
		else {mustat.put(k.nimi, k);}
	}
	
	/**
	 * metodi päivittää kuninkaan sijainnin. Metodi tarkistaa ensin onko siirto sallittu ja heittää
	 * virheen. Jos siirto on ok, metodi tarkistaa syökö siirto muita nappuloita ja päivittää
	 * sijainnin
	 * @param l
	 * @param k
	 * @throws VirheellinenSiirto
	 */
	public void päivitäKuninkaanSijainti(int l, int k) throws VirheellinenSiirto{
		
		int uusileveys;
		int uusikorkeus;
		
		if(this.tyyppi==Pelaajat.VALKOINEN) {
			uusileveys=leveys-l;
			uusikorkeus=korkeus+k;
		} else {
			uusileveys=leveys+l;
			uusikorkeus=korkeus+k;
		}

		if (tarkistaAskeleet(l, k) && tarkistaRajat(uusileveys, uusikorkeus)
				&& tarkistaPäällekkäisyys(uusileveys, uusikorkeus)) {
			syököNappulan(uusileveys, uusikorkeus);
			leveys=uusileveys;
			korkeus=uusikorkeus;
		}
	}
	
	/**
	 * metodi tarkistaa onko siirto sallittu
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaAskeleet (int l, int k) throws VirheellinenSiirto{
		if(l>2 || l==2 && k==2 || k>2) {
			throw new VirheellinenSiirto("Et voi liikuttaa kuningasta kuin"
					+ " yhden sivulle/oikealle/viistoon");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa ylittääkö siirto rajoja
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaRajat (int l, int k) throws VirheellinenSiirto{
		if (l>7 || l<0 || k>7 || k<0 ) {
			throw new VirheellinenSiirto("Et voi liikuttaa kuningasta yli laidan");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa siirtyykö nappula toisen nappulan päälle
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaPäällekkäisyys(int l, int k) throws VirheellinenSiirto{
		if(tarkistaLeveysKorkeus(l, k)==this.tyyppi) {
			throw new VirheellinenSiirto("Et voi siirtyä ruutuun saman värisen kanssa");
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


