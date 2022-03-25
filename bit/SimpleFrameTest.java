package bit;

import javax.swing.*;
import java.awt.*;


public class SimpleFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                    var frame = new SimpleFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                });
    }
}

class SimpleFrame extends JFrame {//创建窗体
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public SimpleFrame() {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
