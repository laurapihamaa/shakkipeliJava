package oop_harjoitustyo;

/**
 * Alusta-luokka edustaa shakkilautaa. Alusta sisältää 8x8-kokoisen matriisin, 
 * joka sisältää kaikki elossa olevat shakkinappulat
 *
 */
public class Alusta{
	
	static Nappulat [][] alusta;
	
	Alusta(Nappulat [][] a) {
		alusta=a;
	}
	
	static void tulostaAlusta(Nappulat [][] alusta) {
		
		  for (int i = 0; i < alusta.length; i++) {
		        for (int j = 0; j < alusta[0].length; j++) {
		            if (j == 0 || j % (alusta.length - 1) != 0) {
		                System.out.print(alusta[i][j] + " ");
		            }
		            else {
		                System.out.println(alusta[i][j]);
		            }
		         }
		     }
		}
	
	/**
	 * metodi tarkistaa onko haluttujen siirtymäpaikkojen välillä muita nappuloita (ts. hyppääkö
	 * nappula toisen nappulan yli) ja heittää virheen. Metodi tarkistaa ainoastaan suorat
	 * @param l
	 * @param k
	 * @param leveys
	 * @param korkeus
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	boolean tarkistaSuorat(int l, int k, int leveys, int korkeus) throws VirheellinenSiirto {
		boolean onnistuuko=true;
		
		if (l>0 && k==0 && alusta[leveys][korkeus].palautaTyyppi()==Pelaajat.VALKOINEN) {
			for (int i=l-1; i>0; i--) {
				l=leveys-i;
				if (alusta[l][korkeus]!=null) {
					onnistuuko=false;
				}}}
		if (l<0 && k==0 && alusta[leveys][korkeus].palautaTyyppi()==Pelaajat.VALKOINEN) {
			for (int i=Math.abs(l)-1; i>0; i--) {
				l=leveys+i;
				if (alusta[l][korkeus]!=null) {
					onnistuuko=false;
				}}}
		if (l>0 && k==0 && alusta[leveys][korkeus].palautaTyyppi()==Pelaajat.MUSTA) {
			for (int i=l-1; i>0; i--) {
				l=leveys+i;
				if (alusta[l][korkeus]!=null) {
					onnistuuko=false;
				}}}
		if (l<0 && k==0 && alusta[leveys][korkeus].palautaTyyppi()==Pelaajat.MUSTA) {
			for (int i=Math.abs(l)-1; i>0; i--) {
				l=leveys-i;
				if (alusta[l][korkeus]!=null) {
					onnistuuko=false;
				}}}
		if (l==0 && k>0) {
			for (int i=k-1; i>0; i--) {
				k=korkeus+i;
				if (alusta[leveys][k]!=null) {
					onnistuuko=false;
				}}}
		
		if (l==0 && k<0) {
			for (int i=Math.abs(k)-1; i>0; i--) {
				k=korkeus-i;
				if (alusta[leveys][k]!=null) {
					onnistuuko=false;
				}}}
		
		if (!onnistuuko) {
			throw new VirheellinenSiirto("Et voi hypätä toisen nappulan yli");}
		
		return onnistuuko;
	}
	
	/**
	 * metodi tarkistaa onko haluttujen siirtymäpaikkojen välillä muita nappuloita (ts. hyppääkö
	 * nappula toisen nappulan yli) ja heittää virheen. Metodi tarkistaa ainoastaan diagonaalit
	 * @param l
	 * @param k
	 * @param leveys
	 * @param korkeus
	 * @return true || false
	 * @throws VirheellinenSiirto
	 */
	
	boolean tarkistaDiagonaalit (int l, int k, int leveys, int korkeus) throws VirheellinenSiirto {
		
		boolean onnistuuko=true;
		
		if (l>0 && k>0) {
			for (int i=l-1; i>0; i--) {
				if(alusta[leveys][korkeus].palautaTyyppi()==Pelaajat.VALKOINEN) {
					if(alusta[leveys-i][korkeus+i] != null) {
					onnistuuko=false;}
				} else if (alusta[leveys+i][korkeus+i] != null){
					onnistuuko=false;
				}}
			}
		if (l<0 && k>0) {
			for (int i=Math.abs(l)-1; i>0; i--) {
				if(alusta[leveys][korkeus].palautaTyyppi()==Pelaajat.VALKOINEN) {
					if(alusta[leveys+i][korkeus+i] != null) {
					onnistuuko=false;}
				} else if (alusta[leveys-i][korkeus+i] != null){
					onnistuuko=false;
				}}
			}
		if (l<0 && k<0) {
			for (int i=l-1; i>0; i--) {
				if(alusta[leveys][korkeus].palautaTyyppi()==Pelaajat.VALKOINEN) {
					if(alusta[leveys+i][korkeus-i] != null) {
					onnistuuko=false;}
				} else if (alusta[leveys-i][korkeus-i] != null){
					onnistuuko=false;
				}}
			}
		if (l>0 && k<0) {
			for (int i=l-1; i>0; i--) {
				if(alusta[leveys][korkeus].palautaTyyppi()==Pelaajat.VALKOINEN) {
					if(alusta[leveys-i][korkeus-i] != null) {
					onnistuuko=false;}
				} else if (alusta[leveys+i][korkeus-i] != null){
					onnistuuko=false;
				}}
			}
		if (!onnistuuko) {
			throw new VirheellinenSiirto("Et voi hypätä toisen nappulan yli");
		}	
		
	return onnistuuko;	
	}
	
	
}
