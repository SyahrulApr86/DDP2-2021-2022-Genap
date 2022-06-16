import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class CurrencyConversionFrame extends JFrame {
    JLabel labelTitle, labelFrom, labelTo;
    JButton convertButton, exitButton;
    String[] currency = {"Rupiah", "Euro", "US Dollar"};
    HashMap<String, Integer> kurs = new HashMap<String, Integer>();

    public CurrencyConversionFrame() {
        kurs.put("Rupiah", 1);
        kurs.put("Euro", 15000);
        kurs.put("US Dollar", 14000);

        // TO DO: Menentukan layout dan size yang ingin digunakan
        // Menentukan layout dan size yang ingin digunakan
        setSize(400, 250);
        setLayout(new FlowLayout());


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Menghentikan program saat frame di tutup
        setTitle("Currency Converter"); // Menentukan judul frame

        // TO DO: Membuat Panel sesuai kebutuhan
        JPanel mainPanel = new JPanel(); // panel utama
        JPanel panelTitle = new JPanel(new FlowLayout(FlowLayout.CENTER)); // panel judul
        JPanel panelFrom = new JPanel(new  FlowLayout(FlowLayout.CENTER)); // panel untuk menampilkan currency asal
        JPanel panelTo = new JPanel(new FlowLayout(FlowLayout.CENTER)); // panel untuk menampilkan currency tujuan
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER)); // panel untuk menampilkan button

        // mengatur layout panel utama
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        labelTitle = new JLabel("Welcome to Currency Converter");

        // TO DO: Membuat dropdown menu
        JComboBox<String> comboFrom = new JComboBox<String>(currency); // dropdown menu untuk currency asal
        JComboBox<String> comboTo = new JComboBox<String>(currency); // dropdown menu untuk currency tujuan

        labelFrom = new JLabel("From");
        labelTo = new JLabel("To");
                
        // TO DO: Membuat textfield
        JTextField textFieldFrom = new JTextField(20);
        JTextField textFieldTo = new JTextField(20);
        textFieldTo.setEditable(false);

        // TO DO: Membuat button
        convertButton = new JButton("Convert");
        exitButton = new JButton("Exit");

        // TO DO: Masukkan widget ke panel yang tepat
        panelTitle.setPreferredSize(new Dimension(400, 20)); // mengatur ukuran panel judul
        panelTitle.add(labelTitle);

        panelFrom.setPreferredSize(new Dimension(400, 52)); // mengatur ukuran panel currency asal
        panelFrom.add(labelFrom);
        panelFrom.add(comboFrom);
        panelFrom.add(textFieldFrom);

        panelTo.setPreferredSize(new Dimension(400, 52)); // mengatur ukuran panel currency tujuan
        panelTo.add(labelTo);
        panelTo.add(comboTo);
        panelTo.add(textFieldTo);

        panelButton.add(convertButton);
        panelButton.add(exitButton);

        // TO DO: Masukkan panel ke dalam frame

        // memasukkan panel ke panel utama
        mainPanel.add(panelTitle);
        mainPanel.add(panelFrom);
        mainPanel.add(panelTo);
        mainPanel.add(panelButton);

        // memasukkan panel utama ke frame
        add(mainPanel);

        setVisible(true);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // TO DO: implementasikan pemanggilan method ConvertCurrency
                String originalValue = textFieldFrom.getText(); // nilai awal
                String fromCurrency = (String) comboFrom.getSelectedItem(); // currency asal
                String toCurrency = (String) comboTo.getSelectedItem(); // currency tujuan

                String result = ConvertCurrency(originalValue, fromCurrency, toCurrency); // hasil konversi
                textFieldTo.setText(result);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // TO DO: hentikan eksekusi program
                System.exit(0);
            }
        });
    }
    
    // Method converter
    public String ConvertCurrency(String originalValue, String tipeCurrencyFrom, String tipeCurrencyTo) {
        double value = Double.valueOf(originalValue);
        double fromValue = value * kurs.get(tipeCurrencyFrom);
        double convertedValue = fromValue / kurs.get(tipeCurrencyTo);
        return String.format("%.2f", convertedValue);
    }
    
}
