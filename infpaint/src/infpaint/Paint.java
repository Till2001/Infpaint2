package infpaint;
import basiX.*;
import java.awt.Color;
import java.util.*;
import infpaint.*;

public class Paint {

	int fh = 1000;
	int fb = 1500;
	int sd = 5;
	int fvlw1,fvlw2,fvlw3,fvlws,txt,tyt,timeout,penstate,fuex,fuey;
	int maxtimeout = 2;
	private boolean ende;
	private String hr,min;
	private Fenster f,ff,fo,fw;
	private Leinwand lw;
	private Stift s;
	private Tastatur t;
	private Maus m;
	private Knopf endknopf,optionen,farbe,farbe1,farbvs,farbueb,farbrnd,pen,paintbucket,eraser,line,werkzeugliste,spray,farbverlauf,sdh,sdr,text,pipette;
	private ListBox datei,form;
	private ListAuswahl schriftwahl;
	private BeschriftungsFeld fbb,fhb,sdb,clock1,clock2,clock3,schrift,timeoutdesc;
	private ZahlenFeld fbz,fhz,sdz,timeoutz;
	private Rollbalken rgb1,rgb2,rgb3;
	private TextFeld texttext;
	private WahlBox normalbox,strichbox;
	private WahlBoxGruppe wbg;
	

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
		rgb1.setzeWerte(0, 255, 0);
		rgb2.setzeWerte(0, 255, 0);
		rgb3.setzeWerte(0, 255, 0);		
		farbvs = new Knopf("", 50, 50, 400, 400, ff);
		farbvs.setzeBenutzbar(true);
		farbvs.setzeHintergrundFarbe(s.farbe());
		farbueb = new Knopf("Übernehmen",850,450,150,50,ff);
		farbrnd = new Knopf("Zufällige Farbe", 800, 225, 150, 50, ff);
		paintbucket = new Knopf("",100,50,50,50,fw);
		paintbucket.setzeIcon("/infpaint/bucket.png");
		pipette = new Knopf("", 200, 100, 50, 50, fw);
		pipette.setzeIcon("/infpaint/pipette.png");
		line = new Knopf("",150,50,50,50,fw);
		line.setzeIcon("/infpaint/line.png");
		eraser = new Knopf("",200,50,50,50,fw);
		eraser.setzeIcon("/infpaint/eraser.png");
		werkzeugliste = new Knopf("Werkzeuge", fb-300,  0, 100, 30,f);
		farbverlauf = new Knopf("", 100, 100, 50, 50, fw);
		farbverlauf.setzeIcon("/infpaint/rainbow.png");
		text = new Knopf("",150,100,50,50,fw);
		text.setzeIcon("/infpaint/text.png");
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
		form = new ListBox(200, 0, 100, 30, f);
		form.fuegeAn("Formen");
		form.setzeSchriftGroesse(15);
		form.fuegeAn("Quadrat");
		form.fuegeAn("Rechteck");
		form.fuegeAn("Dreieck");
		form.fuegeAn("Kreis");
		texttext = new TextFeld(0, 0, 150,50, lw);
		texttext.setzeSichtbar(false);
		clock1 = new BeschriftungsFeld(hr, fb-370, -10, 50, 50, f);
		clock1.setzeSchriftGroesse(15);
		clock1.setzeSchriftFarbe(Farbe.WEISS);
		clock2 = new BeschriftungsFeld(min, fb-320, -10, 50, 50, f);
		clock2.setzeSchriftGroesse(15);
		clock2.setzeSchriftFarbe(Farbe.WEISS);
		clock3 = new BeschriftungsFeld(":", fb-340, -12, 50, 50, f);
		clock3.setzeSchriftGroesse(15);
		clock3.setzeSchriftFarbe(Farbe.WEISS);
		normalbox = new WahlBox("Normal", 50, 600, 150, 25, fw);
		strichbox = new WahlBox("Gestrichelt",50,625,150,25,fw);
		wbg = new WahlBoxGruppe();
		wbg.fuegeEin(normalbox);
		wbg.fuegeEin(strichbox);
		wbg.waehleBox(normalbox);
		schriftwahl = new ListAuswahl(0, 127, 149, 100, fo);
		schriftwahl.fuegeAn("Standart");
		schriftwahl.fuegeAn("Comic Sans");
		schriftwahl.fuegeAn("Ittalic");
		schriftwahl.setzeRand(Farbe.SCHWARZ, 1);
		schrift = new BeschriftungsFeld("Schriftart", 0, 98, 149, 30, fo);
		schrift.setzeRand(Farbe.SCHWARZ, 1);
		timeoutdesc = new BeschriftungsFeld("Timeout Zeit", 0, 226, 100, 30, fo);
		timeoutdesc.setzeRand(Farbe.SCHWARZ, 1);
		timeoutz = new ZahlenFeld(99, 226, 50, 30, fo);
		timeoutz.setzeRand(Farbe.SCHWARZ, 1);

	}		


	
	public void startup() {
		Dialog.info("Info", "Vielen dank das sie sich für infpaint entschieden haben.");
		this.main();
	}
	private void main() {
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
			this.formen();
			this.boxgruppen();
			this.bildschirmschoner();
		}
		if(Dialog.entscheidung("Entscheidung", "Möchten sie das Programm wirklich beenden?")) {
		System.exit(0);
		}
		ende=false;
		this.main();
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
	private void formen() {
		if(form.wurdeGewaehlt()) {
			String formstring = form.gewaehlterText();
			switch(formstring) {
			case "Quadrat":
				s.hoch();
				int lq = Dialog.eingabeINT("Eingabe", "Wie groß sollen die Seiten des Quadrats sein?");
				int wq = Dialog.eingabeINT("Eingabe", "In welchem Winkel soll das Quadrat gezeichnet werden?(0=nach Rechts unten,90,nach rechts ,180=nach links oben,270=nach rechts oben)");
				if(wq>360) {
					wq=360;
				}
				Dialog.info("Info","Klicken sie auf den Ursprung des Quadrats, rechte Maustatse zum abbrechen" );
				while(!m.istGedrueckt()) {
					Hilfe.kurzePause();
				}
				s.bewegeAuf(m.hPosition(), m.vPosition());
				s.dreheBis(wq);
				s.zeichneRechteck(lq, lq);
				break;
			case "Rechteck":
				int lo = Dialog.eingabeINT("Eingabe", "Wie lang sollen die obere und untere Seite des Rechtecks sein?");
				int ls = Dialog.eingabeINT("Eingabe", "Wie lang sollen die linke und rechte Seite des Rechtecks sein?");
				int rw = Dialog.eingabeINT("Eingabe", "In welchem winkel soll das Rechteck gezeichnet werden?");
				if(rw>360) {
					rw=360;
				}
				s.hoch();
				Dialog.info("Info", "Klicken sie auf den Eckpunkt ihres Rechtecks");
				while(!m.istGedrueckt()) {
					Hilfe.kurzePause();
				}
				s.bewegeAuf(m.hPosition(), m.vPosition());
				s.dreheBis(rw);
				s.zeichneRechteck(lo, ls);
				break;
			case "Dreieck":
				s.hoch();
				Dialog.info("Info", "Klicken sie auf den ersten eckpunkt");
				while (!m.istGedrueckt()) {
					Hilfe.kurzePause();
				}
				Hilfe.warte(100);
				s.bewegeAuf(m.hPosition(), m.vPosition());
				int dx1 = m.hPosition();
				int dy1 = m.vPosition();

				Dialog.info("Info", "Klicken sie auf den zweiten eckpunkt");

				while (!m.istGedrueckt()) {
					Hilfe.kurzePause();
				}
				Hilfe.warte(100);
				s.bewegeAuf(m.hPosition(), m.vPosition());
				int dx2 = m.hPosition();
				int dy2 = m.vPosition();
				Dialog.info("Info", "Klicken sie auf den dritten eckpunkt");

				while (!m.istGedrueckt()) {
					Hilfe.kurzePause();
				}
				Hilfe.warte(100);

				s.bewegeAuf(m.hPosition(), m.vPosition());
				int dx3 = m.hPosition();
				int dy3 = m.vPosition();
				s.runter();
				s.dreieck(dx1, dy1, dx2, dy2, dx3, dy3);
				s.hoch();
				break;
			case "Kreis":
				int r = Dialog.eingabeINT("Eingabe", "Wie groß soll der Radius des Kreis sein?");
				int kd = Dialog.eingabeINT("Eingabe", "Wie breit soll der Rand des Kreis sein?");
				int td = sd;
				s.setzeLinienBreite(kd);
				Dialog.info("Info", "Klicken sie auf den Mittlepunkt des Kreises");
				while(!m.istGedrueckt()) {
					Hilfe.kurzePause();
				}
				s.zeichneKreis(r);
				s.setzeLinienBreite(td);
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

	private void boxgruppen() {
		if(wbg.wurdeGeaendert()) {
			if(wbg.ausgewaehlteBox().equals(normalbox)) {
				s.setzeLinienTyp(0);
				
			}
			if(wbg.ausgewaehlteBox().equals(strichbox)) {
				s.setzeLinienTyp(1,1);
			}
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
			ende=true; //ende
		}
		
	}

	
	private void bildschirmschoner() {
		if(!m.wurdeBewegt()) {
			Hilfe.warte(1000);
			timeout++;			
			if(timeout>=2) {
				lw.speichereUnter("/infpaint/tempsave.png");
				while(!m.wurdeBewegt()) {
					lw.ladeBild("/infpaint/screensaver.png");
				}
				lw.ladeBild("/infpaint/tempsave.png");
			}
			
			
		}else {
			timeout = 0;
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
		
		if(pipette.wurdeGedrueckt()) {
			penstate=6;
		}
		
		if(text.wurdeGedrueckt()) {
			penstate=7;
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
				Stack<Integer> st1 = new Stack<Integer>();
				Stack<Integer> st2 = new Stack<Integer>();
				Stack<Color> st3 = new Stack<Color>();
				s.normal();
				int tx = 0;
				int ty = 0;
				s.hoch();
				s.bewegeAuf(m.hPosition(), m.vPosition());
				st3.add(lw.farbeVon(m.hPosition(),m.vPosition()));
				st1.clear();
				st2.clear();
				st1.add(m.hPosition());
				st2.add(m.vPosition());
				Dialog.info("Info", "Zum Unterbrechen Rechte Maustaste drücken");
				while(!st1.empty()||!st2.empty()) {
					tx=st1.pop();
					ty=st2.pop();
					this.redraw();
					if(m.istRechtsGedrueckt()) {
						st1.clear();
						st2.clear();
					}
					if(lw.farbeVon(tx, ty).equals(st3.peek())) {
						lw.setzeFarbeBei(tx, ty, s.farbe());
						st1.push(tx+1); 
						st2.push(ty);
						st1.push(tx-1); 
						st2.push(ty);
						st1.push(tx); 
						st2.push(ty+1);
						st1.push(tx); 
						st2.push(ty-1);
						
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
		case 6:
			if (m.istGedrueckt()) {
				s.setzeFarbe(lw.farbeVon(m.hPosition(), m.vPosition()));
				farbe1.setzeHintergrundFarbe(lw.farbeVon(m.hPosition(), m.vPosition()));			
			}
			break;
		case 7:
			if(m.istGedrueckt()) {
				txt = m.hPosition();
				tyt = m.vPosition();
				texttext.setzePosition(m.hPosition(), m.vPosition());
				texttext.setzeSichtbar(true);
			}
			if(texttext.returnWurdeGedrueckt()) {
				s.hoch();
				s.bewegeAuf(txt, tyt+30);
				s.schreibe(texttext.text());
				texttext.setzeSichtbar(false);
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
			if(fvlw1>0||fvlw2<255||fvlw3>0) {
				fvlw1--;
				fvlw2++;
				fvlw3--;
			}else { // 2
				fvlws=7;
			}
			break;
		case 7:
			if(fvlw2>0) {
				fvlw2--;
			}else {
				fvlws=0;
			}
			break;
		
		}
		
	}
}
