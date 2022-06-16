package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class StafHomePanel extends SistakaPanel {
    // Field
    JPanel mainPanel, topPanel, bottomPanel;
    JLabel titleLabel, imgLabel;
    JButton btnTambahMahasiswa, btnTambahDosen, tambahKategori, btnTambahBuku, btnHapusBuku, btnPeringkatAnggota, btnDetailAnggota, btnDaftarPeminjamananBuku, btnLogout;
    Border border;
    ImageIcon icon, setIcon;

    public StafHomePanel(HomeGUI main) {
        super(main);
        // set layout pada panel StaffHomePanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        border = BorderFactory.createLineBorder(darkPurple, 2);
        setBorder(border);
        setBackground(darkerPurple);

        // membuat panel utama
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.setBackground(darkerPurple);

        // membuat panel atas
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 1));
        topPanel.setBackground(darkerPurple);

        // membuat panel bawah
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(darkerPurple);

        // membuat gambar
        icon = new ImageIcon("./img/VIR.gif");
        setIcon = new ImageIcon(icon.getImage().getScaledInstance(200, 176, Image.SCALE_DEFAULT));
        imgLabel = new JLabel(setIcon);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(imgLabel);

        // membuat dan mengatur label untuk judul
        titleLabel = new JLabel();
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(titleLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // membuat komponen Button
        btnTambahMahasiswa = new JButton("Tambah Mahasiswa");
        btnTambahDosen = new JButton("Tambah Dosen");
        tambahKategori = new JButton("Tambah Kategori");
        btnTambahBuku = new JButton("Tambah Buku");
        btnHapusBuku = new JButton("Hapus Buku");
        btnPeringkatAnggota = new JButton("3 Peringkat Pertama");
        btnDetailAnggota = new JButton("Detail Anggota");
        btnDaftarPeminjamananBuku = new JButton("Daftar Peminjaman Buku");
        btnLogout = new JButton("Logout");

        // mengatur dan menghias Button
        modifyAndSettingButton(btnTambahMahasiswa, "tambahMhs", bottomPanel);
        modifyAndSettingButton(btnTambahDosen, "tambahDosen", bottomPanel);
        modifyAndSettingButton(tambahKategori, "tambahKategori", bottomPanel);
        modifyAndSettingButton(btnTambahBuku, "tambahBuku", bottomPanel);
        modifyAndSettingButton(btnHapusBuku, "hapusBuku", bottomPanel);
        modifyAndSettingButton(btnPeringkatAnggota, "peringkat", bottomPanel);
        modifyAndSettingButton(btnDetailAnggota, "detailAnggota", bottomPanel);
        modifyAndSettingButton(btnDaftarPeminjamananBuku, "daftarPeminjam", bottomPanel);
        modifyAndSettingButton(btnLogout, "logout", bottomPanel);

        // menambahkan komponen ke panel utama
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        add(mainPanel);
    }

    @Override
    public void refresh() {
        String title = String.format("Selamat datang kembali %s!", SistakaNG.getPenggunaLoggedIn().getNama());
        titleLabel.setText(title);
    }


}
