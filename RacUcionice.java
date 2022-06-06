class RacUcionice{
	public static void main (String[] args) {
		RacunarskeUcionice ru = new RacunarskeUcionice();
		ru.dodajUcionicu("Crvena");
		ru.dodajUcionicu("Zelena");
		ru.dodajUcionicu("Crvena");
		ru.dodajUcionicu("Zuta");
		ru.dodajUcionicu("Crna");
		ru.dodajUcionicu("Bela");
		
		ru.dodajOpremu("Monitor", "LG", 0);
		ru.dodajOpremu("Stalak", "Plasticni", 0);
		ru.dodajOpremu("Civiluk", "Drveni", 0);
		ru.dodajOpremu("Stolica", "Gejmerska", 0);
		ru.dodajOpremu("Laptop", "Asus", 3);
		ru.dodajOpremu("Laptop", "Lenovo", 0);
		ru.dodajOpremu("Sto", "Drveni", 3);
		ru.dodajOpremu("Bezicni mis", "Logitech", 3);
		ru.dodajOpremu("Monitor", "LG", 0);
		ru.dodajOpremu("Stalak", "Plasticni", 0);
		ru.dodajOpremu("Civiluk", "Drveni", 0);
		ru.dodajOpremu("Stolica", "Gejmerska", 0);
		ru.dodajOpremu("Laptop", "Asus", 3);
		ru.dodajOpremu("Laptop", "Lenovo", 0);
		ru.dodajOpremu("Sto", "Drveni", 3);
		ru.dodajOpremu("Bezicni mis", "Logitech", 3);
		
		ru.dodajuNajprazniju("Stalak", "Plasticni");
		
		System.out.println(ru);
		
		ru.izbaciIzZadUc("Civiluk", 0);
		ru.izbaciIzZadUcTipiOpis("Civiluk", "Drveni", 0);
		ru.izbaciSve("Laptop", 0);
		
		System.out.println(ru);
		
		ru.prebaci("Sto");
		
		System.out.println(ru);
		
		System.out.println("Prebrojani: " + ru.prebrojTip("Laptop"));
	}
}

class RacunarskeUcionice{
	
	//Pokazivac na prvu ucionicu.
	Ucionica prvi;
	int ukUcionica;
	
	public RacunarskeUcionice(){
		this.ukUcionica = 0;
		this.prvi = null;
	}
	
	public String toString(){
		Ucionica temp = prvi;
		String s = "Racunarske ucionice: ";
		while(temp!=null){
			s += temp + "\n";
			temp = temp.veza;
		}
		return s;
	}
	
	//Definisemo klasu Ucionica, sa bojomZidova(String) i spiskom opreme.
	class Ucionica{
		String bojaZidova;
		Oprema prvaOprema;
		Ucionica veza;
		
		public Ucionica(String boja){
			this.bojaZidova = boja;
			this.veza = null;
		}
		
		public String toString(){
			Oprema temp = prvaOprema;
			String s = "[ " + bojaZidova + ": ";
			while(temp!=null){
				s += temp + ", ";
				temp = temp.veza;
			}
			s += "], ";
			return s;
		}
	}
	
	//Definisemo klasu Oprema, sa tipom i kratkim opisom(String).
	class Oprema{
		String tip;
		String opis;
		Oprema veza;
		
		public Oprema(String tip, String opis){
			this.tip = tip;
			this.opis = opis;
			this.veza = null;
		}
		
		public String toString(){
			return tip + ": " + opis;
		}
	}
	
	//Dodaje ucionicu na kraj. Uvecava ukUcionica za 1
	public void dodajUcionicu(String bojaZidova){
		Ucionica nova = new Ucionica(bojaZidova);
		if(prvi==null){
			nova.veza = prvi;
			prvi = nova;
		}
		else{
			Ucionica temp = prvi;
			while(temp.veza != null){
				temp = temp.veza;
			}
			temp.veza = nova;
			nova.veza = null;
		}
		ukUcionica++;
	}
	
	//Nalazi ucionicu pod rednim brojem. Pocetak od 0.
	public Ucionica nadjiUc(int br){
		if(prvi==null || br>ukUcionica)
			return null;
		else{
			Ucionica temp = prvi;
			int count = 0;
			while(temp != null && count<br){
				temp = temp.veza;
				count++;
			}
			return temp;
		}
	}
	
	//Sortirano
	public boolean dodajOpremu(String tip, String opis, int br){
		Ucionica temp = nadjiUc(br);
		if(temp!=null){
			Oprema nova = new Oprema(tip, opis);
			if(temp.prvaOprema==null || temp.prvaOprema.tip.compareTo(tip)>0){
				nova.veza = temp.prvaOprema;
				temp.prvaOprema = nova;
			}
			else{
				Oprema tek = temp.prvaOprema;
				while(tek.veza!=null && tek.veza.tip.compareTo(tip)<0){
					tek = tek.veza;
				}
				nova.veza = tek.veza;
				tek.veza = nova;
				
			}
			return true;
		}
		return false;
	}
	
	public int prebrojTip(String tip){
		if(prvi == null)
			return 0;
		else{
			Ucionica tekUc = prvi;
			int count = 0;
			while(tekUc!=null){
				Oprema tekOp = tekUc.prvaOprema;
				while(tekOp!=null){
					if(tekOp.tip.equals(tip))
						count++;
					tekOp = tekOp.veza;
				}
				tekUc = tekUc.veza;
			}
			return count;
		}
	}
	
	public Ucionica nadjiNajprazniju(){
		if(prvi!=null){
			Ucionica tekUc = prvi;
			Ucionica trazena = null;
			int count = 0, min = Integer.MAX_VALUE;
			while(tekUc!=null){
				Oprema tekOp = tekUc.prvaOprema;
				while(tekOp!=null){
					count++;
					tekOp = tekOp.veza;
				}
				if(count<min){
					min = count;
					trazena = tekUc;
				}
				count = 0;
				tekUc = tekUc.veza;
			}
			return trazena;
		}
		return null;
	}
	
	public boolean dodajuNajprazniju(String tip, String opis){
		Ucionica temp = nadjiNajprazniju();
		if(temp!=null){
			Oprema nova = new Oprema(tip, opis);
			if(temp.prvaOprema==null || temp.prvaOprema.tip.compareTo(tip)>0){
				nova.veza = temp.prvaOprema;
				temp.prvaOprema = nova;
			}
			else{
				Oprema tek = temp.prvaOprema;
				while(tek.veza!=null && tek.veza.tip.compareTo(tip)<0){
					tek = tek.veza;
				}
				nova.veza = tek.veza;
				tek.veza = nova;
				
			}
			return true;
		}
		return false;
	}
	
	public void izbaciIzZadUc(String tip, int br){
		Ucionica zad = nadjiUc(br);
		if(zad!=null){
			if(zad.prvaOprema!=null && zad.prvaOprema.tip.equals(tip)){
				zad.prvaOprema = zad.prvaOprema.veza;
			}
			else{
				Oprema tek = zad.prvaOprema;
				Oprema preth = null;
				while(tek!=null && !tek.tip.equals(tip)){
					preth = tek;
					tek = tek.veza;
				}
				if(tek!=null){
					preth.veza = preth.veza.veza;
				}
			}
		}
	}
	
	public void izbaciIzZadUcTipiOpis(String tip, String opis, int br){
		Ucionica zad = nadjiUc(br);
		if(zad!=null){
			if(zad.prvaOprema!=null && zad.prvaOprema.tip.equals(tip) && zad.prvaOprema.opis.equals(opis)){
				zad.prvaOprema = zad.prvaOprema.veza;
			}
			else{
				Oprema tek = zad.prvaOprema;
				Oprema preth = null;
				while(tek!=null && !(tek.tip.equals(tip) && tek.opis.equals(opis))){
					preth = tek;
					tek = tek.veza;
				}
				if(tek!=null){
					preth.veza = preth.veza.veza;
				}
			}
		}
	}
	
	public void izbaciSve(String tip, int br){
		Ucionica zad = nadjiUc(br);
		while(zad.prvaOprema!=null && zad.prvaOprema.tip.equals(tip)){
			zad.prvaOprema = zad.prvaOprema.veza;
		}
		Oprema tek = zad.prvaOprema;
		Oprema prethodni = null;
		while(tek!=null){
			prethodni = tek;
			if(tek.tip.equals(tip)){
				prethodni.veza = prethodni.veza.veza;
			}
			tek = tek.veza;
		}
		
	}
	
	public void prebaciOpremu(Oprema op, Ucionica uc){
		if(uc==null){
			if(uc.prvaOprema==null || uc.prvaOprema.tip.compareTo(op.tip)>0){
				op.veza = uc.prvaOprema;
				uc.prvaOprema = op;
			}
		}
		else{
			Oprema preth = uc.prvaOprema;
			while(preth.veza!=null && preth.veza.tip.compareTo(op.tip)<0){
				preth = preth.veza;
			}
			op.veza = preth.veza;
			preth.veza = op;
		}
	}
	
	public boolean prebaci(String tip){
		if(prvi==null || ukUcionica <= 1)
			return false;
		else{
			Ucionica tekUc = prvi;
			Ucionica trazenaMin = null, trazenaMax = null;
			int count = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			while(tekUc!=null){
				Oprema tekOp = tekUc.prvaOprema;
				while(tekOp!=null){
					if(tekOp.tip.equals(tip)){
						count++;
					}
					tekOp = tekOp.veza;
				}
				if(count<min){
					min = count;
					trazenaMin = tekUc;
				}
				if(count>max){
					max = count;
					trazenaMax = tekUc;
				}
				count = 0;
				tekUc = tekUc.veza;
			}
			
			
			while(trazenaMax.prvaOprema!=null && trazenaMax.prvaOprema.tip.equals(tip)){
				prebaciOpremu(trazenaMax.prvaOprema, trazenaMin);
				trazenaMax.prvaOprema = trazenaMax.prvaOprema.veza;
			}
			if(trazenaMax!=null){
				Oprema tekOp = trazenaMax.prvaOprema;
				Oprema preth = null;
				while(tekOp!=null){
					if(tekOp.tip.equals(tip)){
						prebaciOpremu(tekOp, trazenaMin);
						preth.veza = preth.veza.veza;
					}
					preth = tekOp;
					tekOp = tekOp.veza;
				}
			}
			return true;
		}
	}
	
}
