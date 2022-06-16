package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class PeringkatPanel extends SistakaPanel {
    // Field
    JPanel mainPanel, innerPanel, panelForText;
    JLabel titleLabel, imgLabel, peringkatAnggotaLabel;
    JButton btnKembali;
    Border border;
    ImageIcon icon, setIcon;

    public PeringkatPanel(HomeGUI main) {
        super(main);
        // set layout pada panel PeringkatPanel
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
        titleLabel.setText("Peringkat");
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen
        peringkatAnggotaLabel = new JLabel();
        peringkatAnggotaLabel.setFont(fontGeneral);
        peringkatAnggotaLabel.setForeground(Color.white);
        peringkatAnggotaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelForText = new JPanel();
        panelForText.setLayout(new GridLayout(1, 3));
        panelForText.setBackground(darkerPurple);
        panelForText.add(new JLabel(""));
        panelForText.add(peringkatAnggotaLabel);
        panelForText.add(new JLabel(""));

        btnKembali = new JButton("Kembali");

        // Menambahkan komponen ke panel
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(imgLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(panelForText);
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
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
        peringkatAnggotaLabel.setText(SistakaNG.handleRankingAnggota());
        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
