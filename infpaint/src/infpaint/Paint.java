package infpaint;
import basiX.*;


public class Paint {

	int fh = 1000;
	int fb = 1500;
	int penstate = 0;
	int sd = 2;
	private boolean ende;
	private String hr,min;
	private Fenster f,ff,fo,fw,fwo;
	private Leinwand lw;
	private Stift s;
	private Tastatur t;
	private Maus m;
	private Knopf endknopf,optionen,farbe,farbe1,farbvs,farbueb,farbrnd,werkzeugoption,pen;
	private ListBox datei;
	private BeschriftungsFeld fbb,fhb,clock1,clock2,clock3;
	private ZahlenFeld fbz,fhz;
	private Rollbalken rgb1,rgb2,rgb3;

	

	public Paint() {
		ende = false;
		hr = String.valueOf(Hilfe.stunde());
		min = String.valueOf(Hilfe.minute());
		f = new Fenster("InfPaint",fb,fh,true);
		ff = new Fenster("Farbauswahl",1000,500,false);
		fw = new Fenster("Werkzeugauswahl",300,500,true);
		fo = new Fenster("Optionen", 500, 500, false);
		fwo = new Fenster("Werkzeugoptionen",500,500,false);
		lw = new Leinwand(0,30,fb,fh-30,f);
		m = new Maus(lw);
		s = new Stift(lw);
		endknopf = new Knopf("Ende",fb-100,0,100,30,f);
		optionen = new Knopf("Optionen",fb-200,0,100,30,f);
		fbb = new BeschriftungsFeld("FensterBreite", 0, 0, 100, 50, fo);
		fhb = new BeschriftungsFeld("FensterHöhhe", 0, 49, 100, 50, fo);
		fbb.setzeRand(Farbe.SCHWARZ, 1);
		fhb.setzeRand(Farbe.SCHWARZ, 1);
		fbz = new ZahlenFeld(99, 0, 50, 50, fo);
		fhz = new ZahlenFeld(99, 49, 50, 50, fo);
		fbz.setzeRand(Farbe.SCHWARZ, 1);
		fhz.setzeRand(Farbe.SCHWARZ, 1);
		fhz.setzeZahl(fh);
		fbz.setzeZahl(fb);
		farbe = new Knopf("Farbe", 70, 0, 100, 30, f);
		farbe1 = new Knopf("", 170, 0, 30, 30, f);
		farbe1.entferneRand();
		farbe1.setzeBenutzbar(false);
		farbe1.setzeHintergrundFarbe(s.farbe());
		datei = new ListBox(0,0,100,30,f);
		f.setzeHintergrundFarbe(Farbe.SCHWARZ);
		werkzeugoption = new Knopf("Werkzeugoptionen", 50, 450, 200, 50, fw);
		pen = new Knopf("", 50, 50, 50, 50, fw);
		pen.setzeIcon("/infpaint/pencil.png");
		rgb1 = new Rollbalken(false, 500, 50, 50, 400, ff);
		rgb2 = new Rollbalken(false, 600, 50, 50, 400, ff);
		rgb3 = new Rollbalken(false, 700, 50, 50, 400, ff);
		rgb1.setzeWerte(0, 255, 255);
		rgb2.setzeWerte(0, 255, 255);
		rgb3.setzeWerte(0, 255, 255);		
		farbvs = new Knopf("", 50, 50, 400, 400, ff);
		farbvs.setzeBenutzbar(true);
		farbvs.setzeHintergrundFarbe(s.farbe());
		farbueb = new Knopf("Übernehmen",850,450,150,50,ff);
		farbrnd = new Knopf("Zufällige Farbe", 800, 225, 150, 50, ff);
		datei.fuegeAn("Datei");
		datei.fuegeAn("Neu");
		datei.fuegeAn("Speichern");
		datei.fuegeAn("Laden");
		datei.setzeSchriftGroesse(15);
		clock1 = new BeschriftungsFeld(hr, fb-270, -10, 50, 50, f);
		clock1.setzeSchriftGroesse(15);
		clock1.setzeSchriftFarbe(Farbe.WEISS);
		clock2 = new BeschriftungsFeld(min, fb-220, -10, 50, 50, f);
		clock2.setzeSchriftGroesse(15);
		clock2.setzeSchriftFarbe(Farbe.WEISS);
		clock3 = new BeschriftungsFeld(":", fb-240, -12, 50, 50, f);
		clock3.setzeSchriftGroesse(15);
		clock3.setzeSchriftFarbe(Farbe.WEISS);
		
	
	}		


	
	public void main() {
		
		while(!ende) {
			Hilfe.kurzePause();
			s.bewegeAuf(m.hPosition(), m.vPosition());
			this.schreiben();
			this.endswitch();
			this.dateibox();
			this.uiscaling();
			this.windowswitches();
			this.uhr();
			this.farbe();
		}
		System.exit(0);
	}



	private void farbe() {
		if(rgb1.wurdeBewegt()||rgb2.wurdeBewegt()||rgb3.wurdeBewegt()) {
			farbvs.setzeHintergrundFarbe(Farbe.rgb(rgb1.wert(), rgb2.wert(), rgb3.wert()));	
		}
		
		if(farbueb.wurdeGedrueckt()) {
			ff.setzeSichtbar(false);
			s.setzeFarbe(farbvs.hintergrundFarbe());
			farbe1.setzeHintergrundFarbe(s.farbe());
		}
		
		if(farbrnd.wurdeGedrueckt()) {
			rgb1.setzeWert(Hilfe.zufall(0, 255));
			rgb2.setzeWert(Hilfe.zufall(0, 255));
			rgb3.setzeWert(Hilfe.zufall(0, 255));
			
		}
		
		
		
		
	}



	private void uhr() {
		if(!hr.equals(String.valueOf(Hilfe.stunde()))) {
			hr = String.valueOf(Hilfe.stunde());
			clock1.setzeText(hr);
		}
		
		if(!min.equals(String.valueOf(Hilfe.minute()))) {
			min = String.valueOf(Hilfe.minute());
			clock2.setzeText(min);
		}
		
		
	}



	private void windowswitches() {
		if(optionen.wurdeGedrueckt()) {
			fo.setzeSichtbar(true);
		}
		
		if(werkzeugoption.wurdeGedrueckt()) {
			fwo.setzeSichtbar(true);
		}
		
		if(farbe.wurdeGedrueckt()) {
			ff.setzeSichtbar(true);
		}
		
		
		
	}



	private void uiscaling() {
		f.setzeGroesse(fb, fh);
		lw.setzeGroesse(fb, fh-30);
		endknopf.setzePosition(fb-100, 0);
		optionen.setzePosition(fb-200, 0);
		
		
		
		
		if(fbz.ganzzahl()==fb) {
		}else {
			fb=fbz.ganzzahl();
		}
		if(fhz.ganzzahl()==fb) {
		}else {
			fh=fhz.ganzzahl();
		}
		
		
		
		
	}



	private void dateibox() {
		if(datei.wurdeGewaehlt()){
			String dateistring = datei.gewaehlterText();
			switch (dateistring) {
			case "Speichern":
				lw.speichere();
				datei.waehle(1);
				break;
			case "Laden":
				lw.ladeBild();
				break;
			case "Neu":
				
				if(Dialog.entscheidung("Entscheidung", "Möchten sie vorher Speichern?")) {
					lw.speichere();
				}
				
				
				lw.löscheAlles();
				break;
			}
		}
	}



	private void endswitch() {
		if(endknopf.wurdeGedrueckt()) {
			ende=true;
		}
		
	}



	private void schreiben() {
		switch(penstate) {
		case 0:
			if(m.istGedrueckt()) {
				s.runter();
			}else {
				s.hoch();
			}
			break;
		case 1:
			break;
		}
		
	}
	
	
}
	