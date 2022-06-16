package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// TODO: Implementasikan hal-hal yang diperlukan
public class DaftarPeminjamPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel, panelForText;
    JLabel titleLabel, imgLabel, lblBuku, detailBukuLabel;
    JButton lihatBtn, btnKembali;
    Border border;
    ImageIcon icon, setIcon;
    JComboBox<String> pilihBukuComboBox;

    public DaftarPeminjamPanel(HomeGUI main) {
        super(main);
        // set layout pada panel DaftarPeminjamPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        border = BorderFactory.createLineBorder(darkPurple, 2);
        setBorder(border);
        setBackground(darkerPurple);

        // membuat panel utama
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.setBackground(darkerPurple);

        // membuat panel bawah
        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(darkerPurple);

        // membuat gambar
        icon = new ImageIcon("./img/VIR.gif");
        setIcon = new ImageIcon(icon.getImage().getScaledInstance(200, 176, Image.SCALE_DEFAULT));
        imgLabel = new JLabel(setIcon);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat dan mengatur label untuk judul
        titleLabel = new JLabel();
        titleLabel.setText("Lihat Daftar Peminjam");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        lblBuku = new JLabel("Pilih Buku");
        lblBuku.setFont(fontGeneral);
        lblBuku.setForeground(Color.white);
        lblBuku.setAlignmentX(Component.CENTER_ALIGNMENT);

        pilihBukuComboBox = new JComboBox<>();
        modifyComboBox(pilihBukuComboBox);

        detailBukuLabel = new JLabel();
        detailBukuLabel.setFont(fontGeneral);
        detailBukuLabel.setForeground(Color.white);
        detailBukuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelForText = new JPanel();
        panelForText.setLayout(new GridLayout(1, 3));
        panelForText.setBackground(darkerPurple);
        panelForText.add(new JLabel(""));
        panelForText.add(detailBukuLabel);
        panelForText.add(new JLabel(""));

        lihatBtn = new JButton("Lihat");
        modifyButton(lihatBtn);

        btnKembali = new JButton("Kembali");

        // Menambahkan komponen ke panel
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(imgLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(lblBuku);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(pilihBukuComboBox);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(panelForText);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(lihatBtn);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        super.modifyAndSettingButton(btnKembali, "staf", innerPanel);
        btnKembali.setMaximumSize(new Dimension(200, 20));
        innerPanel.add(Box.createVerticalGlue());

        mainPanel.add(innerPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        // Menambahkan action listener
        lihatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textDropdown;
                String namaBuku;
                String penulisBuku;
                if (pilihBukuComboBox.getSelectedItem() != null) {
                    textDropdown = pilihBukuComboBox.getSelectedItem().toString();
                    namaBuku = textDropdown.substring(0, textDropdown.indexOf(" oleh "));
                    penulisBuku = textDropdown.substring(textDropdown.indexOf(" oleh ") + 6, textDropdown.length());
                } else {
                    textDropdown = "";
                    namaBuku = "";
                    penulisBuku = "";
                }

                if (namaBuku.isEmpty() || penulisBuku.isEmpty()) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Silahkan Memilih Buku", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Buku buku = SistakaNG.findBuku(namaBuku, penulisBuku);
                if (buku == null) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Buku Tidak Ditemukan", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String daftarPeminjamText = SistakaNG.daftarPeminjam(buku);
                detailBukuLabel.setText(daftarPeminjamText);
            }
        });
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
        detailBukuLabel.setText("");
        pilihBukuComboBox.removeAllItems();
        for (Buku buku : SistakaNG.getDaftarBuku()) {
            String bukuString = buku.getJudul() + " oleh " + buku.getPenulis();
            pilihBukuComboBox.addItem(bukuString);
        }
    }

}
