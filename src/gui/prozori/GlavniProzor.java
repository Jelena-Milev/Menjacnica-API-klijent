package gui.prozori;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import domenske_klase.Drzava;
import gui.kontroler.GUIKontroler;

import javax.swing.border.LineBorder;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class GlavniProzor extends JFrame {

	private JPanel contentPane;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JComboBox comboBoxFrom;
	private JComboBox comboBoxTo;
	private JLabel lblIznoFrom;
	private JLabel lblIznosTo;
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JButton btnKonvertuj;
	private GUIKontroler guiKontr;
	private JButton btnZameni;
	/**
	 * Create the frame.
	 */
	public GlavniProzor(GUIKontroler guiKontr) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GlavniProzor.class.getResource("/money-icon.png")));
		setResizable(false);
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblFrom());
		contentPane.add(getLblTo());
		contentPane.add(getComboBoxFrom());
		contentPane.add(getComboBoxTo());
		contentPane.add(getLblIznoFrom());
		contentPane.add(getLblIznosTo());
		contentPane.add(getTextFieldFrom());
		contentPane.add(getTextFieldTo());
		contentPane.add(getBtnKonvertuj());
		contentPane.add(getBtnZameni());
		this.guiKontr=guiKontr;
		guiKontr.popuniComboBox(comboBoxFrom);
		guiKontr.popuniComboBox(comboBoxTo);
	}

	private JLabel getLblFrom() {
		if (lblFrom == null) {
			lblFrom = new JLabel("Iz valute zemlje:");
			lblFrom.setBounds(32, 68, 134, 15);
		}
		return lblFrom;
	}

	private JLabel getLblTo() {
		if (lblTo == null) {
			lblTo = new JLabel("U valute zemlje:");
			lblTo.setBounds(251, 68, 134, 15);
		}
		return lblTo;
	}

	private JComboBox getComboBoxFrom() {
		if (comboBoxFrom == null) {
			comboBoxFrom=new JComboBox<>();
			comboBoxFrom.setBounds(32, 95, 166, 24);
		}
		return comboBoxFrom;
	}

	private JComboBox getComboBoxTo() {
		if (comboBoxTo == null) {
			comboBoxTo=new JComboBox<>();
			comboBoxTo.setBounds(246, 95, 171, 24);
		}
		return comboBoxTo;
	}

	private JLabel getLblIznoFrom() {
		if (lblIznoFrom == null) {
			lblIznoFrom = new JLabel("Iznos:");
			lblIznoFrom.setBounds(57, 155, 114, 15);
		}
		return lblIznoFrom;
	}

	private JLabel getLblIznosTo() {
		if (lblIznosTo == null) {
			lblIznosTo = new JLabel("Iznos:");
			lblIznosTo.setBounds(269, 155, 110, 15);
		}
		return lblIznosTo;
	}

	private JTextField getTextFieldFrom() {
		if (textFieldFrom == null) {
			textFieldFrom = new JTextField();
			textFieldFrom.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					
				}

				public void removeUpdate(DocumentEvent e) {
					if(textFieldFrom.getText().isEmpty())
						btnKonvertuj.setEnabled(false);
				}

				public void insertUpdate(DocumentEvent e) {
					btnKonvertuj.setEnabled(true);
				}
			});
			textFieldFrom.setBounds(57, 183, 114, 19);
			textFieldFrom.setColumns(10);
		}
		return textFieldFrom;
	}

	private JTextField getTextFieldTo() {
		if (textFieldTo == null) {
			textFieldTo = new JTextField();
			textFieldTo.setBounds(265, 183, 114, 19);
			textFieldTo.setColumns(10);
		}
		return textFieldTo;
	}

	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.setEnabled(false);
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//					ili da daje Object a da Menjacnica konvertuje u Drzava?
					Drzava from=(Drzava)comboBoxFrom.getSelectedItem();
					Drzava to=(Drzava)comboBoxTo.getSelectedItem();
					String iznosFrom = textFieldFrom.getText();					
					try {
//						Double kurs=
						textFieldTo.setText("" + guiKontr.getMenjacnica().konvertuj(from, to, iznosFrom));
					}catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Morate uneti brojcanu vrednost.",
								"Greska", JOptionPane.ERROR_MESSAGE);
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ne postoje podaci o konverziji izmedju datih valuta.",
								"Greska", JOptionPane.ERROR_MESSAGE);
					}

				}
			});

			btnKonvertuj.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnKonvertuj.setBackground(Color.WHITE);
			btnKonvertuj.setBounds(157, 232, 117, 25);
		}
		return btnKonvertuj;
	}
	private JButton getBtnZameni() {
		if (btnZameni == null) {
			btnZameni = new JButton("");
			btnZameni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int from=comboBoxFrom.getSelectedIndex();
					comboBoxFrom.setSelectedIndex(comboBoxTo.getSelectedIndex());
					comboBoxTo.setSelectedIndex(from);
//					ne radi, zasto?
//					Object from=comboBoxFrom.getSelectedItem();
//					comboBoxFrom.setSelectedItem(comboBoxTo.getSelectedItem());
//					comboBoxTo.setSelectedItem(from);
				}
			});
			btnZameni.setContentAreaFilled(false);
			btnZameni.setIcon(new ImageIcon(GlavniProzor.class.getResource("/switch/switch.png")));
			btnZameni.setBounds(210, 95, 24, 24);
		}
		return btnZameni;
	}
}
