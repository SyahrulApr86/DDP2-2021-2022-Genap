package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahBukuPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel;
    JLabel titleLabel, imgLabel, lblJudul,lblPenerbit, lblPenulis, lblKategori, lblStok;
    JButton btnTambahBuku, btnKembali;
    Border border;
    ImageIcon icon, setIcon;
    JTextField fieldJudul, fieldPenulis, fieldPenerbit, fieldStok;
    JComboBox<String> kategoriComboBox;

    public TambahBukuPanel(HomeGUI main) {
        super(main);
        // set layout pada panel TambaBukuPanel
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
        titleLabel.setText("Tambah Buku");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        lblJudul = new JLabel("Judul");
        lblJudul.setFont(fontGeneral);
        lblJudul.setForeground(Color.white);
        lblJudul.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldJudul = new JTextField();
        modifyField(fieldJudul);

        lblPenulis = new JLabel("Penulis");
        lblPenulis.setFont(fontGeneral);
        lblPenulis.setForeground(Color.white);
        lblPenulis.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldPenulis = new JTextField();
        modifyField(fieldPenulis);

        lblPenerbit = new JLabel("Penerbit");
        lblPenerbit.setFont(fontGeneral);
        lblPenerbit.setForeground(Color.white);
        lblPenerbit.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldPenerbit = new JTextField();
        modifyField(fieldPenerbit);

        lblKategori = new JLabel("Kategori");
        lblKategori.setFont(fontGeneral);
        lblKategori.setForeground(Color.white);
        lblKategori.setAlignmentX(Component.CENTER_ALIGNMENT);

        kategoriComboBox = new JComboBox<>();
        modifyComboBox(kategoriComboBox);

        lblStok = new JLabel("Stok");
        lblStok.setFont(fontGeneral);
        lblStok.setForeground(Color.white);
        lblStok.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldStok = new JTextField();
        modifyField(fieldStok);

        btnTambahBuku = new JButton("Tambah");
        modifyButton(btnTambahBuku);

        btnKembali = new JButton("Kembali");

        // Menambahkan komponen ke panel
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(imgLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(lblJudul);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(fieldJudul);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(lblPenulis);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(fieldPenulis);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(lblPenerbit);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(fieldPenerbit);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(lblKategori);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(kategoriComboBox);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(lblStok);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(fieldStok);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(btnTambahBuku);
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
        btnTambahBuku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String judul = fieldJudul.getText();
                String penulis = fieldPenulis.getText();
                String penerbit = fieldPenerbit.getText();
                String strStok = fieldStok.getText();
                String kategori = (String) kategoriComboBox.getSelectedItem();


                if (judul.isEmpty() && penulis.isEmpty() && penerbit.isEmpty() && kategori == null && strStok.isEmpty()) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Nama, Penulis, Penerbit, dan Stok tidak boleh kosong", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int stok;
                try {
                    stok = Integer.parseInt(strStok);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Stok harus berupa angka", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (stok == 0) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Stok tidak boleh 0", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Buku buku = SistakaNG.findBuku(judul, penulis);
                if (buku != null) {
                    JOptionPane.showMessageDialog(main.getFrame(), buku.getJudul() + " sudah pernah dibuat", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                buku = SistakaNG.addBuku(judul, penulis, penerbit, kategori, stok);
                if (buku == null) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Buku tidak berhasil ditambahkan", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(main.getFrame(), "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                fieldJudul.setText("");
                fieldPenulis.setText("");
                fieldPenerbit.setText("");
                fieldStok.setText("");
            }
        });

    }

    @Override
    public void refresh() {
        kategoriComboBox.removeAllItems();
        for (Kategori kategori : SistakaNG.getDaftarKategori()) {
            kategoriComboBox.addItem(kategori.getNama());
        }
    }

}
