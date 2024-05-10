package notepad.Notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame {
    public About(){
        setTitle("About Notepad");
        setSize(600,500);
        setLayout(null);
        setLocationRelativeTo(null);

        ImageIcon notePadIcon=new ImageIcon("notepad/icons/notepad.png");
        Image notePadImageScaled=notePadIcon.getImage().getScaledInstance(70,70 ,Image.SCALE_DEFAULT);
        ImageIcon np3=new ImageIcon(notePadImageScaled);
        JLabel label=new JLabel(np3);
        label.setBounds(50,180,70,70);
        add(label);

        ImageIcon windowsIcon=new ImageIcon("notepad/icons/windows.png");
        Image windowsImagesScaled=windowsIcon.getImage().getScaledInstance(300,60,Image.SCALE_DEFAULT);
        ImageIcon w3=new ImageIcon(windowsImagesScaled);
        JLabel label2=new JLabel(w3);
        label2.setBounds(70,20,310,70);
        add(label2);

        JLabel textLabel=new JLabel("<html>" +
                "   • How to Download and Install Netbeans ...  <br/>" +
                "<br/>" +
                "Other Videos:<br/>" +
                "<br/>" +
                "➡ Java Projects Playlist:    • 1/12 - Bank Management System | Java  <br/>" +
                "➡ JavaScript Projects Playlist:    • JavaScript Projects  <br/>" +
                "➡ React Projects Playlist:    • React Projects  <br/>" +
                "➡ MERN Stack Projects Playlist:    • MERN Stack Projects  <br/>" +
                "➡ Next js Projects Playlist:    • Next.js Projects  <br/>" +
                "➡ Leet Code Questions Playlist:    • LeetCode Solutions | Java"+"</html>");
        Font font=new Font("Arial",Font.PLAIN,13);
        textLabel.setFont(font);
        textLabel.setBounds(150,100,500,200);
        add(textLabel);
 JButton closeButton=new JButton("OK");
 closeButton.setBounds(150,350,120,25);
 closeButton.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
setVisible(false);
     }
 });
 add(closeButton);
        setVisible(true);
    }
}
