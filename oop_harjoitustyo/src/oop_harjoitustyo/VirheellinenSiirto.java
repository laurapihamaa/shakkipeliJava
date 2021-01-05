package oop_harjoitustyo;

class VirheellinenSiirto extends Exception{
	
	String virhe;
	
	VirheellinenSiirto(String s){
		virhe=s;
	}
	
	void virheellinenNappula() {
		System.err.println(virhe);
	}
	void yliRajojen() {
		System.err.println(virhe);
	}

}
