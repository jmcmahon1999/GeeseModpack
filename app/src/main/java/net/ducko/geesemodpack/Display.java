package net.ducko.geesemodpack;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class Display extends JFrame {

	JProgressBar progressBar;
	JPanel p;
	JLabel contentLabel;
	JLabel selectLabel;
	JButton fileLabel;
	JButton fileButton;
	JLabel progressLabel;
	JButton contentButton;
	JButton submit;
	SwingWorker worker;
	
	public Display() {
		super("GeeseModpack");
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		p = new JPanel();
		progressLabel = new JLabel("Initialising");
		progressBar = new JProgressBar();
		p.setLayout(null);
		setSize(300,180);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("icon.png");
		String path = url.getPath();
		ImageIcon img = new ImageIcon(path);
		setIconImage(img.getImage());
	}
	
	public void promptWindow(String defaultPath) {
		p.setVisible(false);
		p.removeAll();
		this.remove(p);
		
		contentLabel = new JLabel("<html>"
				+ " Installing the GeeseModpack "
				+ "<br>WARNING: Any old mods will be deleted. "
				+ "</html>");
		contentLabel.setVerticalAlignment(JLabel.TOP);
		contentLabel.setVerticalTextPosition(JLabel.TOP);
		contentLabel.setBounds(5, 5, 290, 60);
		
		selectLabel = new JLabel("Select Minecraft Directory:");
		selectLabel.setBounds(5, 60, 200, 20);
		
		fileLabel = new JButton(defaultPath);
		fileLabel.setBounds(5, 80, 200, 20);
		fileLabel.setBackground(Color.WHITE);
		fileLabel.setFocusPainted(false);
		fileLabel.setHorizontalAlignment(SwingConstants.LEFT);
		fileLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		fileLabel.setMargin(new Insets(0,2,0,0));
		
		fileButton = new JButton("Browse");
		fileButton.setMargin(new Insets(0,0,0,0));
		fileButton.setBounds(210, 80, 70, 20);
		
		submit = new JButton("Install");
		submit.setBounds(110, 110, 70, 20);
		submit.addActionListener(new SubmitAction());
		
		p.add(contentLabel);
		p.add(submit);
		p.add(selectLabel);
		p.add(fileLabel);
		p.add(fileButton);
		add(p);
		p.setVisible(true);
		setVisible(true);
	}
	
	public void executeWindow() {
		p.setVisible(false);
		p.removeAll();
		this.remove(p);
		progressBar.setValue(0);
		progressBar.setStringPainted(false);
		progressBar.setBounds(10, 110, 262, 20);
		
		contentButton = new JButton();
		contentButton.setText("<HTML>Visit <FONT color=\"#000099\"><U>ducko.net</U></FONT>"
	        + " to see the mod authors and support them.</HTML>");
		contentButton.setBorderPainted(false);
		contentButton.setOpaque(false);
		contentButton.setContentAreaFilled(false);
		contentButton.setBounds(10, 10, 262, 70);
		contentButton.setFocusPainted(false);
		contentButton.addActionListener(new OpenUrlAction("http://localhost:3000"));
	    
		progressLabel.setBounds(10, 90, 262, 20);
		p.add(progressLabel);
		p.add(progressBar);
		p.add(contentButton);
		add(p);
		p.setVisible(true);
		setVisible(true);
	}
	
	private void startWorker(String path) {
		TaskWorker worker = new TaskWorker(this, path);
		worker.execute();
	}
	
	class OpenUrlAction implements ActionListener {

		String url;
		
		public OpenUrlAction(String url) {
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (url != null) {
				URI uri;
				try {
					uri = new URI(url);
					if (Desktop.isDesktopSupported()) {
					      try {
					        Desktop.getDesktop().browse(uri);
					      } catch (IOException err) { err.printStackTrace(); }
					    } else { System.out.println("No browser.");}
				} catch (URISyntaxException err) {
					err.printStackTrace();
				}
			}
		}
		
	}
	
	class SubmitAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String minecraftPath = fileLabel.getText();
			executeWindow();
			startWorker(minecraftPath);
		}
	}
	
}
