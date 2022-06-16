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
public class PembayaranPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel;
    JLabel titleLabel, imgLabel, jmlhDendaLabel;
    JButton btnBayar, btnKembali;
    Border border;
    ImageIcon icon, setIcon;
    JTextField jmlhDendaField;

    public PembayaranPanel(HomeGUI main) {
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
        titleLabel.setText("Bayar Denda");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        jmlhDendaLabel = new JLabel("Jumlah Denda");
        jmlhDendaLabel.setFont(fontGeneral);
        jmlhDendaLabel.setForeground(Color.white);
        jmlhDendaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        jmlhDendaField = new JTextField();
        modifyField(jmlhDendaField);

        btnBayar = new JButton("Bayar");
        modifyButton(btnBayar);

        btnKembali = new JButton("Kembali");

        // Menambahkan komponen ke panel
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(imgLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(jmlhDendaLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(jmlhDendaField);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(btnBayar);
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
        btnBayar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jumlahBayarDenda = jmlhDendaField.getText();
                if (jumlahBayarDenda.isEmpty()) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Harap isi jumlah denda", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!isNumeric(jumlahBayarDenda)) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Jumlah Bayar harus berupa angka", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                long jumlahBayarDendaInt = Long.parseLong(jumlahBayarDenda);
                String hasil = SistakaNG.bayarDenda(jumlahBayarDendaInt);
                JOptionPane.showMessageDialog(main.getFrame(), hasil, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
        jmlhDendaField.setText("");
    }
}
