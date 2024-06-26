/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.googlemeetui;

/**
 *
 * @author HP
 */
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

class RoundedImageLabel extends JLabel {
    private int cornerRadius;

    public RoundedImageLabel(ImageIcon icon, int cornerRadius) {
        super(icon);
        this.cornerRadius = cornerRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint the rounded border
        RoundRectangle2D.Double round = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setClip(round);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint the border
        RoundRectangle2D.Double round = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(getForeground());
        g2.draw(round);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        int arcWidth = cornerRadius;
        int arcHeight = cornerRadius;
        Rectangle bounds = new Rectangle(0, 0, getWidth(), getHeight());
        return new RoundRectangle2D.Float(bounds.x, bounds.y, bounds.width, bounds.height, arcWidth, arcHeight).contains(x, y);
    }
}
