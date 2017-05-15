
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Initializer {
	private JFrame frame;
	private JPanel panel;
	private JLabel header, ipText, portText;
	private JTextField ipBox, portBox;
	private JComboBox<String> userType;
	private JButton button;
	private GridBagConstraints c;
	private Image image;
	public void initialize(String[] args) {
		//Creates the setup gui.
		frame = new JFrame("Fire Administration Tool");
		frame.setSize(350, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		panel = new JPanel(new GridBagLayout());
		// Adds a component container with a grid alignment.
		c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 0, 0);
		// The parameters of the grid boxes containing each component.
		ipBox = new JTextField("10.172.144.53");
		portBox = new JTextField("8080");
		ipBox.setEditable(true);
		portBox.setEditable(true);
		ipText = new JLabel("IPv4 Address");
		portText = new JLabel("Port Number");
		// Text boxes and  their corresponding headers.
		header = new JLabel();
		header.setPreferredSize(new Dimension(325, 100));
		header.setFont(header.getFont().deriveFont(20.0f));
		try {
			// Set every image.
			image = ImageIO.read(Initializer.class.getResource("Picture1.png"));
		} catch (IOException e) {
			System.out.println("An image didn't load!");
		}
		header.setIcon(new ImageIcon(image.getScaledInstance(325, 100, Image.SCALE_SMOOTH)));
		
		// Title Banner
		button = new JButton("Confirm");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//frame.dispose();
				if(userType.getSelectedItem().equals("Client"))
					remoteclient.ClientInitiator.main(new String[] {ipBox.getText(), portBox.getText()} );
				else
					remoteserver.ServerInitiator.main(new String[] {portBox.getText()} );
			}
		});
		// Confirm button.

		userType = new JComboBox<String>(new String[] { "Client", "Server" });
		userType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (userType.getSelectedItem().equals("Client"))
					ipBox.setEnabled(true);
				else
					ipBox.setEnabled(false);
			}
		});
		// Drop-down menu for user type (Client or Server).
		add(header, 0, 0);
		add(ipText, 0, 1);
		add(ipBox, 0, 2);
		add(portText, 0, 3);
		add(portBox, 0, 4);
		add(userType, 0, 5);
		add(button, 0, 6);
		frame.add(panel);
		frame.setVisible(true);
		// Puts everything together and displays it.
	}

	public void add(Component comp, int x, int y) {
		// Puts a component into the given panel element.
		c.gridx = x;
		c.gridy = y;
		panel.add(comp, c);
	}

}
