package infpaint;
import basiX.*;


public class Paint {

	int fh = 1000;
	int fb = 1500;
	int fuex = 0;
	int fuey = 0;
	int penstate = 0;
	int sd = 2;
	int fvlw1 = 0;
	int fvlw2 = 0;
	int fvlw3 = 0;
	int fvlws = 0;
	private boolean ende;
	private String hr,min;
	private Fenster f,ff,fo,fw;
	private Leinwand lw;
	private Stift s;
	private Tastatur t;
	private Maus m;
	private Knopf endknopf,optionen,farbe,farbe1,farbvs,farbueb,farbrnd,pen,paintbucket,eraser,line,werkzeugliste,spray,farbverlauf,sdh,sdr;
	private ListBox datei;
	private BeschriftungsFeld fbb,fhb,sdb,clock1,clock2,clock3;
	private ZahlenFeld fbz,fhz,sdz;
	private Rollbalken rgb1,rgb2,rgb3;

	

	public Paint() {
		ende = false;
		hr = String.valueOf(Hilfe.stunde());
		min = String.valueOf(Hilfe.minute());
		f = new Fenster("InfPaint",fb,fh,true);
		ff = new Fenster("Farbauswahl",1000,500,false);
		fw = new Fenster("Werkzeugauswahl",300,750,true);
		fo = new Fenster("Optionen", 500, 500, false);
		lw = new Leinwand(0,30,fb,fh-30,f);
		m = new Maus(lw);
		s = new Stift(lw);
		t = new Tastatur();
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
		paintbucket = new Knopf("",100,50,50,50,fw);
		paintbucket.setzeIcon("/infpaint/bucket.png");
		line = new Knopf("",150,50,50,50,fw);
		line.setzeIcon("/infpaint/line.png");
		eraser = new Knopf("",200,50,50,50,fw);
		eraser.setzeIcon("/infpaint/eraser.png");
		werkzeugliste = new Knopf("Werkzeuge", fb-300,  0, 100, 30,f);
		farbverlauf = new Knopf("", 100, 100, 50, 50, fw);
		farbverlauf.setzeIcon("/infpaint/rainbow.png");
		datei.fuegeAn("Datei");
		datei.fuegeAn("Neu");
		datei.fuegeAn("Speichern");
		datei.fuegeAn("Laden");
		datei.setzeSchriftGroesse(15);
		spray = new Knopf("",50,100,50,50,fw);
		spray.setzeIcon("/infpaint/Spray.png");
		sdb = new BeschriftungsFeld("Stiftdicke", 50, 371, 170, 30, fw);
		sdb.setzeRand(Farbe.SCHWARZ, 1);
		sdz = new ZahlenFeld(50, 400, 170, 30, fw);
		sdz.setzeRand(Farbe.SCHWARZ, 1);
		sdz.setzeZahl(sd);
		sdh = new Knopf("",220,371,30,30,fw);
		sdh.setzeIcon("/infpaint/hoch.png");
		sdr = new Knopf("", 220, 400, 30, 30, fw);
		sdr.setzeIcon("/infpaint/runter.png");
		clock1 = new BeschriftungsFeld(hr, fb-370, -10, 50, 50, f);
		clock1.setzeSchriftGroesse(15);
		clock1.setzeSchriftFarbe(Farbe.WEISS);
		clock2 = new BeschriftungsFeld(min, fb-320, -10, 50, 50, f);
		clock2.setzeSchriftGroesse(15);
		clock2.setzeSchriftFarbe(Farbe.WEISS);
		clock3 = new BeschriftungsFeld(":", fb-340, -12, 50, 50, f);
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
			this.keybindings();
			this.werkzeugswitches();
			this.stiftdicke();
		}
		System.exit(0);
	}



	private void keybindings() {
		if(t.wurdeGedrueckt()) {
			t.holeZeichen();
			
			switch((int)t.aktuellesZeichen()) {
			case 27:
				lw.löscheAlles();
				break;
			
			}
			
			
			
		}
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

	private void stiftdicke() {
		if(sdz.textWurdeGeaendert()) {
			sd=sdz.ganzzahl();
			this.intov();
		}
		
		if(sdh.wurdeGedrueckt()) {
			sd++;
			sdz.setzeZahl(sd);
			this.intov();
		}
		
		if(sdr.wurdeGedrueckt()) {
			sd--;
			sdz.setzeZahl(sd);
			this.intov();
		}
		
	}

	private void windowswitches() {
		if(optionen.wurdeGedrueckt()) {
			fo.setzeSichtbar(true);
		}
		
		if(farbe.wurdeGedrueckt()) {
			ff.setzeSichtbar(true);
		}
		
		if(werkzeugliste.wurdeGedrueckt()) {
			fw.setzeSichtbar(true);
		}
	}

	private void intov() {
		if(sd>=51) {
			sd=50;
			sdz.setzeZahl(sd);
			this.sdu();
		}else {
			this.sdu();
		}
		if(sd<=0) {
			sd=1;
			sdz.setzeZahl(sd);
			this.sdu();
		}else {
			this.sdu();
		}
		
	}
	
	private void sdu() {
		s.setzeLinienBreite(sd);
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

	private void werkzeugswitches() {
		
		if(pen.wurdeGedrueckt()) {
			penstate=0;
		}
		
		if(eraser.wurdeGedrueckt()) {
			penstate=1;
		}
		
		if(line.wurdeGedrueckt()) {
			penstate=2;
		}
		
		if(paintbucket.wurdeGedrueckt()) {
			penstate=3;
		}		
		
		if(spray.wurdeGedrueckt()) {
			penstate=4;
		}
		
		if(farbverlauf.wurdeGedrueckt()) {
			penstate=5;
		}
	}

	private void schreiben() {
		switch(penstate) {
		case 0:
			s.normal();
			if(m.istGedrueckt()) {
				s.runter();
			}else {
				s.hoch();
			}
			break;
		case 1:
			s.radiere();
			if(m.istGedrueckt()) {
				s.runter();
			}else {
				s.hoch();
			}
			break;
		case 2:
			
			break;
		case 3:
			if(m.istGedrueckt()) {
				s.normal();
				boolean fuew = true;
				int i = 0;
				s.hoch();
				s.bewegeAuf(m.hPosition(), m.vPosition());
				fuey = m.vPosition();
				fuex = m.hPosition();
				lw.setzeFarbeBei(fuex, fuey, s.farbe());
				Dialog.info("Info", "Zum Unterbrechen Rechte Maustaste drücken");
				while (fuew) {
						if(i>=5) {
							this.redraw();
							i=0;
						}
						if(m.istRechtsGedrueckt()) {
							fuew=false;
						}
						
					if(!lw.farbeVon(fuex, fuey).equals(s.farbe())) {
						lw.setzeFarbeBei(fuex, fuey, s.farbe());
						i++;
					}else {
						if(!lw.farbeVon(fuex+1, fuey).equals(s.farbe())) {
							lw.setzeFarbeBei(fuex+1, fuey, s.farbe());
							fuex++;
							i++;
						}else {
							if(!lw.farbeVon(fuex, fuey-1).equals(s.farbe())) {
								lw.setzeFarbeBei(fuex, fuey-1, s.farbe());
								fuey--;
								i++;
							}else {
								if(!lw.farbeVon(fuex-1, fuey).equals(s.farbe())) {
									lw.setzeFarbeBei(fuex-1, fuey, s.farbe());
									fuex--;
									i++;
								}else {
									if(!lw.farbeVon(fuex, fuey+1).equals(s.farbe())) {
										lw.setzeFarbeBei(fuex, fuey+1, s.farbe());
										fuey++;
										i++;
									}else {
										if(!lw.farbeVon(fuex+1, fuey+1).equals(s.farbe())) {
											lw.setzeFarbeBei(fuex+1, fuey+1, s.farbe());
											fuey++;
											fuex++;
											i++;
										}else {
											if(!lw.farbeVon(fuex+1, fuey-1).equals(s.farbe())) {
												lw.setzeFarbeBei(fuex+1, fuey-1, s.farbe());
												fuey--;
												fuex++;
												i++;
											}else {
												if(!lw.farbeVon(fuex-1, fuey+1).equals(s.farbe())) {
													lw.setzeFarbeBei(fuex-1, fuey+1, s.farbe());
													fuey++;
													fuex--;
													i++;
												}else {
													if(!lw.farbeVon(fuex-1, fuey-1).equals(s.farbe())) {
														lw.setzeFarbeBei(fuex-1, fuey-1, s.farbe());
														fuey--;
														fuex--;
														i++;
													}else {
														fuew = false;
													}
												}
											}
										}
									}
								}
							}
							
						}
							
							
							
							
						}
				
							}
			}
			break;
		case 4:
			s.wechsle();
			if(m.istGedrueckt()) {
				s.runter();
			}else {
				s.hoch();
			}
			break;
		case 5:
			if(m.istGedrueckt()) {
				s.runter();
				this.fvl();
			}else {
				s.hoch();
			}
			break;
		}
		
	}

	private void redraw() {
		lw.setzeGroesse(fb+1, fh-30);
		lw.setzeGroesse(fb, fh-30);
	}
	
	private void fvl() {
		s.setzeFarbe(Farbe.rgb(fvlw1, fvlw2, fvlw3));
		switch(fvlws) {
		case 0:
			if(fvlw1<255) {
				fvlw1++;
			}else { //1
				fvlws=1;
			}
			break;
		case 1:
			if(fvlw2<255) {
				fvlw2++;
			}else { //1|2
				fvlws=2;
			}
			break;
		case 2:
			if(fvlw3<255) {
				fvlw3++;
			}else { //1|2|3
				fvlws=3;
			}
			break;
		case 3:
			if(fvlw1>0) {
				fvlw1--;
			}else { //2|3
				fvlws=4;
			}
			break;
		case 4:
			if(fvlw2>0) {
				fvlw2--;
			}else { //3
				fvlws=5;
			}
			break;
		case 5:
			if(fvlw1<255) {
				fvlw1++;
			}else { //1|3
				fvlws=6;
			}
			break;
		case 6:
			if(fvlw1>0||fvlw3>0) {
				fvlw1--;
				fvlw3--;
			}else {
				fvlws=0;
			}
			break;
		}
		
	}
}
	