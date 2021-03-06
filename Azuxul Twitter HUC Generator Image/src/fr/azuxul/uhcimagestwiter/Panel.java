package fr.azuxul.uhcimagestwiter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azuxul on 09/08/2015.
 */
@SuppressWarnings("serial")
public class Panel extends JPanel {

    public void paintComponent(Graphics g){

        Image img = null;
        Image alogo = null;
        Image vlogo = null;
        String name = Main.getWindow().textName.getText().length() <= 0?"NAME":Main.getWindow().textName.getText();
        String team = Main.getWindow().textTeam.getText().length() <=0?"FFA":Main.getWindow().textTeam.getText().equalsIgnoreCase("RVB")?"RvB":"FFA";
        String date = Main.getWindow().textDate.getText().length() <= 0?"DATE":Main.getWindow().textDate.getText();
        String sc1 = Main.getWindow().textSc.getText().length() <= 0?"SCENARIOS":Main.getWindow().textSc.getText();
        String sc2 = "";
        String ip = Main.getWindow().textIp.getText().length() <= 0?"IP":Main.getWindow().textIp.getText();
        String open = Main.getWindow().textOpen.getText().length() <= 0?"OPENTIME":Main.getWindow().textOpen.getText();
        String start = Main.getWindow().textStart.getText().length() <= 0?"STARTTIME":Main.getWindow().textStart.getText();
        String mumble = Main.getWindow().textMumble.getText().length() <= 0?"MUMBLE IP":Main.getWindow().textMumble.getText();

        try{
            team = Integer.parseInt(Main.getWindow().textTeam.getText()) <= 0?"FFA":"To"+Main.getWindow().textTeam.getText();
        }
        catch (Exception ignored){}
        
        try {
        	Backgrond b = Backgrond.getBackgrond((String) Main.getWindow().listBackground.getSelectedItem());
        	
        	if(!b.getName().equalsIgnoreCase(Backgrond.CUSTOM.getName()))
        	{img = ImageIO.read(Main.class.getResource(b.getPath()));}
        	else
        	{try{img = ImageIO.read(new File(b.getPath()));}catch (IOException e) {}}
        	
            alogo = ImageIO.read(Main.class.getResource("/assets/textures/azuxulLogo.png"));
            vlogo = ImageIO.read(Main.class.getResource("/assets/textures/valgriferLogo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        };

        if(sc1.length() >= 33){

            List<Integer> charI = new ArrayList<>();
            int mI = 0;

            for(int i = sc1.length() - 1; i >= 0; i--){

                if(sc1.charAt(i) == '-'){
                    charI.add(i);
                }
            }

            for(Integer i:charI){
                if(i <= 34 && i > mI){

                    mI = i;
                }
            }

            sc2 = sc1.substring(mI + 1, sc1.length());
            sc1 = sc1.substring(0, mI + 1);
        }
        
        int y = (Main.getWindow().checkboxMumbleLink.isSelected() ? 240 : 220);

        BufferedImage bufferedImage = new BufferedImage(440, y, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2D = bufferedImage.createGraphics();
        g2D.drawImage(img, 0, 0, bufferedImage.getWidth() * (bufferedImage.getWidth() * 100 / 440) / 100, bufferedImage.getHeight() * (bufferedImage.getHeight() * 100 / 220) / 100, this);

        g2D.setColor(new Color(55, 55, 55, 179));
        g2D.fillRoundRect(10, 10, 440 - 20, y - 20, 3, 3);

        g2D.setFont(new Font("Sans", 0, 10));
        g2D.setColor(Color.WHITE);
        g2D.drawString("Azuxul & Valgrifer", 340, y-15);
        g2D.setFont(new Font(g2D.getFont().getName(), g2D.getFont().getStyle(), 18));
        g2D.drawString(name + " " + team + " - " + date, 25, 35);
        g2D.drawString("- ScÚnarios: " +  sc1 , 25, 80); //Max char 34
        g2D.drawString(sc2, 25, 105);
        g2D.drawString("- Ip: " + ip, 25, 130);
        g2D.drawString("- Ouverture: " + open, 25, 155);
        g2D.drawString("- Start: " + start, 25, 180);
        
        if(Main.getWindow().checkboxMumbleLink.isSelected())
        {
            g2D.drawString("- Mumble Link IP: " + mumble, 25, 205);
        }

        int imgX = (this.getWidth() - bufferedImage.getWidth())/32, imgY = (this.getHeight() - bufferedImage.getHeight())/2;
        g.drawImage(bufferedImage, imgX, imgY, this);
        g.drawImage(alogo, 855, 75, 155, 155, this);
        g.drawImage(vlogo, 875, 0, 110, 110, this);

        if(Main.save){

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
            fileChooser.setSelectedFile(new File("uhc.png"));
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    try {
                    	if(f.isDirectory())return true;
                    	if(f.getName().substring((int) (f.getName().length() - 4)).equalsIgnoreCase(".lnk"))return true;
                    	if(f.getName().substring((int) (f.getName().length() - 4)).equalsIgnoreCase(".png"))return true;
                    } catch (Exception e) {
                        return false;
                    }
					return false;
                }

                @Override
                public String getDescription() {
                    return "Image png (.png)";
                }
            });
            fileChooser.showSaveDialog(this);

            try {
                ImageIO.write(bufferedImage, "PNG", fileChooser.getSelectedFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Main.save = false;
        }
    }
}
