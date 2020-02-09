package com.example.javafx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.builder.SpringApplicationBuilder;
		import javax.swing.GroupLayout;
		import javax.swing.JButton;
		import javax.swing.JComponent;
		import javax.swing.JFrame;
		import java.awt.EventQueue;
		import java.awt.event.ActionEvent;


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
