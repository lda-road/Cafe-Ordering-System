import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CafeOrderingSystem extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;

	String[][] items = {
			{"Espresso","Americano","Cappuccino","Caramel Macchiato","Mocha"},
			{"Hot Chocolate","Matcha Latte","Strawberry Milk","Fresh Lemonade"},
			{"Croissant","Chocolate Croissant","Blueberry Muffin","Banana Bread","Cinnamon Roll"},
			{"Cheesecake","Chocolate Cake","Brownies","Donut","Ice Cream"},
			{"Extra Shot Espresso","Extra Syrup","Whipped Cream","Extra Ice","Upgrade to Large Size"}};
	
	String[] itemCateg = {"Coffee","Non-Coffee Drinks","Pastries","Desserts", "Optional Add-ons"};
    

    double[][] prices = {
            {120,130,140,150,145},
            {110,135,120,100},
            {90,100,85,95,105},
            {150,140,120,60,80},
            {30,25,20,10,40}
    };

    JCheckBox[][] checks = new JCheckBox[items.length][];
    JTextField[][] qty = new JTextField[items.length][];

    JTextArea receipt;

    JButton computeBtn, placeBtn, clearBtn;

    JRadioButton student, senior, noDiscount;
    ButtonGroup discountGroup;

    static int receiptNumber = 1001;

    double subtotal = 0;
    double finalTotal = 0;
    private JLabel lblCafeOrdeeringSystem;
    private JLabel lblCafeIco;
    private JLabel lblCafeIco_1;
    private JPanel cashPaymentPanel;
    private JTextField cashField;

    public CafeOrderingSystem(){
    	setIconImage(Toolkit.getDefaultToolkit().getImage(CafeOrderingSystem.class.getResource("/icons/MCafeIco.png")));
    	
    	getContentPane().setBackground(new Color(211, 197, 163));
    	setResizable(false);

        setTitle("Cafe Ordering System");
        setSize(681,605);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel menuPanel = new JPanel(new GridLayout(30,2));
        menuPanel.setBackground(new Color(211, 197, 163));
        

        JLabel label_1 = new JLabel("Select Item");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuPanel.add(label_1);
        JLabel label_2 = new JLabel("Qty");
        label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuPanel.add(label_2);
    	JLabel[] labelCateg = new JLabel[itemCateg.length];
    	

        for(int i=0;i<itemCateg.length;i++){
        	labelCateg[i] = new JLabel(itemCateg[i]);
        	labelCateg[i].setFont(new Font("Tahoma", Font.BOLD, 15));
            menuPanel.add(labelCateg[i]);
            menuPanel.add(new JLabel(" "));
            checks[i] = new JCheckBox[items[i].length];
            qty[i] = new JTextField[items[i].length];
        	for(int j=0;j<items[i].length;j++){
        		checks[i][j] = new JCheckBox(items[i][j]);
            	checks[i][j].setBackground(new Color(211, 197, 163));
            	qty[i][j] = new JTextField();
            	qty[i][j].setPreferredSize(new Dimension(5,20));

            	menuPanel.add(checks[i][j]);
            	menuPanel.add(qty[i][j]);
        	}
        }
        getContentPane().setLayout(null);

        JScrollPane menuScroll = new JScrollPane(menuPanel);
        menuScroll.setBounds(10, 63, 330, 381);
        menuScroll.setPreferredSize(new Dimension(350,600));
        getContentPane().add(menuScroll,BorderLayout.WEST);

        receipt = new JTextArea();
        receipt.setBackground(new Color(245, 244, 241));
        receipt.setEditable(false);
        JScrollPane receiptScroll = new JScrollPane(receipt);
        receiptScroll.setBounds(350, 63, 305, 381);
        getContentPane().add(receiptScroll);

        JPanel discountPanel = new JPanel();
        discountPanel.setBackground(new Color(211, 197, 163));
        discountPanel.setBounds(0, 455, 665, 33);

        student = new JRadioButton("Student Discount (10%)");
        student.setHorizontalAlignment(SwingConstants.CENTER);
        student.setBackground(new Color(211, 197, 163));
        senior = new JRadioButton("Senior Citizen Discount (20%)");
        senior.setHorizontalAlignment(SwingConstants.CENTER);
        senior.setBackground(new Color(211, 197, 163));
        noDiscount = new JRadioButton("No Discount");
        noDiscount.setSelected(true);
        noDiscount.setHorizontalAlignment(SwingConstants.CENTER);
        noDiscount.setBackground(new Color(211, 197, 163));

        discountGroup = new ButtonGroup();
        discountGroup.add(student);
        discountGroup.add(senior);
        discountGroup.add(noDiscount);
        discountPanel.setLayout(new GridLayout(0, 3, 0, 0));

        discountPanel.add(student);
        discountPanel.add(senior);
        discountPanel.add(noDiscount);

        getContentPane().add(discountPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(211, 197, 163));
        buttonPanel.setBounds(0, 521, 665, 33);

        computeBtn = new JButton("Compute Total");
        placeBtn = new JButton("Place Order");
        clearBtn = new JButton("Clear Order");

        computeBtn.addActionListener(this);
        placeBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        buttonPanel.add(computeBtn);
        buttonPanel.add(placeBtn);
        buttonPanel.add(clearBtn);

        getContentPane().add(buttonPanel);
        
        lblCafeOrdeeringSystem = new JLabel("Cafe Ordering System");
        lblCafeOrdeeringSystem.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 25));
        lblCafeOrdeeringSystem.setHorizontalAlignment(SwingConstants.CENTER);
        lblCafeOrdeeringSystem.setBounds(173, 19, 317, 33);
        getContentPane().add(lblCafeOrdeeringSystem);
        
        lblCafeIco = new JLabel("");
        lblCafeIco.setHorizontalAlignment(SwingConstants.CENTER);
        lblCafeIco.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCafeIco.setIcon(new ImageIcon(CafeOrderingSystem.class.getResource("/icons/MCafeIco2.png")));
        lblCafeIco.setBounds(55, 11, 63, 52);
        getContentPane().add(lblCafeIco);
        
        lblCafeIco_1 = new JLabel("");
        lblCafeIco_1.setIcon(new ImageIcon(CafeOrderingSystem.class.getResource("/icons/MCafeIco2.png")));
        lblCafeIco_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCafeIco_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCafeIco_1.setBounds(545, 11, 63, 52);
        getContentPane().add(lblCafeIco_1);
        
        cashPaymentPanel = new JPanel();
        cashPaymentPanel.setBackground(new Color(211, 197, 163));
        cashPaymentPanel.setBounds(0, 488, 665, 33);
        getContentPane().add(cashPaymentPanel);
        
        JLabel label = new JLabel("Cash Payment:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        cashPaymentPanel.add(label);
        
        cashField = new JTextField(15);
        cashPaymentPanel.add(cashField);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==computeBtn || e.getSource()==placeBtn){

            receipt.setText("");

            subtotal = 0;
            boolean selected = false;

            for(int i=0;i<items.length;i++){
            	for(int j=0;j<items[i].length;j++){
                if(checks[i][j].isSelected()){

                    selected = true;

                    if(qty[i][j].getText().equals("")){
                        receipt.setText("Enter quantity for selected items.");
                        return;
                    }

                    int quantity;

                    try{
                        quantity = Integer.parseInt(qty[i][j].getText());
                    }
                    catch(Exception ex){
                        receipt.setText("Quantity must be numeric.");
                        return;
                    }

                    subtotal += quantity * prices[i][j];
                	}
            	}
            }

            if(!selected){
                receipt.setText("No items selected.");
                return;
            }
            
            

            double discount = 0;
            String discountType = "None";

            if(student.isSelected()){
                discount = subtotal * 0.10;
                discountType = "Student";
            }

            if(senior.isSelected()){
                discount = subtotal * 0.20;
                discountType = "Senior Citizen";
            }
            if(noDiscount.isSelected()) {
                discountType = "None";
            }

            finalTotal = subtotal - discount;

            receipt.append("=========== CAFE RECEIPT ===========\n");
            receipt.append("Receipt No: " + receiptNumber + "\n");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            receipt.append("Date: " + sdf.format(new Date()) + "\n");
            receipt.append("------------------------------------\n");

            receipt.append(String.format("%-20s %-5s %-8s\n","Item","Qty","Total"));

            for(int i=0;i<items.length;i++){
            	for(int j=0;j<items[i].length;j++){
            		if(checks[i][j].isSelected()){

            			int quantity = Integer.parseInt(qty[i][j].getText());
            			double itemTotal = quantity * prices[i][j];

            			receipt.append(String.format("%-20s %-5d %-8.2f\n",
            					items[i][j],quantity,itemTotal));
                	}
                }
            }

            receipt.append("------------------------------------\n");
            receipt.append("Subtotal: " + subtotal + "\n");
            receipt.append("Discount: " + discountType + "\n");
            receipt.append("Discount Amount: " + discount + "\n");
            receipt.append("TOTAL: " + finalTotal + "\n");

            if(e.getSource()==placeBtn){

                double cash;

                try{
                    cash = Double.parseDouble(cashField.getText());
                }
                catch(Exception ex){
                    receipt.append("\nEnter valid cash payment.");
                    return;
                }

                if(cash < finalTotal){
                    receipt.append("\nInsufficient cash.");
                    return;
                }

                double change = cash - finalTotal;

                receipt.append("Cash: " + cash + "\n");
                receipt.append("Change: " + change + "\n");
                receipt.append("\nThank you for your order!\n");

                receiptNumber++;
            }
        }

        if(e.getSource()==clearBtn){

            for(int i=0;i<items.length;i++){
            	for(int j=0;j<items[i].length;j++){
                checks[i][j].setSelected(false);
                qty[i][j].setText("");
            	}
            }

            receipt.setText("");
            cashField.setText("");
            discountGroup.clearSelection();

            subtotal = 0;
            finalTotal = 0;
        }
    }

    public static void main(String[] args){
        new CafeOrderingSystem();
    }
}