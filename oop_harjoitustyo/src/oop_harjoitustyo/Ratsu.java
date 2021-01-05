package oop_harjoitustyo;

/**
 * Ratsu-luokka edustaa shakkilaudan ratsuja ja on Nappulat-luokan aliluokka.
 * Jokaisella ratsulla on oma tyyppi(valkoinen/musta),
 * leveys, korkeys, nimi sekä boolean-arvo elossa
 *
 */

public class Ratsu extends Nappulat {
	
	Pelaajat tyyppi;
	Integer leveys;
	Integer korkeus;
	String nimi;
	boolean elossa;
	
	Ratsu (Pelaajat pelaaja, int l, int k, String n){
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
	
	void muutaElossa() {
		elossa=false;
	}
	
	/**
	 * metodi päivittää ratsun sijainnin. Metodi tarkistaa ensin onko siirto sallittu ja heittää
	 * virheen. Jos siirto on ok, metodi tarkistaa syökö siirto muita nappuloita ja päivittää
	 * sijainnin
	 * @param l
	 * @param k
	 * @throws VirheellinenSiirto
	 */
	
	public void päivitäRatsunSijainti(int l, int k) throws VirheellinenSiirto{
		
		int uusileveys;
		int uusikorkeus;
		
		if(this.tyyppi==Pelaajat.VALKOINEN) {
			uusileveys=leveys-l;
			uusikorkeus=korkeus+k;
		} else {
			uusileveys=leveys+l;
			uusikorkeus=korkeus+k;}
		
		if (tarkistaSiirrot(l, k) && tarkistaRajat(uusileveys, uusikorkeus)
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
	boolean tarkistaSiirrot(int l, int k) throws VirheellinenSiirto{
		if(l==2 && k==1 || l==2 && k==-1 || l==1 && k==2 || l==1 && k==-2 
				|| l==-1 && k==2 || l==-1 && k==-2){
				return true;
			} else {
				throw new VirheellinenSiirto("Ratsu liikkuu L-kirjaimen muotoisesti");}
	}
	
	/**
	 * metodi tarkistaa ylittääkö siirto rajoja
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaRajat(int l, int k) throws VirheellinenSiirto{
		if (l>7 || l<0 || k>7 || k<0 ) {
			throw new VirheellinenSiirto("Et voi liikuttaa Ratsua yli laidan");
		}else {return true;}
	}
	
	/**
	 * metodi tarkistaa siirtyykö nappula toisen nappulan päälle
	 * @param l
	 * @param k
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaPäällekkäisyys (int l, int k) throws VirheellinenSiirto{
		if(tarkistaLeveysKorkeus(l, k)==tyyppi) {
			throw new VirheellinenSiirto("Et voi siirtyä ruutuun saman värisen kanssa");
		} else {return true;}
	}
	
	/**
	 * metodi tarkistaa syökö nappula toisen nappulan
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
	
	void tarkistaRatsunSiirrot() throws VirheellinenSiirto{
		
	int virheet=0;
		
	if (tarkistaLeveysKorkeus(leveys+2, korkeus+1)==tyyppi || leveys+2>7 || korkeus+1>7) {
		virheet++;}
	if (tarkistaLeveysKorkeus(leveys-2, korkeus+1)==tyyppi || leveys-2<0 || korkeus+1>7) {
		virheet++;}
	if (tarkistaLeveysKorkeus(leveys+2, korkeus-1)==tyyppi || leveys+2>7 || korkeus-1<0) {
		virheet++;}
	if (tarkistaLeveysKorkeus(leveys-2, korkeus-1)==tyyppi || leveys-2<0 || korkeus-1<0) {
		virheet++;}
	if (tarkistaLeveysKorkeus(leveys+1, korkeus+2)==tyyppi || leveys+1 >7 || korkeus+2>7){
		virheet++;}
	if (tarkistaLeveysKorkeus(leveys+1, korkeus-2)==tyyppi || leveys+1>7 || korkeus-2<0){
		virheet++;}
	if (tarkistaLeveysKorkeus(leveys-1, korkeus+2)==tyyppi || leveys-1<0 ||korkeus+2>7) {
		virheet++;}
	if (tarkistaLeveysKorkeus(leveys-1, korkeus-2)==tyyppi || leveys-1<0 || korkeus-2<0) {
		virheet++;}
	if (virheet==8) {
		throw new VirheellinenSiirto("Valitsemallasi nappulalla ei ole mahdollisia siirtoja. Valitse toinen nappula");
	}
	}
	
	public String toString() {
		return nimi;
	}
	
	void lisääIdentiteetteihin(Ratsu k){
		if(k.tyyppi==Pelaajat.VALKOINEN)
		valkoiset.put(k.nimi, k);
		else {mustat.put(k.nimi, k);}
	}

}
