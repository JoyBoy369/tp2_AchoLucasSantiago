package ar.edu.unju.fi.ejercicio3.constantes;

public enum Provincia {
	JUJUY((int) 811611, (int) 53219),
	SALTA((int) 1441351, (int) 155488),
	TUCUMAN((int) 1731820, (int) 22524),
	CATAMARCA((int) 429562, (int) 102602), 
	LARIOJA((int) 383865, (int) 89680),
	SANTIAGODELESTERO((int) 1060906, (int) 136351);

	private int CANTIDAD_POBLACION;
	private int SUPERFICIE;

	
	private Provincia(int cANTIDAD_POBLACION, int sUPERFICIE) {
		CANTIDAD_POBLACION = cANTIDAD_POBLACION;
		SUPERFICIE = sUPERFICIE;
	}

	public int getCANTIDAD_POBLACION() {
		return CANTIDAD_POBLACION;
	}

	public void setCANTIDAD_POBLACION(int cANTIDAD_POBLACION) {
		CANTIDAD_POBLACION = cANTIDAD_POBLACION;
	}

	public int getSUPERFICIE() {
		return SUPERFICIE;
	}

	public void setSUPERFICIE(int sUPERFICIE) {
		SUPERFICIE = sUPERFICIE;
	}

	public double densidadPoblacional() {
		return CANTIDAD_POBLACION / SUPERFICIE;
	}
}