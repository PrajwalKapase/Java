
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CircleDrawing extends JFrame implements ActionListener {

    private JComboBox<String> algoBox, styleBox;
    private JButton drawButton;
    private DrawPanel drawPanel;

    public CircleDrawing() {
        setTitle("Circle Drawing Algorithms");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Control Panel 
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Algorithm:"));
        algoBox = new JComboBox<>(new String[]{"DDA", "Bresenham", "Midpoint"});
        controlPanel.add(algoBox);

        controlPanel.add(new JLabel("Style:"));
        styleBox = new JComboBox<>(new String[]{"Solid", "Dotted", "Dashed"});
        controlPanel.add(styleBox);

        drawButton = new JButton("Draw Circle");
        drawButton.addActionListener(this);
        controlPanel.add(drawButton);

        add(controlPanel, BorderLayout.NORTH);

        // Drawing Panel 
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawPanel.setAlgorithm((String) algoBox.getSelectedItem());
        drawPanel.setStyle((String) styleBox.getSelectedItem());
        drawPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CircleDrawing().setVisible(true));
    }
}

class DrawPanel extends JPanel {

    private String algorithm = "DDA";
    private String style = "Solid";

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xc = getWidth() / 2;
        int yc = getHeight() / 2;
        int r = 200;

        switch (algorithm) {
            case "DDA" -> drawCircleDDA(g, xc, yc, r);
            case "Bresenham" -> drawCircleBresenham(g, xc, yc, r);
            case "Midpoint" -> drawCircleMidpoint(g, xc, yc, r);
        }
    }

    // Drawing Styles Helper 
    private boolean shouldPlot(int count) {
        return switch (style) {
            case "Dotted" -> count % 10 == 0;
            case "Dashed" -> (count % 20) < 10;
            default -> true; // Solid
        };
    }

    // DDA Algorithm 
    private void drawCircleDDA(Graphics g, int xc, int yc, int r) {
        double theta = 0;
        double step = 1.0 / r;
        int count = 0;

        while (theta <= 2 * Math.PI) {
            int x = (int) (xc + r * Math.cos(theta));
            int y = (int) (yc + r * Math.sin(theta));
            if (shouldPlot(count)) g.fillRect(x, y, 1, 1);
            theta += step;
            count++;
        }
    }

    // Bresenham Algorithm 
    private void drawCircleBresenham(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r;
        int d = 3 - 2 * r;
        int count = 0;

        while (x <= y) {
            plot8Points(g, xc, yc, x, y, count);
            count++;
            if (d < 0) d += 4 * x + 6;
            else {
                d += 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    // Midpoint Algorithm 
    private void drawCircleMidpoint(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r;
        int p = 1 - r;
        int count = 0;

        while (x <= y) {
            plot8Points(g, xc, yc, x, y, count);
            count++;
            if (p < 0) p += 2 * x + 3;
            else {
                p += 2 * (x - y) + 5;
                y--;
            }
            x++;
        }
    }

    // Plot 8 symmetric points for circle
    private void plot8Points(Graphics g, int xc, int yc, int x, int y, int count) {
        if (shouldPlot(count)) {
            g.fillRect(xc + x, yc + y, 1, 1);
            g.fillRect(xc - x, yc + y, 1, 1);
            g.fillRect(xc + x, yc - y, 1, 1);
            g.fillRect(xc - x, yc - y, 1, 1);
            g.fillRect(xc + y, yc + x, 1, 1);
            g.fillRect(xc - y, yc + x, 1, 1);
            g.fillRect(xc + y, yc - x, 1, 1);
            g.fillRect(xc - y, yc - x, 1, 1);
        }
    }
}
