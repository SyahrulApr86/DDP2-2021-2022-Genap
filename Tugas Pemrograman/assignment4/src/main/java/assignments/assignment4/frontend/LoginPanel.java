package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.backend.pengguna.Pengguna;
import assignments.assignment4.backend.pengguna.Staf;
import assignments.assignment4.frontend.staf.ui.StafHomePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

// TODO: Implementasikan hal-hal yang diperlukan
public class LoginPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, leftPanel, rightPanel, centerPanel;
    JLabel titleLabel, labelPerintah, imgLabelLeft, imgLabelRight;
    JButton btnLogin, btnBack;
    Border border;
    ImageIcon iconLeft, setIconLeft, iconRight, setIconRight;
    JTextField fieldId;

    public LoginPanel(HomeGUI main){
        super(main);
        // mengatur layout LoginPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        border = BorderFactory.createLineBorder(purple, 2);
        setBorder(border);

        // membuat dan mengatur label untuk judul
        titleLabel = new JLabel("Silakan login");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen TextField, Label, dan Button
        labelPerintah = new JLabel("Masukkan ID anda untuk login ke sistem");
        fieldId = new JTextField();
        btnLogin = new JButton("Login");
        btnBack = new JButton("Kembali");

        // mengatur label
        labelPerintah.setFont(fontGeneral);
        labelPerintah.setForeground(Color.white);

        // mengatur fieldId
        modifyField(fieldId);

        // mengatur button
        modifyButton(btnLogin);
        modifyButton(btnBack);

        // ActionEvent Button btnSubmit untuk menampilkan pesan sesuai kondisi
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String id = fieldId.getText().trim();
                String pesan = "";

                if (id.equals("")){
                    pesan = "Harap masukkan ID anda pada kolom";
                    JOptionPane.showMessageDialog(main.getFrame(), pesan, "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    main.setUser(SistakaNG.handleLogin(id)); // mengubah user menjadi user yang sedang login

                    if (SistakaNG.getPenggunaLoggedIn() instanceof Staf){
                        fieldId.setText("");
                        main.setPanel("staf");

                    } else if (SistakaNG.getPenggunaLoggedIn() instanceof Anggota){
                        fieldId.setText("");
                        main.setPanel("anggota");
                    } else {
                        pesan = String.format("Pengguna dengan ID %s tidak ditemukan", id);
                        JOptionPane.showMessageDialog(main.getFrame(), pesan, "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // ActionEvent Button btnBack untuk menampilkan panel Welcome
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                fieldId.setText("");
                main.setPanel("welcome");
            }
        });

        // membuat panel utama
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // membuat panel kiri
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(1,1));
        leftPanel.setBackground(purple);

        iconLeft = new ImageIcon("./img/bookshelf left.png");
        setIconLeft = new ImageIcon(iconLeft.getImage().getScaledInstance(86, 500, Image.SCALE_DEFAULT));
        imgLabelLeft = new JLabel(setIconLeft);
        imgLabelLeft.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(imgLabelLeft);

        // membuat panel kanan
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(1,1));
        rightPanel.setBackground(purple);

        iconRight = new ImageIcon("./img/bookshelf right.png");
        setIconRight = new ImageIcon(iconRight.getImage().getScaledInstance(86, 500, Image.SCALE_DEFAULT));
        imgLabelRight = new JLabel(setIconRight);
        imgLabelRight.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(imgLabelRight);

        // membuat panel tengah
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(purple);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        centerPanel.add(labelPerintah);
        labelPerintah.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        centerPanel.add(fieldId);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        centerPanel.add(btnLogin);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        centerPanel.add(btnBack);
        centerPanel.add(Box.createVerticalGlue());

        // mengatur panel utama
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        add(mainPanel);
    }

    @Override
    public void refresh() {
        // ignored
    }


}
