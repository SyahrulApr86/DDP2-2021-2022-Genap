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
public class HapusBukuPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel;
    JLabel titleLabel, imgLabel, lblBuku;
    JButton hapusBukuButton, kembaliButton;
    Border border;
    ImageIcon icon, setIcon;
    JComboBox<String> bukuComboBox;

    public HapusBukuPanel(HomeGUI main) {
        super(main);
        // // set layout pada panel HapusBukuPanel
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
        titleLabel.setText("Hapus Buku");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        lblBuku = new JLabel("Buku");
        lblBuku.setFont(fontGeneral);
        lblBuku.setForeground(Color.white);
        lblBuku.setAlignmentX(Component.CENTER_ALIGNMENT);

        bukuComboBox = new JComboBox<>();
        modifyComboBox(bukuComboBox);

        hapusBukuButton = new JButton("Hapus");
        modifyButton(hapusBukuButton);

        kembaliButton = new JButton("Kembali");

        // Menambahkan komponen ke panel
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(imgLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(lblBuku);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(bukuComboBox);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(hapusBukuButton);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        super.modifyAndSettingButton(kembaliButton, "staf", innerPanel);
        kembaliButton.setMaximumSize(new Dimension(200, 20));
        innerPanel.add(Box.createVerticalGlue());

        mainPanel.add(innerPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        // Menambahkan action listener
        hapusBukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textDropdown;
                String namaBuku;
                String penulisBuku;
                if (bukuComboBox.getSelectedItem() != null) {
                    textDropdown = bukuComboBox.getSelectedItem().toString();
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
                    JOptionPane.showMessageDialog(main.getFrame(), "Buku Tidak Ditemukan", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (buku.getStokAwal() != buku.getStok()) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " tidak dapat dihapus karena sedang dipinjam", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                SistakaNG.removeBuku(buku);
                JOptionPane.showMessageDialog(main.getFrame(), "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " berhasil dihapus", "Info", JOptionPane.INFORMATION_MESSAGE);

                updateDropdownBuku();
            }
        });
    }

    private void updateDropdownBuku() {
        bukuComboBox.removeAllItems();
        for (Buku buku : SistakaNG.getDaftarBuku()) {
            String bukuString = buku.getJudul() + " oleh " + buku.getPenulis();
            bukuComboBox.addItem(bukuString);
        }
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
        updateDropdownBuku();
    }

}
