package ui;

import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];  // store map
    int x = 3; //space grid position  x,y
    int y = 3;
    boolean flag_game = false;  // flag of the game win or doing
    int step = 0; // the count of the game step

    String pic_s = "animal";

    int pic_c = 1;

    //the window's menu part
    JMenuItem replayItem = new JMenuItem("重开一把");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("QQ");
    JMenu changeItem = new JMenu("更换图片");
    JMenuItem beautigril = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuBar jMenuBar = new JMenuBar();
    JMenu functionJMenu = new JMenu("功能");
    JMenu aboutMenu = new JMenu("关于我们");

    public GameJFrame() {
        initJFrame();
        //initialization
        initJMunebar();
        initdate();
        //initialization
        initImage();
        this.addKeyListener(this);
        this.setVisible(true);
    }

    private void initdate() {
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = i * 4 + j + 1;
            }
        }
        data[3][3] = 0;
        for (int i = 1; i <= 10000; i++) {
            int t = r.nextInt();
            if (t % 4 == 0) {
                if (y != 3) {
                    data[x][y] = data[x][y + 1];
                    data[x][y + 1] = 0;
                    y = y + 1;
                }
            } else if (i % 4 == 1) {
                if (x != 3) {
                    data[x][y] = data[x + 1][y];
                    data[x + 1][y] = 0;
                    x = x + 1;
                }
            } else if (i % 4 == 2) {
                if (y != 0) {
                    data[x][y] = data[x][y - 1];
                    data[x][y - 1] = 0;
                    y = y - 1;
                }
            } else {
                if (x != 0) {
                    data[x][y] = data[x - 1][y];
                    data[x - 1][y] = 0;
                    x = x - 1;
                }
            }
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();
        JLabel stepCount = new JLabel("步数" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        String s = "image/" + pic_s + "/" + pic_s + pic_c + "/";
        String s2 = ".jpg";
        System.out.println(s);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int count = data[i][j];
                String path = s + count + s2;
                JLabel jLabel = new JLabel(new ImageIcon(path));
                jLabel.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(0));
                this.getContentPane().add(jLabel);
                count++;
            }
        }
        JLabel jLabel1 = new JLabel(new ImageIcon("image/background.png"));
        jLabel1.setBounds(40, 40, 508, 560);
        this.getContentPane().add(jLabel1);
        this.getContentPane().repaint(); // refresh
    }

    private void initJMunebar() {

        functionJMenu.add(replayItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(changeItem);
        changeItem.add(sport);
        changeItem.add(beautigril);
        changeItem.add(animal);
        replayItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        sport.addActionListener(this);
        beautigril.addActionListener(this);
        animal.addActionListener(this);
        aboutMenu.add(accountItem);
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutMenu);
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图单机版 V1.0");
        this.setAlwaysOnTop(true);         //运行在最上方
        this.setLocationRelativeTo(null);  //居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //设置默认关闭
        this.getContentPane().setLayout(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            String s = "image/" + pic_s + "/" + pic_s + pic_c + "/";
            String s2 = ".jpg";
            System.out.println(s);
            this.getContentPane().removeAll();
            JLabel jLabel1 = new JLabel(new ImageIcon(s + "all.jpg"));
            jLabel1.setBounds(83, 134, 420, 420);
            this.getContentPane().add(jLabel1);
            JLabel jLabel2 = new JLabel(new ImageIcon("image/background.png"));
            jLabel2.setBounds(40, 40, 508, 560);
            this.getContentPane().add(jLabel2);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.flag_game == true)
            return;
        int code = e.getKeyCode();  // up  = 38 down = 40 left = 37 right = 39
        if (code == 37) {
            System.out.println("LEFT");
            if (y != 3) {
                step++;
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y = y + 1;
                initImage();
            }
        } else if (code == 38) {
            System.out.println("UP");
            if (x != 3) {
                step++;
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x = x + 1;
                initImage();
            }
        } else if (code == 39) {
            System.out.println("RIGHT");
            if (y != 0) {
                step++;
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y = y - 1;
                initImage();
            }
        } else if (code == 40) {
            System.out.println("DOWN");
            if (x != 0) {
                step++;
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x = x - 1;
                initImage();
            }
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {

            for (int i = 0; i <= 3; i++)
                for (int j = 0; j <= 3; j++) {
                    data[i][j] = i * 4 + j + 1;
                }
            data[3][3] = 0;
            initImage();
        }
        if (check()) {
            this.flag_game = true;
            this.getContentPane().removeAll();
            JLabel jLabel3 = new JLabel(new ImageIcon("image/win.png"));
            jLabel3.setBounds(100, 100, 197, 73);
            this.getContentPane().add(jLabel3);
            JLabel jLabel1 = new JLabel(new ImageIcon("image/animal/animal3/all.jpg"));
            jLabel1.setBounds(83, 134, 420, 420);
            this.getContentPane().add(jLabel1);
            JLabel jLabel2 = new JLabel(new ImageIcon("image/background.png"));
            jLabel2.setBounds(40, 40, 508, 560);
            this.getContentPane().add(jLabel2);
            this.getContentPane().repaint();

        }
    }

    public boolean check() {
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i != 3 || j != 3) {
                    if (data[i][j] != i * 4 + j + 1)
                        return false;
                } else {
                    if (data[i][j] != 0)
                        return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == replayItem) {
            System.out.println("重新游戏");
            step = 0;
            x = 3;
            y = 3;
            initdate();
            //初始化图片
            initImage();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("QQ");
            JDialog window = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/img.png"));
            jLabel.setBounds(0, 0, 903, 650);
            window.getContentPane().add(jLabel);
            window.setSize(903, 650);
            window.setAlwaysOnTop(true);
            window.setLocationRelativeTo(null);
            window.setModal(true);
            window.setVisible(true);
        } else if (obj == sport) {
            pic_s = "sport";
            pic_c = (new Random()).nextInt(10) + 1;
            initImage();
        } else if (obj == beautigril) {
            pic_s = "girl";
            pic_c = (new Random()).nextInt(13) + 1;
            initImage();
        } else if (obj == animal) {
            pic_s = "animal";
            pic_c = (new Random()).nextInt(8) + 1;
            initImage();
        }
    }
}
