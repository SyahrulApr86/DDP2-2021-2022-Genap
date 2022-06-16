package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Mahasiswa;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahMahasiswaPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel;
    JLabel titleLabel, imgLabel, namaLabel, tanggalLahirLabel, programStudiLabel, angkatanLabel;
    JButton btnTambahMhs, btnKembali;
    Border border;
    ImageIcon icon, setIcon;
    JTextField fieldNama, fieldTglLahir, fieldAngkatan;
    JComboBox<String> programStudiComboBox;

    public TambahMahasiswaPanel(HomeGUI main) {
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
        titleLabel.setText("Tambah Mahasiswa");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        namaLabel = new JLabel("Nama");
        namaLabel.setFont(fontGeneral);
        namaLabel.setForeground(Color.white);
        namaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldNama = new JTextField();
        modifyField(fieldNama);

        tanggalLahirLabel = new JLabel("Tanggal Lahir (DD/MM/YYYY)");
        tanggalLahirLabel.setFont(fontGeneral);
        tanggalLahirLabel.setForeground(Color.white);
        tanggalLahirLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldTglLahir = new JTextField();
        modifyField(fieldTglLahir);

        programStudiLabel = new JLabel("Program Studi");
        programStudiLabel.setFont(fontGeneral);
        programStudiLabel.setForeground(Color.white);
        programStudiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] listProgramStudi = { "SIK", "SSI", "MIK", "MTI", "DIK" };
        programStudiComboBox = new JComboBox<>(listProgramStudi);
        modifyComboBox(programStudiComboBox);

        angkatanLabel = new JLabel("Angkatan");
        angkatanLabel.setFont(fontGeneral);
        angkatanLabel.setForeground(Color.white);
        angkatanLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        fieldAngkatan = new JTextField();
        modifyField(fieldAngkatan);

        btnTambahMhs = new JButton("Tambah");
        modifyButton(btnTambahMhs);

        btnKembali = new JButton("Kembali");

        // Menambahkan komponen ke panel
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(imgLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(namaLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(fieldNama);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(tanggalLahirLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(fieldTglLahir);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(programStudiLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(programStudiComboBox);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(angkatanLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(fieldAngkatan);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(btnTambahMhs);
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
        btnTambahMhs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = fieldNama.getText();
                String tanggalLahir = fieldTglLahir.getText();
                String programStudi = (String) programStudiComboBox.getSelectedItem();
                String angkatan = fieldAngkatan.getText();

                if (programStudi == null || nama.isEmpty() || tanggalLahir.isEmpty() || programStudi.isEmpty() || angkatan.isEmpty()) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Data tidak boleh kosong", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Mahasiswa mhs = SistakaNG.addMahasiswa(nama, tanggalLahir, programStudi, angkatan);
                if (mhs == null) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Tidak dapat menambahkan anggota silahkan periksa kembali input anda!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(main.getFrame(), "Berhasil menambahkan mahasiswa dengan ID " + mhs.getId() + "!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                fieldNama.setText("");
                fieldTglLahir.setText("");
                programStudiComboBox.setSelectedIndex(0);
                fieldAngkatan.setText("");
            }
        });

    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
    }

}
