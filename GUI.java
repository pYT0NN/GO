package planer;

import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame /*implements ActionListener*/ {
// private TextField tfCount;    // Declare a TextField component
// private Button btnCount;      // Declare a Button component
private int count = 0;        // Counter's value
int n = 9;

public GUI(){
        setLayout(new GridLayout());
        Label[] arr = new Label[n];
        for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                        arr[i] = new Label(i + " = i, j =  " + j);
                }
        }
        for(int i = 0; i < n; i++) {
                add(arr[i]);
        }

        // lblCount = new Label("Counter:"); // construct the Label component
        // add(lblCount);                  // "super" Frame container adds Label component
        //
        // tfCount = new TextField(count + "", 10); // construct the TextField component with initial text
        // tfCount.setEditable(false);     // set to read-only
        // add(tfCount);                   // "super" Frame container adds TextField component
        //
        // btnCount = new Button("Count"); // construct the Button component
        // add(btnCount);                  // "super" Fram

        setTitle("GO Spiel"); // "super" Frame sets its title
        setSize(500, 500);      // "super" Frame sets its initial window size
        setVisible(true);       // "super" Frame shows

        // btnCount.addActionListener(this);
        // "btnCount" is the source object that fires an ActionEvent when clicked.
        // The source add "this" instance as an ActionEvent listener, which provides
        //   an ActionEvent handler called actionPerformed().
        // Clicking "btnCount" invokes actionPerformed().

}
public static void main(String[] args){

        GUI display = new GUI();
}

// ActionEvent handler - Called back upon button-click.
// @Override
// public void actionPerformed(ActionEvent evt) {
//         ++count; // Increase the counter value
//         // Display the counter value on the TextField tfCount
//         tfCount.setText(count + ""); // Convert int to String
// }

}
