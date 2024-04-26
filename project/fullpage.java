import java.awt.*;

public class fullpage {

    public static void main(String[] args) {
        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Create a frame
        Frame frame = new Frame();
        
        // Set frame properties
        frame.setSize(screenWidth, screenHeight); // Set frame size to full screen
        frame.setUndecorated(true); // Remove window decorations (title bar, borders)
        
        // Add components to the frame (for example, a panel with some content)
        Panel panel = new Panel();
        Label label = new Label("Welcome to Full Page Application!");
        panel.add(label);
        
        // Set layout for the panel (optional)
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50)); // Example layout
        
        // Add the panel to the frame
        frame.add(panel);
        
        // Set frame visible
        frame.setVisible(true);
    }
}

