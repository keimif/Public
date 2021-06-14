import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    // declaring main variables
    private static ArrayList<Product> productArray = new ArrayList<Product>();
    private static Scanner sc = new Scanner(System.in);
    private static DecimalFormat twoDecimals = new DecimalFormat(".##");
    
    public static void main(String[] args){
        // launching main menu
        menuNavigation();
    }
    
    // menu text
    private static void menuText(){
        System.out.println();
        System.out.println("1: ADD NEW PRODUCTS");
        System.out.println("2: EFFECT A TRANSACTION");
        System.out.println("3: VIEW LIST OF PRODUCTS");
        System.out.println("4: VIEW PRODUCTS NEEDING RE-ORDER");
        System.out.println("5: CALCULATE STATISTICS");
        System.out.println("6: EXIT");
        System.out.println();
    }
    
    // menu navigation
    private static void menuNavigation(){
        boolean valid = false;
        // always display the menu
        while(true)
        {
            try
            {
                menuText();
                int input = sc.nextInt();
                System.out.println();
                if(input >= 1 && input <= 6){
                    while(!valid){
                        switch(input){
                            case 1: System.out.println("ADD NEW PRODUCTS");
                                    boolean writeEnabled = false;
                                    while(!writeEnabled){
                                        try{
                                            System.out.println("How many products do you want to add?");
                                            int productAdd = sc.nextInt();
                                            for(int i = 0; i < productAdd; i++){
                                                productArray.add(enterProductDetails());
                                            }
                                            writeEnabled = true;
                                        }catch(NullPointerException | InputMismatchException ex){
                                            System.out.println("Invalid value inputted. Please make sure to input a number\n\n");
                                            sc.nextLine();
                                        }
                                    }
                                    menuNavigation();
                                    break;
                            case 2: effectTransaction();
                                    break;
                            case 3: viewListOfProducts("");
                                    break;
                            case 4: viewProductsNeedingReorder();
                                    break;
                            case 5: calculateStatistics();
                                    break;
                            case 6: exit();
                                    break;
                        }
                    }
                }else{
                    menuNavigation();
                }
            }catch(NullPointerException | InputMismatchException ex){
                System.out.println("Invalid value inputted. Please make sure to input a number.\n\n");
                sc.nextLine();
            }
        }
    }
    
    // 1) creating an instance of the product class
    private static Product enterProductDetails(){
        boolean inputDone = false;
        System.out.println("Please input product details");
        Product productInstance = new Product();
        sc.nextLine();
        while(!inputDone){
            System.out.print("Product ID: ");
            //using trim to remove extra spaces before and after the string value
            String id = sc.nextLine().trim();
            if(!id.equalsIgnoreCase("G") && !id.equalsIgnoreCase("V") && !id.equalsIgnoreCase("C") && !id.equals("")){
                if(getProductPlaceholder(id) == -1){
                    productInstance.setID(id.toUpperCase());
                    inputDone = true;
                }
                else{
                    boolean productVerified = false;
                    while(!productVerified){
                        System.out.print("The Product ID inputted is already registered. Do you want to view the registered product details? \n\nY: YES \nN: NO \n\nInput your choice: ");
                        char choice = sc.next().charAt(0);
                        if(choice == 'y' || choice == 'Y'){
                            viewListOfProducts(id);
                            productVerified = true;
                        }
                        else if(choice == 'n' || choice == 'N'){
                            productVerified = true;
                        }
                        else{
                            System.out.println("Invalid character inputted.\n\n");
                        }
                    }
                }
            }
            else if (id.equals("")){
                System.out.println("Please input Product ID.");
            }
            else{
                System.out.println("Unfortunately, letters G, V and C are being used as system keywords. Please input a different Product ID.");
            }
        }
        
        // setting the description
        inputDone = false;
        while(!inputDone){
            System.out.print("Product Description: ");
            String desc = sc.nextLine().trim();
            if(!desc.equals("")){
                productInstance.setDescription(desc);
                inputDone = true;
            }
            else{
                System.out.println("Please input Product Description.");
            }
        }
        
        // setting the stock
        inputDone = false;
        while(!inputDone){
            try{
                System.out.print("Product Count in Stock: ");
                int stock = sc.nextInt();
                if(stock >= 0){
                    productInstance.addStock(stock);
                    inputDone = true;
                }
                else{
                    System.out.println("Product Stock cannot be a negative number.");
                }
            }catch(NullPointerException | InputMismatchException ex){
                System.out.println("Invalid value inputted. Please make sure to input a number.\n\n");
                sc.nextLine();
            }
        }
        
        // setting the cost price
        inputDone = false;
        while(!inputDone){
            try{
                System.out.print("Product Cost: ");
                double price = sc.nextDouble();
                if(price >= 0){
                    productInstance.setCostPrice(Double.parseDouble(twoDecimals.format(price)));
                    inputDone = true;
                }
                else{
                    System.out.println("Cost Price cannot be a negative number.");
                }
            }catch(NullPointerException | InputMismatchException ex){
                System.out.println("Invalid value inputted. Please make sure to input a price.\n\n");
                sc.nextLine();
            }
        }
        
        // setting the selling price
        inputDone = false;
        while(!inputDone){
            try{
                System.out.print("Product Selling Price: ");
                double price = sc.nextDouble();
                if(price >= 0){
                    productInstance.setSellingPrice(Double.parseDouble(twoDecimals.format(price)));
                    if(productInstance.getSellingPrice() < productInstance.getCostPrice()){
                        boolean valid = false;
                        while(!valid){
                            System.out.println("Product Selling Price is not profitable. Do you want to proceed? \n\nY: YES \nN: NO");
                            char choice = sc.next().charAt(0);
                            if(choice == 'y' || choice == 'Y'){
                                System.out.println("Saving Selling Price: " + productInstance.getSellingPrice());
                                inputDone = true;
                                valid = true;
                            }
                            else if(choice == 'n' || choice == 'N'){
                                System.out.println("Selling price not saved.\n\n");
                                inputDone = false;
                                valid = true;
                            }else{
                                System.out.println("Invalid character inputted.\n\n");
                            }
                        }
                    }
                    else{
                        inputDone = true;
                    }
                }
                else{
                    System.out.println("Selling price cannot be a negative number.");
                }
            }catch(NullPointerException | InputMismatchException ex){
                System.out.println("Invalid value inputted. Please make sure to input a price.\n\n");
                sc.nextLine();
            }
        }
        
        // setting the reorder amount
        inputDone = false;
        while(!inputDone){
            try{
                System.out.print("Alert Minimum Stock Amount:");
                int stock = sc.nextInt();
                if(stock >= 0){
                    productInstance.setReorder(stock);
                    inputDone = true;
                }
                else{
                    System.out.println("Product Minimum Stock cannot be a negative number.");
                }
            }catch(NullPointerException | InputMismatchException ex){
                System.out.println("Invalid value inputted. Please make sure to input a number.\n\n");
                sc.nextLine();
            }
        }
        
        // setting the VAT
        inputDone = false;
        while(!inputDone){
            try{
                System.out.print("Product VAT (%):");
                int vat = sc.nextInt();                
                if(vat >= 0){
                    productInstance.setVat(vat);
                    inputDone = true;
                }
                else{
                    System.out.println("Product VAT cannot be a negative number.");
                }
            }catch(NullPointerException | InputMismatchException ex){
                System.out.println("Invalid value inputted. Please make sure to input a number.\n\n");
                sc.nextLine();
            }
        }
        
        // setting the discount
        inputDone = false;
        while(!inputDone){
            try{
                System.out.print("Discount Amount:");
                double discount = sc.nextDouble();
                if(discount > productInstance.getSellingPrice()){
                    System.out.println("Discount cannot be more than the Selling Price: " + productInstance.getSellingPrice());
                }
                else if(discount >= 0){
                    productInstance.setDiscount(discount);
                    inputDone = true;
                }
                else{
                    System.out.println("Product Discount cannot be a negative number.");
                }
            }catch(NullPointerException | InputMismatchException ex){
                System.out.println("Invalid value inputted. Please make sure to input a number.\n\n");
                sc.nextLine();
            }
        }
        // returning the whole product instance
        return productInstance;
    }

    // 2) effecting a transaction
    private static void effectTransaction(){
        System.out.println("EFFECTING A TRANSACTION");
        if(!productArray.isEmpty()){
            displayOffers();
            ArrayList<String> productSelected = new ArrayList<String>();
            ArrayList<Integer> productQuantity = new ArrayList<Integer>();
            boolean transactionDone = false;
            while(!transactionDone){
                displayStock();
                System.out.println("Ready with your purchase list?\n\nV: View Purchase List \nG: End Transaction and Generate Receipt \nC: Cancel Transaction \n\nInput a Product ID or Navigation Character:");
                String choice = sc.nextLine();
                if(!choice.equals("")){
                    if(choice.equalsIgnoreCase("G")){
                        String receipt = generateReceipt(productSelected, productQuantity);
                        if(!receipt.equals("")){
                            System.out.println("\n\nRECEIPT \n\n" + receipt + "\n\n");
                            for(int i = 0; i < productSelected.size(); i++){
                                int arrayIndex = getProductPlaceholder(productSelected.get(i));
                                if(arrayIndex != -1){
                                    productArray.get(arrayIndex).takeStock(productQuantity.get(i));
                                }
                                else{
                                    System.out.println("ERROR: Product not found.\n\n");
                                    transactionDone = true;
                                    break;
                                }
                            }
                            System.out.println("WE WOULD LIKE TO TAKE THE OPPORTUNITY TO THANK YOU FOR YOUR PURCHASE. \n\nSEE YOU NEXT TIME! \n\n");
                            transactionDone = true;
                            sc.nextLine();
                        }
                        else{
                            System.out.println("No products were added to cart.");
                            sc.nextLine();
                        }
                    }
                    else if(choice.equalsIgnoreCase("V")){
                        String receipt = generateReceipt(productSelected, productQuantity);
                        if(!receipt.equals("")){
                            System.out.println("\n\n" + receipt);
                            sc.nextLine();
                        }
                        else{
                            System.out.println("No products were added to cart.");
                            sc.nextLine();
                        }
                    }
                    else if(choice.equalsIgnoreCase("C")){
                        transactionDone = true;
                    }
                    else{
                        int arrayPlaceholder = getProductPlaceholder(choice);
                        if(arrayPlaceholder != -1){
                            boolean productAdded = false;
                            while(!productAdded){
                                try{
                                    System.out.print("Quantity: ");
                                    int quantity = sc.nextInt();
                                    if(quantity < 0){
                                        System.out.println("Quantity cannot be a negative number");
                                    }
                                    if(quantity == 0){
                                        productAdded = true;
                                    }
                                    else if(quantity <= productArray.get(arrayPlaceholder).getStock()){
                                        if(!productSelected.contains(productArray.get(arrayPlaceholder).getID())){
                                            productSelected.add(productArray.get(arrayPlaceholder).getID());
                                            productQuantity.add(quantity);
                                            productAdded = true;
                                            System.out.println("Product Added To Cart");
                                            sc.nextLine();
                                        }
                                        else{
                                            boolean productUpdated = false;
                                            int index = productSelected.indexOf(productArray.get(arrayPlaceholder).getID());
                                            while(!productUpdated){
                                                System.out.print("This product was already added in this transaction with a quantity of " + productQuantity.get(index) + ". \n\nWhat do you want to do? \n\nC: Change Quantity \nR: Remove this item from purchase list \n\nInput your choice: ");
                                                String quantityChoice = sc.nextLine().trim();
                                                if(quantityChoice.equals("")){
                                                    System.out.println("Please input your choice.\n\n");
                                                }
                                                else if(quantityChoice.equalsIgnoreCase("C")){
                                                    productQuantity.set(index, quantity);
                                                    productUpdated = true;
                                                    System.out.println("Quantity Updated.");
                                                    sc.nextLine();
                                                }
                                                else if(quantityChoice.equalsIgnoreCase("R")){
                                                    productSelected.remove(index);
                                                    productQuantity.remove(index);
                                                    productUpdated = true;
                                                    System.out.println("Product Removed From Cart.");
                                                    sc.nextLine();
                                                }
                                            }
                                            productAdded = true;
                                        }
                                    }
                                    else{
                                        System.out.println("\n\nUnfortunately, we only have " + productArray.get(arrayPlaceholder).getStock() + " of " + productArray.get(arrayPlaceholder).getDescription() + ". \n\nPlease re-input the quantity or input 0 to cancel item order.\n\n");
                                    }
                                }catch(NullPointerException | InputMismatchException ex){
                                    System.out.println("Invalid value inputted. Please make sure to input a number.\n\n");
                                    sc.nextLine();
                                }
                            }
                        }
                        else{
                            System.out.println("Product ID does not match with any of the records. \n\n");
                            sc.nextLine();
                        }
                    }
                }
                else{
                    System.out.println("Please input your choice. \n\n");
                }
            }
        }
        else{
            System.out.println("Product List does not exists. \n\n");
        }
        menuNavigation();
    }
    
    // displaying the currently available offers
    private static void displayOffers(){
        System.out.println();
        String discountList = "";
        for(int i = 0; i < productArray.size(); i++){
            if(productArray.get(i).getDiscount() > 0 && productArray.get(i).getStock() > 0){
                discountList = discountList + getProductInfo(productArray.get(i).getID(), 1, true, true, false, false, false, false, false, true, true, true, false, false, false, false, false, false) + "\n";
            }
        }
        if(!discountList.equals("")){
            System.out.println("Hi, there! Are you interested in benefitting from our discounts?\n\n" + discountList + vatDisclaimer());
        }        
        sc.nextLine();
    }
    
    // displaying the current stock
    private static void displayStock(){
        String stockList = "";
        for(int i = 0; i < productArray.size(); i++){
            if(productArray.get(i).getStock() > 0){
                // this returns one product into one string variable
                stockList = stockList + getProductInfo(productArray.get(i).getID(), 1, true, true, false, false, false, false, false, true, true, true, false, true, true, false, false, false) + "\n";
            }
        }
        System.out.println("Currently in our stock: \n\n" + stockList + vatDisclaimer());
    }
    
    // displaying a message regarding VAT
    private static String vatDisclaimer(){
        return "Disclaimer: Please note that FINAL PRICE includes calculated VAT. \n\n";
    }
    
    // creating the receipt
    private static String generateReceipt(ArrayList<String> productSelected, ArrayList<Integer> productQuantity){
        boolean errorDetected = false;
        String transactionList = "";
        double totalPrice = 0;
        for(int i = 0; i < productSelected.size(); i++){
            int arrayIndex = getProductPlaceholder(productSelected.get(i));
            if(arrayIndex != -1){
                transactionList = transactionList + getProductInfo(productArray.get(arrayIndex).getID(), productQuantity.get(i), true, true, false, false, false, true, false, true, true, true, true, true, true, false, false, false) + "\n";
                totalPrice = totalPrice + Double.parseDouble(twoDecimals.format(productArray.get(i).getFinalPrice(productQuantity.get(i))));
            }
            else{
                System.out.println("ERROR: Product not found.\n\n");
                errorDetected = true;
                break;
            }
        }
        if(!errorDetected && !transactionList.equals("")){
            transactionList = transactionList + "TOTAL PRICE: " + totalPrice + "\n\n";
        }
        return transactionList;
    }
    
    // getting the price + VAT
    private static double getVATPrice(double price, int vat){
        return Double.parseDouble(twoDecimals.format(price + ((price * vat)/100)));
    }
    
    // viewing the list of all the available products
    private static void viewListOfProducts(String id){
        if(!id.equals("")){
            System.out.println("\n\nVIEW PRODUCT DETAILS - " + id.toUpperCase());
            int arrayPlaceholder = getProductPlaceholder(id);
            if(arrayPlaceholder != -1){
                System.out.println(getProductInfo(productArray.get(arrayPlaceholder).getID(), 1, true, true, true, true, true, false, true, true, true, true, false, true, false, true, false, false) + "\n");          
            }
            else{
                System.out.println("Product not found\n\n");
            }
        }
        else{
            System.out.println("VIEW LIST OF PRODUCTS");
            String productList = "";
            for(int i = 0; i < productArray.size(); i++){
                productList = productList + getProductInfo(productArray.get(i).getID(), 1, true, true, true, true, true, false, true, true, true, true, false, true, false, true, false, false) + "\n";
            }
            if(!productList.equals("")){
                System.out.println("\n\n" + productList + "\n\n");
            }
            else{
                System.out.println("\n\nProduct List does not exist.\n\n");
            }
            sc.nextLine();
            sc.nextLine();
            menuNavigation();
        }
    }
    
    // setting the product placeholder within the ArrayList
    private static int getProductPlaceholder(String id){
        int placeholder = -1;
        for(int i = 0; i < productArray.size(); i++){
            if(id.equalsIgnoreCase(productArray.get(i).getID())){
                placeholder = i;
                break;
            }
        }
        return placeholder;
    }
    
    // showing the products that need to be re-ordered
    private static void viewProductsNeedingReorder(){
        System.out.println("VIEW PRODUCTS NEEDING RE-ORDER");
        String productList = "";
        for(int i = 0; i < productArray.size(); i++){
            if(productArray.get(i).getStock() <= productArray.get(i).getReorder()){
                productList = productList + getProductInfo(productArray.get(i).getID(), 1, true, true, true, true, false, false, false, false, false, false, false, false, false, true, false, false) + "\n";
            }
        }
        if(!productList.equals("")){
            System.out.println("\n\n" + productList + "\n\n");
        }
        else{
            System.out.println("\n\nThere is no need for re-orders.\n\n");
        }
        sc.nextLine();
        sc.nextLine();
        menuNavigation();
    }
    
    // calculating the statistical data
    private static void calculateStatistics(){
        System.out.println("CALCULATE STATISTICS");
        System.out.println();
        String stats = "";
        double totalProfit = 0;
        for(int i = 0; i < productArray.size(); i++){
            stats = stats + getProductInfo(productArray.get(i).getID(), 1, true, true, true, true, false, false, true, true, true, true, false, true, false, true, true, true) + "\n";
            totalProfit = totalProfit + productArray.get(i).getAllProfit();
        }
        if(!stats.equals("")){
            System.out.println(stats + "\n\nTOTAL PROFIT: " + twoDecimals.format(totalProfit));
        }
        else{
            System.out.println("Product List does not exist.");
        }
        sc.nextLine();
        sc.nextLine();
        menuNavigation();
    }
    
    // exiting from the application
    private static void exit(){
        System.out.println("THANK YOU AND GOOD DAY!");
        System.exit(0);
    }
    
    // returning the product information depending on the parameters sent
    private static String getProductInfo(String productID, int quantity, boolean id, boolean description, boolean totalPurchased, boolean stock, boolean alert, boolean showQuantity, boolean cost, boolean sell, boolean discount, boolean discountPrice, boolean discountByQuantity, boolean vat, boolean finalPrice, boolean profit, boolean totalSold, boolean allProfit){
        String productBuilder = "";
        int arrayPlaceholder = getProductPlaceholder(productID);
        if(arrayPlaceholder != -1){
            if(id){
                productBuilder = productBuilder + "ID: " + productArray.get(arrayPlaceholder).getID() + "\n";
            }
            
            if(description){
                productBuilder = productBuilder + "DESCRIPTION: " + productArray.get(arrayPlaceholder).getDescription() + "\n";
            }
            
            if(totalPurchased){
                productBuilder = productBuilder + "TOTAL PURCHASED: " + productArray.get(arrayPlaceholder).getTotalPurchased() + "\n";
            }
            
            if(stock){
                productBuilder = productBuilder + "AVAILABLE STOCK: " + productArray.get(arrayPlaceholder).getStock() + "\n";
            }
            
            if(alert){
                productBuilder = productBuilder + "MINIMUM STOCK ALERT: " + productArray.get(arrayPlaceholder).getReorder() + "\n";
            }
            
            if(showQuantity){
                productBuilder = productBuilder + "QUANTITY: " + quantity + "\n";
            }
            
            if(cost){
                productBuilder = productBuilder + "PRODUCT COST PRICE PER ITEM: " + productArray.get(arrayPlaceholder).getCostPrice() + "\n";
            }
            
            if(sell){
                productBuilder = productBuilder + "PRODUCT SELLING PRICE PER ITEM: " + productArray.get(arrayPlaceholder).getSellingPrice() + "\n";
            }
            
            if(discount){
                productBuilder = productBuilder + "DISCOUNT OFFERED PER ITEM: " + productArray.get(arrayPlaceholder).getDiscount() + "\n";
            }
            
            if(discountPrice){
                productBuilder = productBuilder + "DISCOUNTED PRICE PER ITEM: " + productArray.get(arrayPlaceholder).getDiscountedPrice() + "\n";
            }
            
            if(discountByQuantity){
                productBuilder = productBuilder + "DISCOUNTED PRICE: " + productArray.get(arrayPlaceholder).getQuantityCost(quantity) + "\n";
            }
            
            if(vat){
                productBuilder = productBuilder + "VAT ON PRODUCT: " + productArray.get(arrayPlaceholder).getVat() + "\n";
            }
            
            if(finalPrice){
                productBuilder = productBuilder + "FINAL PRICE: " + productArray.get(arrayPlaceholder).getFinalPrice(quantity) + "\n";
            }
            
            if(profit){
                double profitCalculation = productArray.get(arrayPlaceholder).getProfit();
                String alertMessage = "";
                if(profitCalculation == 0){
                    alertMessage = "ALERT: NO PROFIT DETECTED.";
                }
                else if(profitCalculation < 0){
                    alertMessage = "ALERT: PROFIT LOSS DETECTED";
                }
                productBuilder = productBuilder + "PROFIT ON EACH PRODUCT: " + profitCalculation + " " + alertMessage + "\n";
            }
            
            if(totalSold){
                productBuilder = productBuilder + "TOTAL ITEMS SOLD: " + productArray.get(arrayPlaceholder).getTotalSold() + "\n";
            }
            
            if(allProfit){
                productBuilder = productBuilder + productArray.get(arrayPlaceholder).getID() + " TOTAL PROFIT: " + productArray.get(arrayPlaceholder).getAllProfit() + "\n";
            }
        }
        else{
            productBuilder = "-1";
        }
        return productBuilder;
    }
}