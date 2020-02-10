package com.example.javafx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import javax.swing.JFrame;


@SpringBootApplication
public class JavafxApplication extends JFrame {

	public JavafxApplication() {
		new Window();
	}

	public static void main(String[] args) {

		var ctx = new SpringApplicationBuilder(JavafxApplication.class).run(args);

//		EventQueue.invokeLater(() -> {
//
//			var ex = ctx.getBean(JavafxApplication.class);
//			ex.setVisible(true);
//		});
	}
}
