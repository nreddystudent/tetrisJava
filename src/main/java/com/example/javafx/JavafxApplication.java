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

		initUI();
	}

	private void initUI() {

		var quitButton = new JButton("Quit");

		quitButton.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		createLayout(quitButton);

		setTitle("Quit button");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent... arg) {

		var pane = getContentPane();
		var gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addComponent(arg[0])
		);

		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(arg[0])
		);
	}

	public static void main(String[] args) {

		var ctx = new SpringApplicationBuilder(JavafxApplication.class)
				.headless(false).run(args);

		EventQueue.invokeLater(() -> {

			var ex = ctx.getBean(JavafxApplication.class);
			ex.setVisible(true);
		});
	}
}
