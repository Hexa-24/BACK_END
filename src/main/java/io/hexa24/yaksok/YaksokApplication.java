package io.hexa24.yaksok;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"io.hexa24.global", "io.hexa24.yaksok"})
public class YaksokApplication {

	public static void main(String[] args) {
		SpringApplication.run(YaksokApplication.class, args);
	}


	@Bean
		public ModelMapper modelMapper() {
    return new ModelMapper();
}

}
