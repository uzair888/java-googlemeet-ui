package com.mycompany.googlemeetui;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class VideoConferenceUI extends JFrame {
 private static final int FRAME_WIDTH = 1200;
 private static final int FRAME_HEIGHT = 800;
    public VideoConferenceUI() {
        // Set up the main frame
        setTitle("Video Conference UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(new BorderLayout());
        setResizable(false); // Disable window resizing
        setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        topPanel.setPreferredSize(new Dimension(800, 80));
        topPanel.setLayout(new BorderLayout());
        JLabel topLabel = new JLabel("Fathima is presenting", SwingConstants.LEFT);
        topLabel.setForeground(Color.WHITE);
        topLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        topLabel.setIcon(loadIcon("/presentor-image.png")); // Replace with actual path
        topPanel.add(topLabel, BorderLayout.WEST);

        // Center panel (main video display)
           JPanel centerPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(700, 610); // Adjust the size of the center panel
            }
        };
        centerPanel.setBackground(Color.DARK_GRAY);
        centerPanel.setLayout(new GridBagLayout());

        RoundedImageLabel mainVideo = new RoundedImageLabel(loadIcon("/presenter-image.jpg"), 14); // Replace with actual path
        mainVideo.setPreferredSize(new Dimension(700, 610)); // Set the size for the video image
        centerPanel.add(mainVideo);

        // Right panel (participant thumbnails)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Adjust rows and columns as needed
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setPreferredSize(new Dimension(460, 300)); 

        // Adding participant thumbnails
        String[] participantNames = {"Jenelia", "Joe Carlson", "Lucy Sera", "Sara Johns", 
                                     "Jennifer David", "Rachel Green", "Carol Mark", "Monica Geller"};
        String[] participantImages = {"/Jennela-image.jpg", "/joe-image.jpg", "/lucy-image.jpg", "/sara-image.jpg",
                                      "/jennifer.jpg", "/rachel.jpg", "/carol.jpg", "/monica.jpg"};
        for (int i = 0; i < participantNames.length; i++) {
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(100, 400));

            RoundedImageLabel imageLabel = new RoundedImageLabel(scaleImageIcon(loadIcon(participantImages[i]), 210, 150), 14);
            imageLabel.setBounds(0, 0, 210, 135);

            JLabel nameLabel = new JLabel(participantNames[i]);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            nameLabel.setBounds(5, 100, 200, 40); // Position name at the bottom left

            layeredPane.add(imageLabel, Integer.valueOf(0));
            layeredPane.add(nameLabel, Integer.valueOf(1));

            rightPanel.add(layeredPane);
        }

       JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setPreferredSize(new Dimension(1200, 70));
        bottomPanel.setLayout(new BorderLayout());

        // Left label "Class meeting"
        JLabel classMeetingLabel = new JLabel("Class meeting", SwingConstants.LEFT);
        classMeetingLabel.setForeground(Color.WHITE);
        classMeetingLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        classMeetingLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        bottomPanel.add(classMeetingLabel, BorderLayout.WEST);

        // Center panel for control buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.DARK_GRAY);
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        String[] buttonIcons = {"/mic2.png", "/camera.png", "/cc.png", "/hand.png", "/screen.png", "/more.png"};
        int[][] iconSizes = {
                {16, 20}, {20, 14}, {20, 16}, {20, 20}, {20, 16}, {3, 15}
        };
        for (int i = 0; i < buttonIcons.length; i++) {
            JButton button = createCircularButton(loadIcon(buttonIcons[i]), iconSizes[i][0], iconSizes[i][1]); // Replace with actual paths
            controlPanel.add(button);
        }
        JButton endButton = createRoundedEdgeButton(loadIcon("/end.png")); // Replace with actual path
        controlPanel.add(endButton);
        bottomPanel.add(controlPanel, BorderLayout.CENTER);

        // Right panel for additional icons
        JPanel rightIconsPanel = new JPanel();
        rightIconsPanel.setBackground(Color.DARK_GRAY);
        rightIconsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
          rightIconsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
         String[] additionalIcons = {"/i.png", "/everyone.png", "/chat.png", "/activities.png"}; // Replace with actual paths
        int[][] additionalIconSizes = {
                {20, 20}, {22, 20}, {20, 20}, {24, 22}
        };
        for (int i = 0; i < additionalIcons.length; i++) {
            JButton button = createIconButton(loadIcon(additionalIcons[i]), additionalIconSizes[i][0], additionalIconSizes[i][1]); // Replace with actual paths
            rightIconsPanel.add(button);
        }

        bottomPanel.add(rightIconsPanel, BorderLayout.EAST);

        // Adding panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }

    
        private JButton createIconButton(ImageIcon icon, int iconWidth, int iconHeight) {
        // Scale the icon to the desired size
        Image scaledImage = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setPreferredSize(new Dimension(50, 50));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        return button;
    }
    
    private JButton createCircularButton(ImageIcon icon,int iconWidth, int iconHeight) {
        // Scale the icon to a smaller size (e.g., 30x30)
        Image scaledImage = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                // Draw shadow
                g2.setColor(new Color(0, 0, 0, 50)); // Semi-transparent black
                g2.fillOval(2, 2, getWidth() - 10, getHeight() - 10); // Adjust shadow size and position

                // Draw button background
                g2.setColor(new Color(83, 83, 83)); // Darker gray color
                g2.fillOval(0, 0, getWidth() - 10, getHeight() - 10); // Adjust button size and position

                // Draw the scaled icon if it exists
                if (scaledIcon != null) {
                    int iconWidth = scaledIcon.getIconWidth();
                    int iconHeight = scaledIcon.getIconHeight();
                    int x = (getWidth() - iconWidth) / 2 - 5; // Adjust icon position
                    int y = (getHeight() - iconHeight) / 2 - 5; // Adjust icon position
                    scaledIcon.paintIcon(this, g2, x, y);
                }

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                
            }

            @Override
            public boolean contains(int x, int y) {
                int radius = getWidth() / 2;
                return (x - radius) * (x - radius) + (y - radius) * (y - radius) <= radius * radius;
            }
        };

        button.setPreferredSize(new Dimension(50, 50));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        return button;
    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        if (icon != null) {
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }

    private JButton createRoundedEdgeButton(ImageIcon icon) {
        // Scale the icon to a smaller size (e.g., 30x30)
        Image scaledImage = icon.getImage().getScaledInstance(70, 46, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                // Set the background color to a darker gray
                g.setColor(new Color(64, 64, 64)); // Darker gray color
                g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 30, 30);

                // Draw the scaled icon if it exists
                if (scaledIcon != null) {
                    int iconWidth = scaledIcon.getIconWidth();
                    int iconHeight = scaledIcon.getIconHeight();
                    int x = (getWidth() - iconWidth) / 2;
                    int y = (getHeight() - iconHeight) / 2;
                    scaledIcon.paintIcon(this, g, x, y);
                }
            }

            @Override
            protected void paintBorder(Graphics g) {

            }

            @Override
            public boolean contains(int x, int y) {
                int arcWidth = 30;
                int arcHeight = 30;
                Rectangle bounds = new Rectangle(0, 0, getWidth(), getHeight());
                return new RoundRectangle2D.Float(bounds.x, bounds.y, bounds.width, bounds.height, arcWidth, arcHeight).contains(x, y);
            }
        };

        button.setPreferredSize(new Dimension(70, 46));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        button.setOpaque(false);
        return button;
    }

    private ImageIcon loadIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
