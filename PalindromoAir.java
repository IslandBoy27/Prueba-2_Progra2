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

public class PalindromoAir {
    private Ticket[] seats = new Ticket[30];
    private boolean dispatched = false;

    // Método firstAvailable (recursivo)
    public int firstAvailable(int index) {
        if (index >= seats.length) return -1;
        if (seats[index] == null) return index;
        return firstAvailable(index + 1);
    }

    // Sobrecarga del método firstAvailable para inicializar la búsqueda
    public int firstAvailable() {
        return firstAvailable(0);
    }

    // Método searchPassenger (recursivo)
    public int searchPassenger(String name, int index) {
        if (index >= seats.length) return -1;
        if (seats[index] != null && seats[index].getName().equals(name)) return index;
        return searchPassenger(name, index + 1);
    }

    // Sobrecarga del método searchPassenger para inicializar la búsqueda
    public int searchPassenger(String name) {
        return searchPassenger(name, 0);
    }

    // Método isPalindromo (recursivo)
    public boolean isPalindromo(String name, int start, int end) {
        if (start >= end) return true;
        if (name.charAt(start) != name.charAt(end)) return false;
        return isPalindromo(name, start + 1, end - 1);
    }

    // Sobrecarga del método isPalindromo para inicializar la verificación
    public boolean isPalindromo(String name) {
        return isPalindromo(name.toUpperCase(), 0, name.length() - 1);
    }

    // Método printPassengers (recursivo)
    public void printPassengers(int index, StringBuilder sb) {
        if (index >= seats.length) return;
        if (seats[index] != null) sb.append("Asiento ").append(index + 1).append(": Nombre: ").append(seats[index].getName()).append(", Total pagado: $").append(seats[index].getTotalPaid()).append("\n");
        printPassengers(index + 1, sb);
    }

    // Sobrecarga del método printPassengers para inicializar la impresión
    public String printPassengers() {
        StringBuilder sb = new StringBuilder();
        printPassengers(0, sb);
        return sb.toString();
    }

    // Método income (recursivo)
    public double income(int index) {
        if (index >= seats.length) return 0;
        if (seats[index] == null) return income(index + 1);
        return seats[index].getTotalPaid() + income(index + 1);
    }

    // Sobrecarga del método income para inicializar el cálculo
    public double income() {
        return income(0);
    }

    // Método reset (recursivo)
    public void reset(int index) {
        if (index >= seats.length) return;
        seats[index] = null;
        reset(index + 1);
    }

    // Sobrecarga del método reset para inicializar el reseteo
    public void reset() {
        reset(0);
        dispatched = false;
    }

    // Método sellTicket
    public void sellTicket(String name) {
        int availableSeat = firstAvailable();
        if (availableSeat == -1) {
            JOptionPane.showMessageDialog(null, "No hay asientos disponibles.");
            return;
        }

        double price = 800;
        if (isPalindromo(name)) {
            price *= 0.8; // Descuento del 20%
        }

        seats[availableSeat] = new Ticket(name, price);
        JOptionPane.showMessageDialog(null, "Ticket vendido a " + name + " por $" + price);
    }

    // Método cancelTicket
    public void cancelTicket(String name) {
        int seatIndex = searchPassenger(name);
        if (seatIndex == -1) {
            JOptionPane.showMessageDialog(null, "No se encontró el ticket para " + name + ".");
        } else {
            Ticket canceledTicket = seats[seatIndex];
            seats[seatIndex] = null;
            JOptionPane.showMessageDialog(null, "Ticket cancelado: \nNombre: " + canceledTicket.getName() + "\nTotal pagado: $" + canceledTicket.getTotalPaid());
        }
    }

    // Método dispatch
    public void dispatch() {
        if (dispatched) {
            JOptionPane.showMessageDialog(null, "El avión ya ha sido despachado. No hay tickets vendidos.");
            return;
        }
        double totalIncome = income();
        if (totalIncome == 0) {
            JOptionPane.showMessageDialog(null, "No hay tickets vendidos. No se puede despachar el avión.");
        } else {
            JOptionPane.showMessageDialog(null, "Ingreso total generado: $" + totalIncome);
            reset();
            dispatched = true;
        }
    }
}