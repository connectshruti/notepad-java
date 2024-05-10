package notepad.Notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    Notepad(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Notepad");
        ImageIcon notepadImageIcon=new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
        setIconImage(notepadImageIcon.getImage());
        Font font=new Font("Helvetica", Font.PLAIN, 14);

        JMenuBar menuBar=new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        JMenu file=new JMenu("File");
        JMenu edit=new JMenu("Edit");
        JMenu about=new JMenu("About");
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(about);

        JMenuItem open=new JMenuItem("Open");
        JMenuItem newFile=new JMenuItem("New");
        JMenuItem save=new JMenuItem("Save");
        JMenuItem print=new JMenuItem("Print");
        JMenuItem exit=new JMenuItem("Exit");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);
        newFile.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        file.add(open);
        file.add(newFile);
        file.add(save);
        file.add(print);
        file.add(exit);


        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem selectAll = new JMenuItem("Select All");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);

        JMenuItem aboutUs=new JMenuItem("About");
        aboutUs.addActionListener(this);
        about.add(aboutUs);

        setFontForMenuBarItems(menuBar,font);
        setJMenuBar(menuBar);

        area=new JTextArea();
        area.setWrapStyleWord(true);
        area.setLineWrap(true);

        JScrollPane scrollPane=new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("Open")){
            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter filter=new FileNameExtensionFilter("Only txt files","txt");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(true);

            int result=fileChooser.showOpenDialog(area);
            if(result!=fileChooser.APPROVE_OPTION){
                return;
            }
            File selectedFile=fileChooser.getSelectedFile();
            try{
            BufferedReader reader=new BufferedReader(new FileReader(selectedFile));
            area.read(reader,null);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else if (ae.getActionCommand().equals("New")) {
            area.setText("");
        }else if (ae.getActionCommand().equals("Save")) {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setApproveButtonText("Save");
            int result=fileChooser.showOpenDialog(area);
            if(result!=fileChooser.APPROVE_OPTION){
                return;
            }
            File file=new File(fileChooser.getSelectedFile()+".txt");
            BufferedWriter outputFile=null;
                try{
                    outputFile=new BufferedWriter(new FileWriter(file));
                    area.write(outputFile);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
        }else if (ae.getActionCommand().equals("Print")) {
          try{
              area.print();
          }catch(Exception ex){
              ex.printStackTrace();
          }

        }else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        }else if (ae.getActionCommand().equals("Copy")) {
            String selectedText = area.getSelectedText();
            if (selectedText != null && !selectedText.isEmpty()) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection selection = new StringSelection(selectedText);
                clipboard.setContents(selection, null);
            }
        }else if (ae.getActionCommand().equals("Cut")) {
            area.cut();
        }else if (ae.getActionCommand().equals("Paste")) {
            Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents=clipboard.getContents(null);
            if(contents!=null&&contents.isDataFlavorSupported(DataFlavor.stringFlavor)){
                try{
                    String clipboardText=(String)contents.getTransferData(DataFlavor.stringFlavor);
                    int caretPosition=area.getCaretPosition();
                    area.insert(clipboardText,caretPosition);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }else if (ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        }else if (ae.getActionCommand().equals("About")) {
          new About();
        }
    }
    private static void setFontForMenuBarItems(JComponent component, Font font){
            for(Component comp: component.getComponents()){
               if(comp instanceof JMenu){
                   comp.setFont(font);
                   for(Component comp2:((JMenu) comp).getMenuComponents()){
                       comp2.setFont(font);
                   }
               }
            }

    }
    public static void main(String []args){
        new Notepad();
    }
}
