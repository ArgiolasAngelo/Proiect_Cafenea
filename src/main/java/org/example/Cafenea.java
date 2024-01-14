package org.example;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Cafenea extends JFrame {

    private JRadioButton boabe;
    private JRadioButton macinata;
    private JRadioButton capsule;

    Cafenea() {
        JPanel primaLinie = new JPanel();
        primaLinie.setBounds(0, 0, 750, 50);

        JLabel titlu = new JLabel("Formular Cafenea");
        titlu.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaDoi = new JPanel();
        liniaDoi.setBounds(0, 100, 750, 50);
        liniaDoi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel nume = new JLabel("Nume cafea:");
        nume.setFont(new Font("Dialog", Font.PLAIN, 30));

        JTextField numeText = new JTextField();
        numeText.setPreferredSize(new Dimension(300, 40));

        JPanel liniaTrei = new JPanel();
        liniaTrei.setBounds(0, 150, 750, 50);
        liniaTrei.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 0));

        JLabel cantitate = new JLabel("Cantitate:");
        cantitate.setFont(new Font("Dialog", Font.PLAIN, 30));

        JTextField cantitateText = new JTextField();
        cantitateText.setPreferredSize(new Dimension(300, 40));

        JPanel liniaPatru = new JPanel();
        liniaPatru.setBounds(0, 250, 750, 50);

        JLabel tip = new JLabel("Tip cafea:");
        tip.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaCinci = new JPanel();
        liniaCinci.setBounds(0, 300, 750, 50);
        liniaCinci.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));

        boabe = new JRadioButton("Boabe");
        macinata = new JRadioButton("Macinata");
        capsule = new JRadioButton("Capsule");
        boabe.setFont(new Font("Dialog", Font.PLAIN, 20));
        macinata.setFont(new Font("Dialog", Font.PLAIN, 20));
        capsule.setFont(new Font("Dialog", Font.PLAIN, 20));

        ButtonGroup group = new ButtonGroup();
        group.add(boabe);
        group.add(macinata);
        group.add(capsule);

        JLabel ambalaj = new JLabel("Tip ambalaj:");
        ambalaj.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaSase = new JPanel();
        liniaSase.setBounds(0, 400, 750, 60);

        String[] ambalajAlegere = {"Plastic", "Hartie", "Carton"};
        JComboBox<String> comboBox = new JComboBox<>(ambalajAlegere);
        comboBox.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel liniaSapte = new JPanel();
        liniaSapte.setBounds(0, 650, 750, 70);

        JButton save = new JButton("SAVE");
        save.setFont(new Font("Dialog", Font.PLAIN, 25));
        JButton cancel = new JButton("CANCEL");
        cancel.setFont(new Font("Dialog", Font.PLAIN, 25));

        // Adăugare ActionListener pentru butonul de salvare
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvareDateJSON(numeText.getText(), cantitateText.getText(), getTipCafea(), (String) comboBox.getSelectedItem());
            }
        });

        primaLinie.add(titlu);
        liniaDoi.add(nume);
        liniaDoi.add(numeText);
        liniaTrei.add(cantitate);
        liniaTrei.add(cantitateText);
        liniaPatru.add(tip);
        liniaCinci.add(boabe);
        liniaCinci.add(macinata);
        liniaCinci.add(capsule);
        liniaSase.add(ambalaj);
        liniaSase.add(comboBox);
        liniaSapte.add(save);
        liniaSapte.add(cancel);
        this.add(liniaDoi);
        this.add(liniaTrei);
        this.add(liniaPatru);
        this.add(liniaCinci);
        this.add(liniaSase);
        this.add(liniaSapte);
        this.setTitle("Formular");
        this.add(primaLinie);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(750, 750);

        this.setVisible(true);
    }

    private String getTipCafea() {
        if (boabe.isSelected()) {
            return "Boabe";
        } else if (macinata.isSelected()) {
            return "Macinata";
        } else if (capsule.isSelected()) {
            return "Capsule";
        }
        return "";
    }

    private void salvareDateJSON(String nume, String cantitate, String tip, String ambalaj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Nume cafea", nume);
        jsonObject.put("Cantitate", cantitate);
        jsonObject.put("Tip cafea", tip);
        jsonObject.put("Ambalaj", ambalaj);

        try (FileWriter fileWriter = new FileWriter("date_cafea.json", true)) {
            fileWriter.write(jsonObject.toJSONString() + "\n");
            fileWriter.flush();
            JOptionPane.showMessageDialog(this, "Datele au fost salvate cu succes!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "A apărut o eroare la salvarea datelor.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}