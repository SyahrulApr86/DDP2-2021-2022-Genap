package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
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
public class TambahKategoriPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel;
    JLabel titleLabel, imgLabel, namaKategoriLabel, poinLabel;
    JButton btnTambahKtgr, btnKembali;
    Border border;
    ImageIcon icon, setIcon;
    JTextField namaTextField, poinTextField;

    public TambahKategoriPanel(HomeGUI main) {
        super(main);
        // set layout pada panel TambaKategoriPanel
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
        titleLabel.setText("Tambah Kategori");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        namaKategoriLabel = new JLabel("Nama");
        namaKategoriLabel.setFont(fontGeneral);
        namaKategoriLabel.setForeground(Color.white);
        namaKategoriLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        namaTextField = new JTextField();
        modifyField(namaTextField);

        poinLabel = new JLabel("Poin");
        poinLabel.setFont(fontGeneral);
        poinLabel.setForeground(Color.white);
        poinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        poinTextField = new JTextField();
        modifyField(poinTextField);


        btnTambahKtgr = new JButton("Tambah");
        modifyButton(btnTambahKtgr);

        btnKembali = new JButton("Kembali");

        // Menambahkan komponen ke panel
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(imgLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(namaKategoriLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(namaTextField);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(poinLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(poinTextField);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(btnTambahKtgr);
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
        btnTambahKtgr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = namaTextField.getText();
                String strPoin = poinTextField.getText();
                if (nama.isEmpty() && strPoin.isEmpty()) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Nama dan Poin tidak boleh kosong", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int poin;
                try {
                    poin = Integer.parseInt(strPoin);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Poin harus berupa angka", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Kategori kategori = SistakaNG.findKategori(nama);
                if (kategori != null) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Kategori " + kategori.getNama() + " sudah pernah ditambahkan!", "Notice", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                kategori = SistakaNG.addKategori(nama, poin);
                if (kategori == null) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Kategori " + nama + " gagal ditambahkan", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(main.getFrame(), "Kategori " + nama + " dengan poin " + poin  + " berhasil ditambahkan", "Sukses!", JOptionPane.INFORMATION_MESSAGE);
                namaTextField.setText("");
                poinTextField.setText("");
            }
        });
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
    }
}
