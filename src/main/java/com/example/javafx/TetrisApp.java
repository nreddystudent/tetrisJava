package com.example.javafx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import javax.swing.JFrame;


@SpringBootApplication
public class TetrisApp extends JFrame {

	public TetrisApp() {
		new Window();
	}
	public static void main(String[] args) {

		var ctx = new SpringApplicationBuilder(TetrisApp.class).run(args);
	}
}
