package org.iop.code_test_vaadin;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Theme("dataview-theme")
public class CodeTestVaadinApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(CodeTestVaadinApplication.class, args);
	}

}
