import javax.swing.*;
import java.awt.*;

public class HelloJNI {  // Save as HelloJNI.java
    static {
        System.loadLibrary("hello"); // Load native library hello.dll (Windows) or libhello.so (Unixes)
        //  at runtime
        // This library contains a native method called sayHello()
    }

    // Test Driver
    public static void main(String[] args) {
        HelloJNI obj = new HelloJNI();  // Create an instance and invoke the native method
        obj.sayHello();
        int[] a = new int[]{-2, -1, 0, 1, 2, 3};
        System.out.println(obj.calculateMean(a));
        System.out.println(obj.calculateSTDDev(a));

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define new buttons
        JLabel jl = new JLabel("Enter the number of samples to generate:");
        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField textField = new JTextField("TextBox");
        textField.setMaximumSize(textField.getPreferredSize());
        JButton jb1 = new JButton("Go");
        jb1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel mean_label = new JLabel("Mean");
        JTextField mean = new JTextField("TextBox");
        JPanel mean_panel = new JPanel();
        mean_panel.setLayout(new FlowLayout());
        mean_panel.add(mean_label);
        mean_panel.add(mean);
        mean_panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel std_label = new JLabel("Standard deviation");
        JTextField std = new JTextField("TextBox");
        std.setSize(200, 50);
        JPanel std_panel = new JPanel();
        std_panel.setLayout(new FlowLayout());
        std_panel.add(std_label);
        std_panel.add(std);
        std_panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Define the panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(jl);
        panel.add(textField);
        panel.add(jb1);
        panel.add(mean_panel);
        panel.add(std_panel);

        // Set the window to be visible as the default to be false
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }

    // Declare an instance native method sayHello() which receives no parameter and returns void
    private native void sayHello();

    public native double calculateSTDDev(int[] numbers);

    public native double calculateMean(int[] numbers);
}
//g++ -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/darwin" -dynamiclib -o libhello.dylib HelloJNI.cpp
//https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html#zz-4.3