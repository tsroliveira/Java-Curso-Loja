package com.java.curso.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//Quando a classe é um component ela é chamada pelo spring sempre que necessário
@Component
public class StringToDouble implements Converter<String, Double>{
	
	@Override
	public Double convert(String source) {
		source = source.trim();
		if(source.length() > 0 ) {
			source = source.replace(".", "").replace(",", ".");    //Primeiro replace remove os "." e o segundo converte a "," em ponto "." >> Exemplo: Inicial=1.000,99 >>PrimeiroReplace=1000,99 >>SegundoReplace=1000.99			
			return Double.parseDouble(source);
		}
		return 0.;
	}
}
