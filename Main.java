/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.pkg2_progra2;

/**
 *
 * @author Jayma
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private PalindromoAir palindromoAir = new PalindromoAir();
    private JTextArea textArea;

    public static void main(String[] args) {
        new Main().createGUI();
    }

    public void createGUI() {
        JFrame frame = new JFrame("PalindromoAir");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Nombre del pasajero:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JButton sellButton = new JButton("Vender Ticket");
        JButton cancelButton = new JButton("Cancelar Ticket");
        JButton searchButton = new JButton("Buscar Pasajero");

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    palindromoAir.sellTicket(name);
                    updateTextArea();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    palindromoAir.cancelTicket(name);
                    updateTextArea();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    int seatIndex = palindromoAir.searchPassenger(name);
                    if (seatIndex == -1) {
                        JOptionPane.showMessageDialog(null, "No se encontró el pasajero " + name + ".");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pasajero " + name + " está en el asiento " + (seatIndex + 1) + ".");
                    }
                }
            }
        });

        inputPanel.add(sellButton);
        inputPanel.add(cancelButton);
        inputPanel.add(searchButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        JButton dispatchButton = new JButton("Despachar Avión");
        dispatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                palindromoAir.dispatch();
                updateTextArea();
            }
        });

        JButton printPassengersButton = new JButton("Mostrar Pasajeros");
        printPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, palindromoAir.printPassengers(), "Lista de Pasajeros", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(dispatchButton);
        bottomPanel.add(printPassengersButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void updateTextArea() {
        textArea.setText("");
        textArea.append("Ingreso total: $" + palindromoAir.income() + "\n");
    }
}