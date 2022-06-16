package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Dosen;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahDosenPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel;
    JLabel titleLabel, imgLabel, namaLabel;
    JButton btnTambahDosen, btnKembali;
    Border border;
    ImageIcon icon, setIcon;
    JTextField namaTextField;

    public TambahDosenPanel(HomeGUI main) {
        super(main);
        // set layout pada panel TamabahDosenPanel
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
        titleLabel.setText("Tambah Dosen");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        namaLabel = new JLabel("Nama");
        namaLabel.setFont(fontGeneral);
        namaLabel.setForeground(Color.white);
        namaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        namaTextField = new JTextField();
        modifyField(namaTextField);


        btnTambahDosen = new JButton("Tambah");
        modifyButton(btnTambahDosen);

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
        innerPanel.add(namaTextField);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        innerPanel.add(btnTambahDosen);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        super.modifyAndSettingButton(btnKembali, "staf", innerPanel);
        btnKembali.setMaximumSize(new Dimension(200, 20));
        innerPanel.add(btnKembali);
        innerPanel.add(Box.createVerticalGlue());

        mainPanel.add(innerPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        // Menambahkan action listener
        btnTambahDosen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = namaTextField.getText();
                if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(main.getFrame(), "Nama tidak boleh kosong", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Dosen dosen = SistakaNG.addDosen(nama);
                JOptionPane.showMessageDialog(main.getFrame(), "Berhasil menambahkan dosen dengan ID " + dosen.getId() + "!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                namaTextField.setText("");
            }
        });
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
    }

}
