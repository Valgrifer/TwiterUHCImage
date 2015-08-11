package fr.azuxul.uhcimagestwiter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

/**
 * Created by Azuxul on 09/08/2015.
 */
public class Window {

    private JFrame frame;
    private JPanel panel;
    public JButton buttonSave;
    public JButton buttonChangePath;
    public JFormattedTextField textName;
    public JFormattedTextField textTeam;
    public JFormattedTextField textDate;
    public JFormattedTextField textIp;
    public JFormattedTextField textSc;
    public JFormattedTextField textOpen;
    public JFormattedTextField textStart;
    public JFormattedTextField textPathImage;
    public JFormattedTextField textMumble;
    public JComboBox<String> listBackground;
    public JCheckBox checkboxMumbleLink;
    public JLabel labelName;
    public JLabel labelTeamSize;
    public JLabel labelDate;
    public JLabel labelIP;
    public JLabel labelSc;
    public JLabel labelOpen;
    public JLabel labelStart;
    public JLabel labelBackground;
    public JLabel labelPathImage;
    public JLabel labelMumble;
    public JLabel labelCredits;

    public Window(int HEIGHT, int WEIGHT, String TITLE){

        frame = new JFrame(TITLE);

        frame.setSize(HEIGHT, WEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(720, 325));
        frame.setResizable(false);

        panel = new Panel();
        frame.setContentPane(panel);

        addComponent();

        frame.setVisible(true);

    }

    public void update(){

        frame.validate();
        frame.repaint();
    }

    private void addComponent() {

        final int defaultX = ((frame.getWidth() - 440) / 10) + 440;

    	
        ActionListener listenerSave = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.save = true;
                update();
            }

        };

        ActionListener listenerChangePath = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public String getDescription() {
                        return "Image png (.png)";
                    }

					@Override
					public boolean accept(File arg0) {
						return true;
					}
                });
                fileChooser.showOpenDialog(panel);
                
                textPathImage.setText(fileChooser.getSelectedFile().getPath());
                Backgrond.CUSTOM.setPath(fileChooser.getSelectedFile().getPath());
                Main.getWindow().update();
            }

        };
        
        ActionListener listenerChangeImage = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(((String) listBackground.getSelectedItem()).equalsIgnoreCase(Backgrond.CUSTOM.getName()))
                {
                    panel.add(labelPathImage);
                    panel.add(textPathImage);
                    panel.add(buttonChangePath);

                    checkboxMumbleLink.setBounds(defaultX-5, 190, 90, 20);
                    labelMumble.setBounds(defaultX + 95, 190, 20, 20);
                    textMumble.setBounds(defaultX + 120, 190, 225, 20);
                }
                else
                {
                	try
                	{
                        panel.remove(labelPathImage);
                        panel.remove(textPathImage);
                        panel.remove(buttonChangePath);

                        checkboxMumbleLink.setBounds(defaultX-5, 165, 90, 20);
                        labelMumble.setBounds(defaultX + 95, 165, 20, 20);
                        textMumble.setBounds(defaultX + 120, 165, 225, 20);
                        
                	}
                	catch(Exception ex){}
                }
                
                Main.getWindow().update();
            }

        };

        ActionListener listenerMumble = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkboxMumbleLink.isSelected())
                {
                    panel.add(labelMumble);
                    panel.add(textMumble);
                }
                else
                {
                    panel.remove(labelMumble);
                    panel.remove(textMumble);
                }
                
                Main.getWindow().update();
            }
        };

        buttonSave = new JButton("Export file as .png");
        buttonChangePath = new JButton("...");
        textName = new JFormattedTextField();
        textTeam = new JFormattedTextField();
        textDate = new JFormattedTextField();
        textSc = new JFormattedTextField();
        textIp = new JFormattedTextField();
        textStart = new JFormattedTextField();
        textOpen = new JFormattedTextField();
        textPathImage = new JFormattedTextField();
        textMumble = new JFormattedTextField();
        listBackground = new JComboBox<String>();
        checkboxMumbleLink = new JCheckBox("Mumble Link");
        labelName = new JLabel("Name :");
        labelTeamSize = new JLabel("Team size (RvB=RvB, FFA=0) :");
        labelDate = new JLabel("Date :");
        labelSc = new JLabel("Scénarios :");
        labelIP = new JLabel("Ip:");
        labelOpen = new JLabel("Ouverture :");
        labelStart = new JLabel("Start :");
        labelBackground = new JLabel("Image de font :");
        labelPathImage = new JLabel("Chemin de l'image :");
        labelMumble = new JLabel("Ip :");
        labelCredits = new JLabel("http://azuxul.free.fr/ \t - \t https://github.com/Azuxul/ \t - \t @Azuxul");

        panel.setLayout(null);

        buttonSave.setBounds(defaultX, 255, ((frame.getWidth() - ((frame.getWidth() - 440) / 32) * 2 + 440) / 3), 30);
        buttonSave.addActionListener(listenerSave);
        panel.add(buttonSave);

        labelName.setBounds(defaultX, 15, ((frame.getWidth() - ((frame.getWidth() - 440) / 32) * 2 + 440) / 8), 25);
        panel.add(labelName);

        textName.setBounds(((frame.getWidth() - 440) / 10) + 500, 15, ((frame.getWidth() - ((frame.getWidth() - 440) / 32) * 2 + 440) / 5), 20);
        textName.getDocument().addDocumentListener(new UpdateListener());
        panel.add(textName);

        labelTeamSize.setBounds(defaultX, 40, 155, 25);
        panel.add(labelTeamSize);

        textTeam.setBounds(defaultX + 155, 40, 30, 20);
        textTeam.getDocument().addDocumentListener(new UpdateListener());
        panel.add(textTeam);

        labelDate.setBounds(defaultX + 205, 40, 30, 25);
        panel.add(labelDate);

        textDate.setBounds(defaultX + 245, 40, 100, 20);
        textDate.getDocument().addDocumentListener(new UpdateListener());
        panel.add(textDate);

        labelSc.setBounds(defaultX, 65, 55, 25);
        panel.add(labelSc);

        textSc.setBounds(defaultX + 60, 65, 285, 20);
        textSc.getDocument().addDocumentListener(new UpdateListener());
        panel.add(textSc);

        labelIP.setBounds(defaultX, 90, 20, 25);
        panel.add(labelIP);

        textIp.setBounds(defaultX + 25, 90, 320, 20);
        textIp.getDocument().addDocumentListener(new UpdateListener());
        panel.add(textIp);

        labelOpen.setBounds(defaultX, 115, 60, 25);
        panel.add(labelOpen);

        textOpen.setBounds(defaultX + 65, 115, 115, 20);
        textOpen.getDocument().addDocumentListener(new UpdateListener());
        panel.add(textOpen);

        labelStart.setBounds(defaultX + 190, 115, 35, 25);
        panel.add(labelStart);

        textStart.setBounds(defaultX + 230, 115, 115, 20);
        textStart.getDocument().addDocumentListener(new UpdateListener());
        panel.add(textStart);
        
        labelBackground.setBounds(defaultX, 140, 80, 20);
        panel.add(labelBackground);
        
        listBackground.setBounds(defaultX+85, 140, 120, 20);
        for(Backgrond b : Backgrond.values())listBackground.addItem(b.getName());
        listBackground.setSelectedIndex(new Random().nextInt(Backgrond.values().length-1));
        listBackground.addActionListener(listenerChangeImage);
        panel.add(listBackground);

        labelPathImage.setBounds(defaultX, 165, 100, 20);
        
        textPathImage.setBounds(defaultX+105, 165, 215, 20);
        textPathImage.getDocument().addDocumentListener(new UpdatePathListener());

        buttonChangePath.setBounds(defaultX+325, 165, 20, 20);
        buttonChangePath.addActionListener(listenerChangePath);
        
        checkboxMumbleLink.setBounds(defaultX-5, 165, 90, 20);
        checkboxMumbleLink.addActionListener(listenerMumble);
        panel.add(checkboxMumbleLink);
        
        labelMumble.setBounds(defaultX + 95, 165, 20, 20);
        
        textMumble.setBounds(defaultX + 120, 165, 225, 20);
        textMumble.getDocument().addDocumentListener(new UpdateListener());
        
        labelCredits.setBounds(defaultX, 230, (frame.getWidth() - ((frame.getWidth() - 440) / 32) * 2 + 440) / 3, 25);
        panel.add(labelCredits);
    }

}

class UpdateListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        Main.getWindow().update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        Main.getWindow().update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        Main.getWindow().update();
    }
}
class UpdatePathListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        Backgrond.CUSTOM.setPath(Main.getWindow().textPathImage.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        Backgrond.CUSTOM.setPath(Main.getWindow().textPathImage.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        Backgrond.CUSTOM.setPath(Main.getWindow().textPathImage.getText());
    }
}

