import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.text.Caret;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse("cars.xml");
        Element root =  doc.getDocumentElement();
        NodeList cars = root.getElementsByTagName("car");

        JFrame mainFrame = new JFrame("Cars");
        mainFrame.setSize(350, 200);
        mainFrame.setLocation(720, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridBagLayout());

        JLabel jLabel = new JLabel("search by...");
        JButton buttonManufacturer = new JButton("manufacturer");
        buttonManufacturer.setPreferredSize(new Dimension(120, 25));
        JButton buttonYear = new JButton("year");
        buttonYear.setPreferredSize(new Dimension(120, 25));
        JButton buttonPowered = new JButton("powered");
        buttonPowered.setPreferredSize(new Dimension(120, 25));
        JButton close = new JButton("close");

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,20);
        mainFrame.add(jLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,0,15,0);
        mainFrame.add(buttonManufacturer, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0,0,15,0);
        mainFrame.add(buttonYear, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        mainFrame.add(buttonPowered, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,0,0,20);
        mainFrame.add(close, c);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonManufacturer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame manufacturerFrame = new JFrame("Manufacturer");
                manufacturerFrame.setSize(350, 200);
                manufacturerFrame.setLocation(720, 250);
                manufacturerFrame.setLayout(new GridBagLayout());

                JComboBox jComboBox = new JComboBox();
                jComboBox.addItem("Audi");
                jComboBox.addItem("Chevrolet");
                jComboBox.addItem("Aston Martin");
                jComboBox.addItem("Volkswagen");
                jComboBox.addItem("Kia");
                jComboBox.addItem("Mazda");
                jComboBox.addItem("BMW");
                jComboBox.addItem("Toyota");
                jComboBox.addItem("Dacia");
                jComboBox.addItem("Fiat");
                jComboBox.addItem("Ford");

                JButton get = new JButton("get");
                JButton close = new JButton("close");

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                manufacturerFrame.add(jComboBox, c);

                c = new GridBagConstraints();
                c.gridx = 1;
                c.gridy = 0;
                c.insets = new Insets(0,10,0,0);
                manufacturerFrame.add(get, c);

                c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(20,0,0,0);
                manufacturerFrame.add(close, c);

                close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manufacturerFrame.dispose();
                    }
                });

                get.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String selectedCar = (String) jComboBox.getSelectedItem();
                        StringBuilder selectedCars = new StringBuilder();

                        for (int i = 0; i < cars.getLength(); i++) {
                            Node carNode = cars.item(i);
                            Element element = (Element) carNode;

                            String car = element.getElementsByTagName("manufacturer").item(0).getTextContent();
                            if (car.equals(selectedCar)) {
                                String id = "id: " + element.getAttribute("id");
                                String manufacturer = "manufacturer: " + car;
                                String model = "model: " + element.getElementsByTagName("model").item(0).getTextContent();
                                String productionYear = "production-year: " + element.getElementsByTagName("production-year").item(0).getTextContent();
                                String horsepower = "horsepower: " + element.getElementsByTagName("horsepower").item(0).getTextContent();
                                String powered = "powered by: " + element.getElementsByTagName("consumption").item(0).getAttributes().item(0).getNodeValue();
                                String consumption = "consumption: " + element.getElementsByTagName("consumption").item(0).getTextContent();
                                String price = "price: " + element.getElementsByTagName("price").item(0).getTextContent();

                                selectedCars.append(id).append("\n").append(manufacturer).append("\n")
                                        .append(model).append("\n").append(productionYear).append("\n").append(horsepower).append("\n").append(powered)
                                        .append("\n").append(consumption).append("\n").append(price).append("\n\n");
                            }
                        }

                        JFrame jFrame = new JFrame();
                        jFrame.setSize(300, 320);
                        jFrame.setLocation(720, 300);
                        JTextArea jTextArea = new JTextArea(10, 20);
                        jFrame.add(jTextArea);
                        jTextArea.setWrapStyleWord(true);
                        jTextArea.setLineWrap(true);
                        JScrollPane jScrollPane = new JScrollPane(jTextArea);
                        jFrame.add(jScrollPane);
                        jTextArea.append(selectedCars.toString());
                        jTextArea.getCaret().setDot(0);
                        jFrame.setVisible(true);
                    }
                });
                manufacturerFrame.setVisible(true);
            }
        });

        buttonYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame yearFrame = new JFrame("Year");
                yearFrame.setSize(350, 200);
                yearFrame.setLocation(720, 250);
                yearFrame.setLayout(new GridBagLayout());

                JTextField year1 = new JTextField(4);
                JTextField year2 = new JTextField(4);
                JButton get = new JButton("get");
                get.setPreferredSize(new Dimension(80, 25));
                JLabel jLabel1 = new JLabel("min year");
                JLabel jLabel2 = new JLabel("max year");
                JButton close = new JButton("close");
                close.setPreferredSize(new Dimension(80, 25));

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(0,0,0,15);
                yearFrame.add(jLabel1, c);

                c = new GridBagConstraints();
                c.gridx = 1;
                c.gridy = 0;
                yearFrame.add(year1, c);

                c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(0,0,0,15);
                yearFrame.add(jLabel2, c);

                c = new GridBagConstraints();
                c.gridx = 1;
                c.gridy = 1;
                yearFrame.add(year2, c);

                c = new GridBagConstraints();
                c.gridx = 2;
                c.gridy = 0;
                c.insets = new Insets(0,15,5,0);
                yearFrame.add(get, c);

                c = new GridBagConstraints();
                c.gridx = 2;
                c.gridy = 1;
                c.insets = new Insets(5,15,0,0);
                yearFrame.add(close, c);

                close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        yearFrame.dispose();
                    }
                });

                get.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int selectedYear1 = Integer.parseInt(year1.getText());
                        int selectedYear2 = Integer.parseInt(year2.getText());
                        StringBuilder selectedCars = new StringBuilder();

                        for (int i = 0; i < cars.getLength(); i++) {

                            Node car = cars.item(i);
                            Element element = (Element) car;

                            int year = Integer.parseInt(element.getElementsByTagName("production-year").item(0).getTextContent());
                            if (selectedYear1 <= year && selectedYear2 >= year) {
                                String id = "id: " + element.getAttribute("id");
                                String manufacturer = "manufacturer: " + element.getElementsByTagName("manufacturer").item(0).getTextContent();
                                String model = "model: " + element.getElementsByTagName("model").item(0).getTextContent();
                                String productionYear = "production-year: " + year;
                                String horsepower = "horsepower: " + element.getElementsByTagName("horsepower").item(0).getTextContent();
                                String powered = "powered by: " + element.getElementsByTagName("consumption").item(0).getAttributes().item(0).getNodeValue();
                                String consumption = "consumption: " + element.getElementsByTagName("consumption").item(0).getTextContent();
                                String price = "price: " + element.getElementsByTagName("price").item(0).getTextContent();

                                selectedCars.append(id).append("\n")
                                        .append(manufacturer).append("\n").append(model).append("\n").append(productionYear).append("\n").append(horsepower)
                                        .append("\n").append(powered).append("\n").append(consumption).append("\n").append(price).append("\n\n");
                            }
                        }
                        JFrame jFrame = new JFrame();
                        jFrame.setSize(300, 320);
                        jFrame.setLocation(720, 300);
                        JTextArea jTextArea = new JTextArea(10, 20);
                        jFrame.add(jTextArea);
                        jTextArea.setWrapStyleWord(true);
                        jTextArea.setLineWrap(true);
                        JScrollPane jScrollPane = new JScrollPane(jTextArea);
                        jFrame.add(jScrollPane);
                        jTextArea.append(selectedCars.toString());
                        jTextArea.getCaret().setDot(0);
                        jFrame.setVisible(true);
                    }
                });
                yearFrame.setVisible(true);
            }
        });

        buttonPowered.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame poweredFrame = new JFrame("Powered");
                poweredFrame.setSize(350, 200);
                poweredFrame.setLocation(720, 250);
                poweredFrame.setLayout(new GridBagLayout());

                JComboBox jComboBox = new JComboBox();
                jComboBox.addItem("Electric");
                jComboBox.addItem("Fuel or Hybrid");

                JButton get = new JButton("get");
                JButton close = new JButton("close");

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                poweredFrame.add(jComboBox, c);

                c = new GridBagConstraints();
                c.gridx = 1;
                c.gridy = 0;
                c.insets = new Insets(0,10,0,0);
                poweredFrame.add(get, c);

                c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(20,0,0,0);
                poweredFrame.add(close, c);

                close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        poweredFrame.dispose();
                    }
                });

                get.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String selectedPower = (String) jComboBox.getSelectedItem();
                        StringBuilder selectedCars = new StringBuilder();
                        if (selectedPower.equals("Electric")) {
                            for (int i = 0; i < cars.getLength(); i++) {
                                Node carNode = cars.item(i);
                                Element element = (Element) carNode;

                                String poweredBy = element.getElementsByTagName("consumption").item(0).getAttributes().item(0).getNodeValue();
                                if (poweredBy.equals("electric")) {
                                    String id = "id: " + element.getAttribute("id");
                                    String manufacturer = "manufacturer: " + element.getElementsByTagName("manufacturer").item(0).getTextContent();
                                    String model = "model: " + element.getElementsByTagName("model").item(0).getTextContent();
                                    String productionYear = "production-year: " + element.getElementsByTagName("production-year").item(0).getTextContent();
                                    String horsepower = "horsepower: " + element.getElementsByTagName("horsepower").item(0).getTextContent();
                                    String powered = "powered by: " + poweredBy;
                                    String consumption = "consumption: " + element.getElementsByTagName("consumption").item(0).getTextContent();
                                    String price = "price: " + element.getElementsByTagName("price").item(0).getTextContent();

                                    selectedCars.append(id).append("\n").append(manufacturer).append("\n").append(model)
                                            .append("\n").append(productionYear).append("\n").append(horsepower).append("\n").append(powered).append("\n")
                                            .append(consumption).append("\n").append(price).append("\n\n");
                                }
                            }

                            JFrame electricFrame = new JFrame();
                            electricFrame.setSize(300, 320);
                            electricFrame.setLocation(720, 300);
                            JTextArea jTextArea = new JTextArea(10, 20);
                            electricFrame.add(jTextArea);
                            jTextArea.setWrapStyleWord(true);
                            jTextArea.setLineWrap(true);
                            JScrollPane jScrollPane = new JScrollPane(jTextArea);
                            electricFrame.add(jScrollPane);
                            jTextArea.append(selectedCars.toString());
                            jTextArea.getCaret().setDot(0);
                            electricFrame.setVisible(true);

                        } else {

                            JFrame fuelOrHybridFrame = new JFrame("consumption");
                            fuelOrHybridFrame.setLayout(new GridBagLayout());
                            fuelOrHybridFrame.setSize(350, 200);
                            fuelOrHybridFrame.setLocation(720, 320);
                            JLabel jLabel1 = new JLabel("min consumption");
                            fuelOrHybridFrame.setLocation(720, 320);
                            JLabel jLabel2 = new JLabel("max consumption");
                            JTextField jTextField1 = new JTextField(4);
                            JTextField jTextField2 = new JTextField(4);
                            JButton get = new JButton("get");
                            get.setPreferredSize(new Dimension(80, 25));
                            JButton close = new JButton("close");
                            close.setPreferredSize(new Dimension(80, 25));
                            fuelOrHybridFrame.setVisible(true);

                            GridBagConstraints c = new GridBagConstraints();
                            c.gridx = 0;
                            c.gridy = 0;
                            c.insets = new Insets(0,0,0,15);
                            fuelOrHybridFrame.add(jLabel1, c);

                            c = new GridBagConstraints();
                            c.gridx = 0;
                            c.gridy = 1;
                            c.insets = new Insets(0,0,0,15);
                            fuelOrHybridFrame.add(jLabel2, c);

                            c = new GridBagConstraints();
                            c.gridx = 1;
                            c.gridy = 0;
                            fuelOrHybridFrame.add(jTextField1, c);

                            c = new GridBagConstraints();
                            c.gridx = 1;
                            c.gridy = 1;
                            fuelOrHybridFrame.add(jTextField2, c);

                            c = new GridBagConstraints();
                            c.gridx = 2;
                            c.gridy = 0;
                            c.insets = new Insets(0,15,5,0);
                            fuelOrHybridFrame.add(get, c);

                            c = new GridBagConstraints();
                            c.gridx = 2;
                            c.gridy = 1;
                            c.insets = new Insets(5,15,0,0);
                            fuelOrHybridFrame.add(close, c);

                            close.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    fuelOrHybridFrame.dispose();
                                }
                            });

                            get.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    double consumption1 = Double.parseDouble(jTextField1.getText());
                                    double consumption2 = Double.parseDouble(jTextField2.getText());
                                    StringBuilder selectedCars = new StringBuilder();

                                    for (int i = 0; i < cars.getLength(); i++) {
                                        Node carNode = cars.item(i);
                                        Element element = (Element) carNode;

                                        double carConsumption = Double.parseDouble(element.getElementsByTagName("consumption").item(0).getTextContent());
                                        if (carConsumption >= consumption1 && carConsumption <= consumption2) {
                                            String id = "id: " + element.getAttribute("id");
                                            String manufacturer = "manufacturer: " + element.getElementsByTagName("manufacturer").item(0).getTextContent();
                                            String model = "model: " + element.getElementsByTagName("model").item(0).getTextContent();
                                            String productionYear = "production-year: " + element.getElementsByTagName("production-year").item(0).getTextContent();
                                            String horsepower = "horsepower: " + element.getElementsByTagName("horsepower").item(0).getTextContent();
                                            String powered = "powered by: " + element.getElementsByTagName("consumption").item(0).getAttributes().item(0).getNodeValue();
                                            String consumption = "consumption: " + element.getElementsByTagName("consumption").item(0).getTextContent();
                                            String price = "price: " + element.getElementsByTagName("price").item(0).getTextContent();

                                            selectedCars.append(id).append("\n").append(manufacturer).append("\n").append(model).append("\n")
                                                    .append(productionYear).append("\n").append(horsepower).append("\n").append(powered).append("\n")
                                                    .append(consumption).append("\n").append(price).append("\n\n");
                                        }

                                    }

                                    JFrame jFrame = new JFrame();
                                    jFrame.setSize(300, 320);
                                    jFrame.setLocation(720, 300);
                                    JTextArea jTextArea = new JTextArea(10, 20);
                                    jFrame.add(jTextArea);
                                    jTextArea.setWrapStyleWord(true);
                                    jTextArea.setLineWrap(true);
                                    JScrollPane jScrollPane = new JScrollPane(jTextArea);
                                    jFrame.add(jScrollPane);
                                    jTextArea.append(selectedCars.toString());
                                    jTextArea.getCaret().setDot(0);
                                    jFrame.setVisible(true);

                                }
                            });
                        }
                    }
                });
                poweredFrame.setVisible(true);
            }
        });
        mainFrame.setVisible(true);
    }
}
