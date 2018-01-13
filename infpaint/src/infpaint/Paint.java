package infpaint;
import basiX.*;


public class Paint {

	int fh = 900;
	int fb = 1400;
	int penstate = 0;
	int sd = 2;
	private boolean ende;
	private Fenster f,ff,fw;
	private Leinwand lw;
	private Stift s;
	private Tastatur t;
	private Maus m;
	private Knopf endknopf;
	private ListBox datei;
	
	

	public Paint() {
		ende = false;
		f = new Fenster("InfPaint",fb,fh,true);
		ff = new Fenster("Farbauswahl",500,500,false);
		fw = new Fenster("Werkzeugauswahl",300,800,true);
		lw = new Leinwand(0,30,fb,fh-30,f);
		m = new Maus(lw);
		s = new Stift(lw);
		endknopf = new Knopf("Ende",fb-100,0,100,30,f);
		datei = new ListBox(0,0,100,30,f);
		
	
		datei.fuegeAn("Datei");
		datei.fuegeAn("Neu");
		datei.fuegeAn("Speichern");
		datei.fuegeAn("Laden");
		datei.setzeSchriftGroesse(15);
	}		


	
	public void main() {
		
		while(!ende) {
			Hilfe.kurzePause();
			s.bewegeAuf(m.hPosition(), m.vPosition());
			this.schreiben();
			this.endswitch();
			this.dateibox();
			this.uiscaling();
		}
		System.exit(0);
		
		
	
	
	}



	private void uiscaling() {
		if(fb==f.breite()||fh==f.hoehe()) {
		}else {
			fb = f.breite();
			fh = f.hoehe();
			lw.setzeGroesse(fb, fh);
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
	