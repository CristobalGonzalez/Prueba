package bol8_Ejer2;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Bol8_Ejerc2 {
	public static void main(String[] args) {
		tecladoMovl aplicacion = new tecladoMovl();
		aplicacion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		aplicacion.setSize(192, 380);
		aplicacion.setVisible(true);
	}
}