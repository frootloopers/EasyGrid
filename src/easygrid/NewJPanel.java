/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easygrid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Jia Jia Chen
 */
public class NewJPanel extends javax.swing.JPanel {

    public int[][] Map;
    public int mapX;
    public int mapY;
    public int mouseX;
    public int mouseY;
    public int tileX;
    public int tileY;
    public int colour;
    public boolean[] Combo;
    public static String imported = "";

    PointerInfo pointer;
    Point point;

    //new colours
    float Violet[] = Color.RGBtoHSB(238, 130, 238, null);
    Color VIOLET = Color.getHSBColor(Violet[0], Violet[1], Violet[2]);

    /**
     * Creates new form NewJPanel
     */
    public NewJPanel() {
        System.out.println("MainPanel Initialized.");
        initComponents();
        //
        mapX = 100;
        mapY = 100;
        tileX = 9;
        tileY = 9;
        Map = new int[mapY][mapX];
        Combo = new boolean[3];
        colour = 2;

        //
        for (int x = 0; x < 3; x++) {
            Combo[x] = false;
        }

        //listen to the keyboard
        KeyListener kListener = new KeyListener() {
            //it wont work without this for some reason
            public void keyTyped(KeyEvent key) {
            }

            public void keyPressed(KeyEvent key) {
                switch (key.getKeyCode()) {
                    case KeyEvent.VK_1:
                        System.out.println("KeyEvent/1:Eraser");
                        if (Combo[0] == true) {
                            colour = 10;
                        } else {
                            colour = 0;
                        }
                        break;
                    case KeyEvent.VK_2:
                        System.out.println("KeyEvent/2:Black");
                        if (Combo[0] == true) {
                            colour = 11;
                        } else {
                            colour = 1;
                        }
                        break;
                    case KeyEvent.VK_3:
                        System.out.println("KeyEvent/3:Red");
                        if (Combo[0] == true) {
                            colour = 12;
                        } else {
                            colour = 2;
                        }
                        break;
                    case KeyEvent.VK_4:
                        System.out.println("KeyEvent/4:Blue");
                        if (Combo[0] == true) {
                            colour = 13;
                        } else {
                            colour = 3;
                        }
                        break;
                    case KeyEvent.VK_5:
                        System.out.println("KeyEvent/5:Yellow");
                        if (Combo[0] == true) {
                            colour = 14;
                        } else {
                            colour = 4;
                        }
                        break;
                    case KeyEvent.VK_6:
                        System.out.println("KeyEvent/6:Purple");
                        if (Combo[0] == true) {
                            colour = 15;
                        } else {
                            colour = 5;
                        }
                        break;
                    case KeyEvent.VK_7:
                        System.out.println("KeyEvent/7:Orange");
                        if (Combo[0] == true) {
                            colour = 16;
                        } else {
                            colour = 6;
                        }
                        break;
                    case KeyEvent.VK_8:
                        System.out.println("KeyEvent/8:Green");
                        if (Combo[0] == true) {
                            colour = 17;
                        } else {
                            colour = 7;
                        }
                        break;
                    case KeyEvent.VK_9:
                        System.out.println("KeyEvent/9:Magenta");
                        if (Combo[0] == true) {
                            colour = 18;
                        } else {
                            colour = 8;
                        }
                        break;
                    case KeyEvent.VK_0:
                        System.out.println("KeyEvent/0:Cyan");
                        if (Combo[0] == true) {
                            colour = 19;
                        } else {
                            colour = 9;
                        }
                        break;
                    case KeyEvent.VK_F:
                        FillAll();
                        System.out.println("KeyEvent/f:FillAll");
                        break;
                    case KeyEvent.VK_G:
                        if (tileX == 9) {
                            tileX = 10;
                            tileY = 10;
                        } else {
                            tileX = 9;
                            tileY = 9;
                        }
                        System.out.println("KeyEvent/g:GridToggle");
                        break;
                    case KeyEvent.VK_SHIFT:
                        System.out.println("KeyEvent/shift:Alternate1");
                        Combo[0] = true;
                        break;
                }
            }

            //what happens when you let go
            public void keyReleased(KeyEvent key) {
                switch (key.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        break;
                    case KeyEvent.VK_DOWN:
                        break;
                    case KeyEvent.VK_RIGHT:
                        break;
                    case KeyEvent.VK_LEFT:
                        break;
                    case KeyEvent.VK_SPACE:
                        break;
                    case KeyEvent.VK_SHIFT:
                        System.out.println("KeyEvent/shiftRELEASED:Alternate1Off");
                        Combo[0] = false;
                        break;
                }
            }
        };

        //listen to the mouse
        MouseListener mListener = new MouseListener() {
            public void mouseClicked(MouseEvent ms) {
            }

            public void mouseEntered(MouseEvent ms) {
            }

            public void mouseExited(MouseEvent ms) {
            }

            public void mousePressed(MouseEvent ms) {
                pointer = MouseInfo.getPointerInfo();
                point = ms.getPoint();
                mouseX = point.x;
                mouseY = point.y;
                if (mouseX < mapX * 10 && mouseX >= 0 && mouseY < mapY * 10 && mouseY >= 0) {
                    Map[mouseY / 10][mouseX / 10] = colour;
                }
//                System.out.println(mouseX + "," + mouseY);
            }

            public void mouseReleased(MouseEvent ms) {
            }
        };

        //listen to the mouse more closely
        MouseMotionListener mMListener = new MouseMotionListener() {
            public void mouseDragged(MouseEvent ms) {
                pointer = MouseInfo.getPointerInfo();
                //use .getPoint() or else it gets the point on the computer screen
                point = ms.getPoint();
                mouseX = point.x;
                mouseY = point.y;
                if (mouseX < mapX * 10 && mouseX >= 0 && mouseY < mapY * 10 && mouseY >= 0) {
                    Map[mouseY / 10][mouseX / 10] = colour;
                }
//                System.out.println(mouseX + "," + mouseY);
            }

            public void mouseMoved(MouseEvent ms) {
            }
        };

        addKeyListener(kListener);
        addMouseListener(mListener);
        addMouseMotionListener(mMListener);
        setFocusable(true);

        Timer time = new Timer(34, new TimerListener());
        time.start();
    }

    public void FillAll() {
        Map = new int[mapY][mapX];
        for (int x = 0; x < mapX; x++) {
            for (int y = 0; y < mapY; y++) {
                Map[y][x] = colour;
            }
        }
    }

    public void paintComponent(Graphics g) {

        //paint the background
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 1000, 1000);
        //

        //paint the map
        for (int y = 0; y < mapY; y++) {
            for (int x = 0; x < mapX; x++) {
                switch (Map[y][x]) {
                    case 0:
                        g.setColor(Color.WHITE);
                        break;
                    case 1:
                        g.setColor(Color.BLACK);
                        break;
                    case 2:
                        g.setColor(Color.RED);
                        break;
                    case 3:
                        g.setColor(Color.BLUE);
                        break;
                    case 4:
                        g.setColor(Color.YELLOW);
                        break;
                    case 5:
                        g.setColor(VIOLET);
                        break;
                    case 6:
                        g.setColor(Color.ORANGE);
                        break;
                    case 7:
                        g.setColor(Color.GREEN);
                        break;
                    case 8:
                        //
                        g.setColor(Color.MAGENTA);
                        break;
                    case 9:
                        //
                        g.setColor(Color.CYAN);
                        break;
                    case 10:
                        g.setColor(Color.GRAY);
                        break;
                    case 11:
                        g.setColor(Color.DARK_GRAY);
                        break;
                    case 12:
                        g.setColor(Color.PINK);
                        break;
                    case 13:
                        g.setColor(Color.CYAN);
                        break;
                    case 14:
                        //
                        g.setColor(Color.WHITE);
                        break;
                    case 15:
                        //
                        g.setColor(Color.WHITE);
                        break;
                    case 16:
                        //
                        g.setColor(Color.WHITE);
                        break;
                    case 17:
                        //
                        g.setColor(Color.WHITE);
                        break;
                    case 18:
                        //
                        g.setColor(Color.WHITE);
                        break;
                    case 19:
                        //
                        g.setColor(Color.WHITE);
                        break;
                    default:
                        System.out.println("illegal colour detected at " + y + "," + x);
                        g.setColor(Color.WHITE);
                        break;
                }
                g.fillRect(x * 10, y * 10, tileX, tileY);
            }
        }
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
//            System.out.println(counter);
            //do changes

            //refresh the frame
            repaint();
        }
    }

    /**
     * Saves the current array
     *
     * @param Position The array your saving from
     */
    public static void FileSaver(boolean[][] Position) {
        try {
            String toSave = "xxxxxx";
            //make sure its three digits
            DecimalFormat three = new DecimalFormat("000");
            for (int y = 0; y < 150; y++) {
                for (int x = 0; x < 150; x++) {
                    if (Position[y][x] == true) {
                        System.out.println(three.format(x) + "," + three.format(y));
                        toSave = toSave.concat(three.format(y));
                        toSave = toSave.concat(three.format(x));
                    }
                }
            }
            //load what was already saved
            FileLoader();
//            System.out.println("Saved: " + toSave);
            //print it all on the text file
            PrintWriter fileOutput = new PrintWriter(new FileWriter("saves.txt"));
            fileOutput.print(imported + toSave);
            //make sure to close properly
            fileOutput.close();
        } catch (Exception e) {
            System.out.println("Error, save failed.");
        }
    }

    /**
     * Loads an array from a save as a string
     */
    public static void FileLoader() {
        try {
            Scanner fileInput = new Scanner(new FileReader("saves.txt"));
            imported = fileInput.nextLine();
//            System.out.println(imported);
            fileInput.close();
        } catch (Exception e) {
            System.out.println("Error, load failed.");
        }
    }

    /**
     * Builds an array from loaded data
     *
     * @param loadPos Which divider you want to load from
     * @param Position Get the array you want to change
     * @return Returns the array after writing the load info
     */
    public static boolean[][] ArrayBuilder(int loadPos, boolean[][] Position) {
        int currentPos = 0;

        //clear all
        for (int y = 0; y < 150; y++) {
            for (int x = 0; x < 150; x++) {
                Position[y][x] = false;
            }
        }

        try {
            for (int x = 0; x < imported.length(); x += 6) {
                if (currentPos > loadPos) {
                    break;
                }
                if (imported.charAt(x) == 'x') {
                    currentPos++;
                } else if (loadPos == currentPos) {
                    Position[(int) (imported.charAt(x) - 48) * 100 + (int) (imported.charAt(x + 1) - 48) * 10 + (int) (imported.charAt(x + 2) - 48)][(int) (imported.charAt(x + 3) - 48) * 100 + (int) (imported.charAt(x + 4) - 48) * 10 + (int) (imported.charAt(x + 5) - 48)] = true;
                }
            }
        } catch (Exception e) {
            System.out.print("Error binding to array. Make sure stored arrays are seperated by 'xxxxxx'. One of these should also be at the beginning. The number of digits stored in each stored array should be a multiple of 6.");
        }

        return Position;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
