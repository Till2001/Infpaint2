package infpaint;
import basiX.*;


public class Paint {

	int fh = 900;
	int fb = 1600;
	int penstate = 0;
	private boolean ende;
	private Fenster f,ff,fw;
	private Leinwand lw;
	private Stift s;
	private Tastatur t;
	private Maus m;
	private Knopf endknopf;
	
	
	

	public Paint() {
		ende = false;
		f = new Fenster("InfPaint",fb,fh,true);
		ff = new Fenster("Farbauswahl",500,500,false);
		fw = new Fenster("Werkzeugauswahl",300,800,true);
		lw = new Leinwand(0,50,fb,fh-50,f);
		m = new Maus(lw);
		s = new Stift(lw);
		endknopf = new Knopf("Ende",fb-100,0,100,50,f);
	
	
	
	
	}		


	
	public void main() {
		
		while(!ende) {
			Hilfe.kurzePause();
			s.bewegeAuf(m.hPosition(), m.vPosition());
			this.schreiben();
			this.endswitch();
		
			
		}
		System.exit(0);
		
	
	
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
		
		
		}
		
	}
	
	
}
	