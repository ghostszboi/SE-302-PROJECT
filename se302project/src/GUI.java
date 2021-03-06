import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI extends Component implements ActionListener {

    JFrame mainFrame;
    JFrame helpFrame;
    JLabel helpLabel;
    JPanel addPanel;

    JTextField adField;
    JTextField soyadField;
    JTextField doğumField;
    JTextField idField;

    JLabel başlık;
    JLabel adLabel;
    JLabel soyadLabel;
    JLabel doğumLabel;
    JLabel idLabel;
    JLabel genderLabel;
    JLabel akrabaLabel;

    JButton addButton;
    JButton removeButton;

    JMenuBar menuBar;
    JMenu file,edit,help;
    JMenuItem exit,save,about,open;


    JComboBox cinsiyetBox;
    String[] cinsiyet = {"Kadın", "Erkek"};
    JComboBox akrabaBox;
    String[] akraba = {"Ben", "Baba", "Anne", "Eş", "Kardeş", "Çocuk"};

    JTree FamilyTree;
    DefaultMutableTreeNode reference;
    DefaultMutableTreeNode parents;
    DefaultMutableTreeNode spouse;
    DefaultMutableTreeNode children;
    DefaultMutableTreeNode broSis;
    DefaultMutableTreeNode mother;
    DefaultMutableTreeNode father;



    GUI() {
        //Mainframe name.
        mainFrame = new JFrame("Family Tree");
        file = new JMenu("File");
        file.setFont(new Font("Arial", Font.BOLD, 13));
        edit = new JMenu("Edit");
        edit.setFont(new Font("Arial", Font.BOLD, 13));
        help = new JMenu("Help");
        help.setFont(new Font("Arial", Font.BOLD, 13));
        about = new JMenuItem("About");
        about.addActionListener(this);
        about.setFont(new Font("Arial", Font.BOLD, 13));
        save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setFont(new Font("Arial", Font.BOLD, 13));
        open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setFont(new Font("Arial", Font.BOLD, 13));
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setFont(new Font("Arial", Font.BOLD, 13));


        //Addpanel settings.
        addPanel = new JPanel();
        addPanel.setBounds(750, 20, 550, 680);
        addPanel.setBackground(Color.lightGray);

        //All field settings.
        başlık = new JLabel("Family Tree");
        başlık.setBounds(10, 10 , 200,30);
        başlık.setFont(new Font("Verdana", Font.PLAIN, 24));
        adField = new JTextField();
        soyadField = new JTextField();
        doğumField = new JTextField();
        idField = new JTextField();
        adField.setBounds(1000, 50, 250, 25);
        adLabel = new JLabel("Adı: ");
        adLabel.setBounds(800, 50, 50, 30);
        adLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        soyadField.setBounds(1000, 130, 250, 25);
        soyadLabel = new JLabel("Soyadı: ");
        soyadLabel.setBounds(800, 130, 90, 30);
        soyadLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        doğumField.setBounds(1000, 210, 250, 25);
        doğumLabel = new JLabel("Doğum Yılı: ");
        doğumLabel.setBounds(800, 210, 160, 30);
        doğumLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        idField.setBounds(1000, 290, 250, 25);
        idLabel = new JLabel("ID: ");
        idLabel.setBounds(800, 290, 160, 30);
        idLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        genderLabel = new JLabel("Cinsiyet: ");
        genderLabel.setBounds(800, 400, 160, 30);
        genderLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        akrabaLabel = new JLabel("Akraba: ");
        akrabaLabel.setBounds(800, 500, 160, 30);
        akrabaLabel.setFont(new Font("Verdana", Font.PLAIN, 18));



        cinsiyetBox = new JComboBox(cinsiyet);
        cinsiyetBox.setFont(new Font("Verdana", Font.PLAIN, 18));
        cinsiyetBox.setBounds(1000, 400, 200, 25);

        akrabaBox = new JComboBox(akraba);
        akrabaBox.setFont(new Font("Verdana", Font.PLAIN, 18));
        akrabaBox.setBounds(1000, 500, 200, 30);


        addButton = new JButton("Kişi Ekle");
        addButton.setBounds(800, 630, 180, 50);
        addButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        addButton.addActionListener(this);

        removeButton = new JButton("Kişi Kaldır");
        removeButton.setBounds(1070, 630, 180, 50);
        removeButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        removeButton.addActionListener(this);


        reference = new DefaultMutableTreeNode("Reference");
        FamilyTree = new JTree(reference);
        FamilyTree.setBounds(20, 50, 500, 650);

        parents = new DefaultMutableTreeNode("Ebeveynler");
        spouse = new DefaultMutableTreeNode("Eş");
        children = new DefaultMutableTreeNode("Çocuklar");
        broSis = new DefaultMutableTreeNode("Kardeşler");
        mother = new DefaultMutableTreeNode("Anne");
        father = new DefaultMutableTreeNode("Baba");

        //idField need to be integer.
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        //doğumField need to be integer.
        doğumField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        //adField need to be string.
        adField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)){
                e.consume();
            }
            }
        });
        //soyadField need to be string.
        soyadField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)){
                    e.consume();
                }
            }
        });

        helpFrame = new JFrame("App Help");
        helpLabel = new JLabel("<html>  Merhabalar program basit bir aile soyağacı programıdır." +
                "Programa kendinizi(referans) ekleyerek başlayabilirsiniz. Kendinizi eklemek için önce bütün boş alanları doldurmalısınız." +
                " Doldurduktan sonra Akraba kısmından BEN kısmını seçip KİŞİ EKLE'ye bastığınız zaman solda yukarıda kendinizi göreceksiniz.<br><br>" +
                " Kendinizi ekledikten sonra aile soyağacına akraba kısmından istediğiniz kişiyi ekleyebilirsiniz. Eklediğiniz kişiler aynı" +
                " prosedürler uygulandıktan sonra ait oldukları klasörlerin içine dolmaya başlayacaktır. Klasörlere tıklayarak eklenen kişiyi" +
                " kolaylıkla görebilirsiniz.<br><br>Ekstradan soyağacından ANNE yazısının üstüne tıklayıp KARDEŞ,ANNE,BABA eklerseniz amcanızı, halanızı, dedenizi vb." +
                " aile üyelerinizi ANNE klasörünün altında görebilirsiniz. Aynı durum BABA içinde geçerlidir. EŞ seçili ise anne ve baba ekleyebilirsiniz." +
                " Eklediğiniz anne ve baba EŞ'in altında gözükecektir.<br><br> ~Family Tree~ <html>");

        helpLabel.setBounds(10, 0, 370,340);
        helpFrame.add(helpLabel);

        reference.add(parents);
        reference.add(spouse);
        reference.add(broSis);
        reference.add(children);
        parents.add(mother);
        parents.add(father);

        menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setSize(1366 , 768);
        mainFrame.setVisible(true);


        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);
        help.add(about);
        file.add(save);
        file.add(open);
        file.add(exit);
        mainFrame.add(başlık);
        mainFrame.add(adField);
        mainFrame.add(adLabel);
        mainFrame.add(soyadField);
        mainFrame.add(soyadLabel);
        mainFrame.add(doğumField);
        mainFrame.add(doğumLabel);
        mainFrame.add(idField);
        mainFrame.add(idLabel);
        mainFrame.add(genderLabel);
        mainFrame.add(akrabaLabel);
        mainFrame.add(addButton);
        mainFrame.add(removeButton);
        mainFrame.add(cinsiyetBox);
        mainFrame.add(akrabaBox);
        mainFrame.add(FamilyTree);
        mainFrame.add(addPanel);

    }
    public void resetField(){
        adField.setText("");
        soyadField.setText("");
        doğumField.setText("");
        idField.setText("");
        cinsiyetBox.setSelectedItem(0);
        akrabaBox.setSelectedItem(0);
    }
    public void exceptionHandler() {

        if (idField.getText().equals("")) {
            JOptionPane idHata = new JOptionPane();
            idHata.showMessageDialog(null, "ID girilmedi.");
            throw new StringIndexOutOfBoundsException("HATA");

        } else if (adField.getText().equals("")) {
            JOptionPane kişiHata = new JOptionPane();
            kişiHata.showMessageDialog(null, "Kişi adı girilmedi.");
            throw new StringIndexOutOfBoundsException("HATA");
        } else if (soyadField.getText().equals("")) {
            JOptionPane soyadHata = new JOptionPane();
            soyadHata.showMessageDialog(null, "Soyadı girilmedi.");
            throw new StringIndexOutOfBoundsException("HATA");
        } else {
            JOptionPane success = new JOptionPane();
            success.showMessageDialog(null, "Bilgileriniz kaydedilmiştir.");

        }
    }




    public static void main(String[] args) {
        new GUI();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            System.exit(0);
        }


        if (e.getSource() == about) {
            helpFrame.setLayout(null);
            helpFrame.setSize(400,400);
            helpFrame.setVisible(true);

        }


        if (e.getSource() == removeButton) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) FamilyTree.getSelectionPath().getLastPathComponent();
            if (selectedNode != parents && selectedNode != spouse && selectedNode != broSis && selectedNode != children) {

                DefaultTreeModel model = (DefaultTreeModel) FamilyTree.getModel();
                model.removeNodeFromParent(selectedNode);
                model.reload();
            }
        }
        //Referans
        if (e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 0)) {
            exceptionHandler();
                DefaultTreeModel model = (DefaultTreeModel) FamilyTree.getModel();

                reference.setUserObject(adField.getText() + " " + soyadField.getText() + " ID:" + idField.getText() + " (Referans) ");
                model.nodeChanged(reference);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();


            }

        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) FamilyTree.getSelectionPath().getLastPathComponent();
        if  (selectedNode != spouse && e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 1) && selectedNode != father && selectedNode != mother ) {
            if (idField.getText().equals("")) {
                JOptionPane idHata = new JOptionPane();
                idHata.showMessageDialog(null, "ID girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else if (adField.getText().equals("")) {
                JOptionPane kişiHata = new JOptionPane();
                kişiHata.showMessageDialog(null, "Kişi adı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else if (soyadField.getText().equals("")) {
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Soyadı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else if (cinsiyetBox.getSelectedIndex() == 0) {
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Cinsiyeti kontrol edin.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else {
                JOptionPane succes = new JOptionPane();
                succes.showMessageDialog(null, "Bilgileriniz kaydedilmiştir.");

                father.setUserObject(adField.getText() + " " + soyadField.getText() + " (Baba) ");
                parents.add(father);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();

            }
        }
        if (cinsiyetBox.getSelectedIndex() == 1 && e.getSource() == addButton && selectedNode == father  && akrabaBox.getSelectedIndex() == 1) {
            exceptionHandler();


                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Dede) ");
                father.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();
            }

        if (e.getSource() == addButton && selectedNode == father && cinsiyetBox.getSelectedIndex() == 0 && akrabaBox.getSelectedIndex() == 2) {
            exceptionHandler();


                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Babaanne) ");
                father.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();
            }

        if (e.getSource() == addButton && selectedNode == father && akrabaBox.getSelectedIndex() == 4 && cinsiyetBox.getSelectedIndex() == 1 ) {
            exceptionHandler();


                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Amca) ");
                father.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();
            }

        if (selectedNode == father && akrabaBox.getSelectedIndex() == 4 && e.getSource() == addButton && cinsiyetBox.getSelectedIndex() == 0) {
            exceptionHandler();



                    DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Hala) ");
                    father.add(tempNode);
                    SwingUtilities.updateComponentTreeUI(mainFrame);
                    resetField();
            }


            if (e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 2) && selectedNode != mother && selectedNode != spouse && selectedNode != father) {
                if (idField.getText().equals("")) {
                    JOptionPane idHata = new JOptionPane();
                    idHata.showMessageDialog(null, "ID girilmedi.");
                    throw new StringIndexOutOfBoundsException("HATA");

                } else if (adField.getText().equals("")) {
                    JOptionPane kişiHata = new JOptionPane();
                    kişiHata.showMessageDialog(null, "Kişi adı girilmedi.");
                    throw new StringIndexOutOfBoundsException("HATA");
                } else if (soyadField.getText().equals("")) {
                    JOptionPane soyadHata = new JOptionPane();
                    soyadHata.showMessageDialog(null, "Soyadı girilmedi.");
                    throw new StringIndexOutOfBoundsException("HATA");

                } else if (cinsiyetBox.getSelectedIndex() == 1) {
                    JOptionPane soyadHata = new JOptionPane();
                    soyadHata.showMessageDialog(null, "Cinsiyeti kontrol edin.");
                    throw new StringIndexOutOfBoundsException("HATA");
                } else {
                    JOptionPane succes = new JOptionPane();
                    succes.showMessageDialog(null, "Bilgileriniz kaydedilmiştir.");


                    mother.setUserObject(adField.getText() + " " + soyadField.getText() + " (Anne) ");
                    parents.add(mother);
                    SwingUtilities.updateComponentTreeUI(mainFrame);
                    resetField();
                }
            }

        if (e.getSource() == addButton && selectedNode == mother && cinsiyetBox.getSelectedIndex() == 1 && akrabaBox.getSelectedIndex() == 1) {
            exceptionHandler();


            DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Dede) ");
            mother.add(tempNode);
            SwingUtilities.updateComponentTreeUI(mainFrame);
            resetField();
            }

        if (e.getSource() == addButton && selectedNode == mother && cinsiyetBox.getSelectedIndex() == 0 && akrabaBox.getSelectedIndex() == 2) {
            exceptionHandler();


            DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Anneanne) ");
            mother.add(tempNode);
            SwingUtilities.updateComponentTreeUI(mainFrame);
            resetField();
            }

        if (e.getSource() == addButton && selectedNode == mother && akrabaBox.getSelectedIndex() == 4 && cinsiyetBox.getSelectedIndex() == 1) {
            exceptionHandler();


            DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Dayı) ");
            mother.add(tempNode);
            SwingUtilities.updateComponentTreeUI(mainFrame);
            resetField();
            }

        if (e.getSource() == addButton && selectedNode == mother && akrabaBox.getSelectedIndex() == 4 && cinsiyetBox.getSelectedIndex() == 0) {
            exceptionHandler();


            DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Teyze) ");
            mother.add(tempNode);
            SwingUtilities.updateComponentTreeUI(mainFrame);
            resetField();
            }

        //Eş
        if (e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 3)) {

            if (idField.getText().equals("")) {
                JOptionPane idHata = new JOptionPane();
                idHata.showMessageDialog(null, "ID girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else if (adField.getText().equals("")) {
                JOptionPane kişiHata = new JOptionPane();
                kişiHata.showMessageDialog(null, "Kişi adı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else if (soyadField.getText().equals("")) {
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Soyadı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else if (selectedNode == mother || selectedNode == father) {
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Anne veya Baba seçiliyken ekleme yapamazsınız.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else {
                JOptionPane succes = new JOptionPane();
                succes.showMessageDialog(null, "Bilgileriniz kaydedilmiştir.");

                spouse.setUserObject(adField.getText() + " " + soyadField.getText() + " (Eş) ");
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();

            }
        }
            if(e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 1) && cinsiyetBox.getSelectedIndex() == 1 && selectedNode == spouse){
                exceptionHandler();
                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Kayınpeder) ");
                spouse.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();

            }
            if(e.getSource() == addButton && selectedNode == spouse && cinsiyetBox.getSelectedIndex() == 0 && akrabaBox.getSelectedIndex() == 2){
                exceptionHandler();
                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Kayınvalide) ");
                spouse.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();

            }

        //Kız Kardeş
        if (e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 4) && (cinsiyetBox.getSelectedIndex() == 0) && selectedNode != mother && selectedNode != father){

            if (idField.getText().equals("")) {
                JOptionPane idHata = new JOptionPane();
                idHata.showMessageDialog(null, "ID girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else if (adField.getText().equals("")) {
                JOptionPane kişiHata = new JOptionPane();
                kişiHata.showMessageDialog(null, "Kişi adı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else if (soyadField.getText().equals("")) {
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Soyadı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else {
                JOptionPane succes = new JOptionPane();
                succes.showMessageDialog(null, "Bilgileriniz kaydedilmiştir.");

                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Kız Kardeş) ");
                broSis.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();
            }
        }
        // Erkek Kardeş
        else if(e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 4) && (cinsiyetBox.getSelectedIndex() == 1) && selectedNode != mother && selectedNode != father){
            if (idField.getText().equals("")) {
                JOptionPane idHata = new JOptionPane();
                idHata.showMessageDialog(null, "ID girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else if (adField.getText().equals("")) {
                JOptionPane kişiHata = new JOptionPane();
                kişiHata.showMessageDialog(null, "Kişi adı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else if (soyadField.getText().equals("")) {
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Soyadı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else {
                JOptionPane succes = new JOptionPane();
                succes.showMessageDialog(null, "Bilgileriniz kaydedilmiştir.");

                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Erkek Kardeş) ");
                broSis.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();
            }

        }
        //Kız Çocuk
        if (e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 5) && (cinsiyetBox.getSelectedIndex() == 0))  {

            if (idField.getText().equals("")) {
                JOptionPane idHata = new JOptionPane();
                idHata.showMessageDialog(null, "ID girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else if (adField.getText().equals("")) {
                JOptionPane kişiHata = new JOptionPane();
                kişiHata.showMessageDialog(null, "Kişi adı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else if (soyadField.getText().equals("")) {
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Soyadı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else if (selectedNode == mother || selectedNode == father){
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Anne veya Baba seçiliyken ekleme yapamazsınız.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else {
                JOptionPane succes = new JOptionPane();
                succes.showMessageDialog(null, "Bilgileriniz kaydedilmiştir.");

                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Kız Çocuk) ");
                children.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();
            }
        }
        //Erkek Çocuk
        else if(e.getSource() == addButton && (akrabaBox.getSelectedIndex() == 5) && (cinsiyetBox.getSelectedIndex() == 1)){
            if (idField.getText().equals("")) {
                JOptionPane idHata = new JOptionPane();
                idHata.showMessageDialog(null, "ID girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else if (adField.getText().equals("")) {
                JOptionPane kişiHata = new JOptionPane();
                kişiHata.showMessageDialog(null, "Kişi adı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else if (soyadField.getText().equals("")) {
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Soyadı girilmedi.");
                throw new StringIndexOutOfBoundsException("HATA");
            } else if (selectedNode == mother || selectedNode == father){
                JOptionPane soyadHata = new JOptionPane();
                soyadHata.showMessageDialog(null, "Anne veya Baba seçiliyken ekleme yapamazsınız.");
                throw new StringIndexOutOfBoundsException("HATA");

            } else {
                JOptionPane succes = new JOptionPane();
                succes.showMessageDialog(null, "Bilgileriniz kaydedilmiştir.");

                DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(adField.getText() + " " + soyadField.getText() + " (Erkek Çocuk) ");
                children.add(tempNode);
                SwingUtilities.updateComponentTreeUI(mainFrame);
                resetField();
            }

        }



        }
    }





