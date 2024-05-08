package ar.edu.unju.fi.ejercicio6.main;

import ar.edu.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {

	public static void main(String[] args) {
		FelinoSalvaje felinoSalvaje = new FelinoSalvaje("Tanner", (byte) 20, 186f);
		Converter<FelinoSalvaje, FelinoDomestico> converter = x -> new FelinoDomestico(x.getNombre(), x.getEdad(),
				x.getPeso());
		if (Converter.isNotNull(felinoSalvaje)) {
			FelinoDomestico felinoDomestico = converter.convert(felinoSalvaje);
			converter.mostrarObjeto(felinoDomestico);
		} else {
			System.out.println("No hay felino");
		}
	}

}