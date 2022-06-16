package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Implementasikan hal-hal yang diperlukan
public class PengembalianPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel;
    JLabel titleLabel, imgLabel, bukuLabel, tanggalPengembalianLabel;
    JButton btnKembalikanBuku, btnKembali;
    Border border;
    ImageIcon icon, setIcon;
    JTextField tanggalPengembalianField;
    JComboBox<String> listBukuComboBox;

    public PengembalianPanel(HomeGUI main) {
        super(main);
        // set layout pada panel TambahMahasiswaPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        border = BorderFactory.createLineBorder(darkPurple, 2);
        setBorder(border);
        setBackground(darkerPurple);

        // membuat panel utama
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.setBackground(darkerPurple);

        // membuat panel dalam panel utama
        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(darkerPurple);

        // membuat gambar
        icon = new ImageIcon("./img/book.png");
        setIcon = new ImageIcon(icon.getImage().getScaledInstance(200, 176, Image.SCALE_DEFAULT));
        imgLabel = new JLabel(setIcon);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat dan mengatur label untuk judul
        titleLabel = new JLabel();
        titleLabel.setText("Pengembalian Buku");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        bukuLabel = new JLabel("Buku");
        bukuLabel.setFont(fontGeneral);
        bukuLabel.setForeground(Color.white);
        bukuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        listBukuComboBox = new JComboBox<>();
        modifyComboBox(listBukuComboBox);

        tanggalPengembalianLabel = new JLabel("Tanggal Pengembalian (DD/MM/YYYY)");
        tanggalPengembalianLabel.setFont(fontGeneral);
        tanggalPengembalianLabel.setForeground(Color.white);
        tanggalPengembalianLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tanggalPengembalianField = new JTextField();
        modifyField(tanggalPengembalianField);

        btnKembalikanBuku = new JButton("Kembalikan");
        modifyButton(btnKembalikanBuku);

        btnKembali = new JButton("Kembali");

        // Menambahkan komponen ke panel
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(imgLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(bukuLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(listBukuComboBox);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(tanggalPengembalianLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(tanggalPengembalianField);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(btnKembalikanBuku);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        super.modifyAndSettingButton(btnKembali, "anggota", innerPanel);
        btnKembali.setMaximumSize(new Dimension(200, 20));
        innerPanel.add(Box.createVerticalGlue());

        mainPanel.add(innerPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        // Menambahkan action listener
        btnKembalikanBuku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textDropdown, namaBuku, penulisBuku;
                String tanggalPengembalian = tanggalPengembalianField.getText();
                if (listBukuComboBox.getSelectedItem() != null) {
                    textDropdown = listBukuComboBox.getSelectedItem().toString();
                    namaBuku = textDropdown.substring(0, textDropdown.indexOf(" oleh "));
                    penulisBuku = textDropdown.substring(textDropdown.indexOf(" oleh ") + 6);
                } else {
                    textDropdown = "";
                    namaBuku = "";
                    penulisBuku = "";
                }

                if (textDropdown.isEmpty() || tanggalPengembalian.isEmpty()) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Harap isi buku dan tanggal pinjam", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Buku buku = SistakaNG.findBuku(namaBuku, penulisBuku);
                if (buku == null) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Buku Tidak Ditemukan", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (!isDateValid(tanggalPengembalian)) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Tanggal Yang Dimasukkan harus dalam format DD/MM/YYYY!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String hasil = SistakaNG.kembalikanBuku(buku, tanggalPengembalian);
                JOptionPane.showMessageDialog(main.getFrame(), hasil, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
        listBukuComboBox.removeAllItems();
        for (Buku buku : SistakaNG.getDaftarBuku()) {
            String bukuString = buku.getJudul() + " oleh " + buku.getPenulis();
            listBukuComboBox.addItem(bukuString);
        }
    }
}
