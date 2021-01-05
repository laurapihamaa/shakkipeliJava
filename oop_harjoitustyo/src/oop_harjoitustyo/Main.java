package oop_harjoitustyo;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main (String args []) {
		
	Nappulat.lisääNappulat();
	
	System.out.println("\nPeliä pelataan ilmoittamalla komentoriville ensin siirrettävä nappula, ");
	System.out.println("sitten askeleet vasen/oikea ja lopuksi ylös/alas\n");
	System.out.println("Esimerkki1: Liikutaan nappulaa:kuningas1 Korkeus:1 Leveys:1");
	System.out.println("nyt Kuningas on siirtynyt yhden askeleen ylös ja oikealle\n");
	System.out.println("Esimerkki2: Liikutan nappulaa kuningas2, korkeus:-1 Leveys:-1");
	System.out.println("Nyt kuningas on siirtynyt yhden askeleen alas ja vasemmalle\n");
	System.out.println("onko säännöt ymmärretty?");
	
	Scanner lukija = new Scanner(System.in);
	lukija.nextLine();
	
	System.out.println("\nAloitetaan peli");
	System.out.println("\nLuodaan pelaajat");
	Pelaajat pelaaja1 = Pelaajat.MUSTA;
	System.out.println("Anna pelaajan 1 nimi:");
	String pelaaja1Nimi = lukija.nextLine();
	System.out.println(pelaaja1Nimi + " on " + pelaaja1);
	
	Pelaajat pelaaja2 = Pelaajat.VALKOINEN;
	System.out.println("Anna pelaajan 2 nimi:");
	String pelaaja2Nimi = lukija.nextLine();
	System.out.println(pelaaja2Nimi + " on " + pelaaja2);

	Nappulat.päivitäAlusta();
	boolean syöteOnNumero;
	boolean oikeasiirto;
	boolean oikeanappula;
	int leveys=0;
	int korkeus=0;
	String nappula=null;
	Nappulat n = new Nappulat(null);
	
	while(Nappulat.kuningasElossa()) {
		oikeasiirto=false;
		oikeanappula=false;
	while(!oikeanappula) {
	try {
	System.out.println(pelaaja2Nimi + "n vuoro tehdä siirto");
	System.out.println("Mitä nappulaa siirrät?");
	nappula=lukija.next();
	n=Nappulat.tarkistaValkoinenNappula(nappula);
	Nappulat.tarkistaElossa(n);
	Nappulat.tarkistaSiirrot(n);
	oikeanappula=true;}
	catch (VirheellinenSiirto e){
		e.virheellinenNappula();}}//oikeanappula
	
	while(!oikeasiirto) {
	try {
	System.out.println("Montako pystysuuntaan?");
	syöteOnNumero=false;
	
	while(!syöteOnNumero) {
	if(lukija.hasNextInt()==true) {
	leveys = lukija.nextInt();
	syöteOnNumero=true;
	} else {System.out.println("syötä numero");
	lukija.next();}
	}
	
	System.out.println("Montako vaakasuuntaan?");
	syöteOnNumero=false;
	while(!syöteOnNumero) {
		if(lukija.hasNextInt()==true) {
		korkeus = lukija.nextInt();
		syöteOnNumero=true;
		} else {System.out.println("syötä numero");
		lukija.next();} //if-else
	}//syöteonnumero
	
	Nappulat.päivitäNappula(n, leveys, korkeus);
	oikeasiirto=true;
	} catch (VirheellinenSiirto e) {
		e.yliRajojen();} //try-catch
	}//oikeasiirto
	
	Nappulat.päivitäAlusta();
	
	oikeasiirto=false;
	oikeanappula=false;
	if (Nappulat.kuningasElossa()) {
	while(!oikeanappula) {
		try {
		System.out.println(pelaaja1Nimi + "n vuoro tehdä siirto");
		System.out.println("Mitä nappulaa siirrät?");
		nappula=lukija.next();
		n=Nappulat.tarkistaMustaNappula(nappula);
		Nappulat.tarkistaElossa(n);
		Nappulat.tarkistaSiirrot(n);
		oikeanappula=true;}
		catch (VirheellinenSiirto e){
			e.virheellinenNappula();}}//oikeanappula
		
		while(!oikeasiirto) {
		try {
		System.out.println("Montako pystysuuntaan?");
		syöteOnNumero=false;
		
		while(!syöteOnNumero) {
		if(lukija.hasNextInt()==true) {
		leveys = lukija.nextInt();
		syöteOnNumero=true;
		} else {System.out.println("syötä numero");
		lukija.next();}//ifelse
		}//syöteonnumero 
		
		System.out.println("Montako vaakasuuntaan?");
		syöteOnNumero=false;
		while(!syöteOnNumero) {
			if(lukija.hasNextInt()==true) {
			korkeus = lukija.nextInt();
			syöteOnNumero=true;
			} else {System.out.println("syötä numero");
			lukija.next();}
		}//syöteonnumero
		
		Nappulat.päivitäNappula(n, leveys, korkeus);
		oikeasiirto=true;
		} catch (VirheellinenSiirto e) {
			e.yliRajojen();}
		} //oikeasiirto

		oikeasiirto=false;
		oikeanappula=false;
		Nappulat.päivitäAlusta();
	}}
	
	if (Nappulat.kuningas1.elossa == false) {
		System.out.println("Peli päättyi, " + pelaaja1 + "voitti");
	}else {
		System.out.println("Peli päättyi, " + pelaaja2 + "voitti");
	}
		
}}

