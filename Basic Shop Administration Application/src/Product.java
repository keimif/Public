// being able to convert to 2 decimal places
import java.text.DecimalFormat;

public class Product{
    private String productId;
    private String productDescription;
    private int productTotalPurchased;
    private double productCost;
    private double productSell;
    private int productAlert;
    private int productStock;
    private int productVAT;
    private double productDiscount;
    //DecimalFormat returns a String, so we will need to parse
    private static DecimalFormat twoDecimals = new DecimalFormat(".##");

    // initial constructor for the Product class
    public Product(){
        this.productId = "";
        this.productDescription = "";
        this.productTotalPurchased = 0;
        this.productCost = 0.00;
        this.productSell = 0.00;
        this.productAlert = 0;
        this.productStock = 0;
        this.productVAT = 0;
        this.productDiscount = 0;
    }
    
    // getters and setters for the product variables
    protected void setID(String id){
        this.productId = id;
    }
    
    protected String getID(){
        return this.productId;
    }
    
    protected void setDescription(String description){
        this.productDescription = description;
    }
    
    protected String getDescription(){
        return this.productDescription;
    }
    
    protected void setCostPrice(double costprice){
        this.productCost = costprice;
    }

    protected double getCostPrice(){
        return Double.parseDouble(twoDecimals.format(this.productCost));
    }
    
    protected void setSellingPrice(double sellingprice){
        this.productSell = sellingprice;
    }
    
    protected double getSellingPrice(){
        return Double.parseDouble(twoDecimals.format(this.productSell));
    }
    
    protected void setReorder(int reorder){
        this.productAlert = reorder;
    }
    
    protected int getReorder(){
        return this.productAlert;
    }
    
    protected void addStock(int stock){
        this.productStock = stock;
        this.productTotalPurchased = this.productTotalPurchased + stock;
    }
    
    protected void takeStock(int stock){
        this.productStock = this.productStock - stock;
    }
    
    protected int getStock(){
        return this.productStock;
    }
    
    protected void setVat(int vat){
        this.productVAT = vat;
    }
    
    protected int getVat(){
        return this.productVAT;
    }
    
    protected void setDiscount(double discount){
        this.productDiscount = discount;
    }

    protected double getDiscount(){
        return Double.parseDouble(twoDecimals.format(this.productDiscount));
    }
    
    protected double getQuantityCost(int quantity){
        return Double.parseDouble(twoDecimals.format(this.getDiscountedPrice() * quantity));
    }
    
    protected double getDiscountedPrice(){
        return Double.parseDouble(twoDecimals.format(this.productSell - this.productDiscount));
    }
    
    protected double getFinalPrice(int quantity){
        return Double.parseDouble(twoDecimals.format(this.getQuantityCost(quantity) + ((this.getVat() * this.getDiscountedPrice()) / 100)));
    }
    
    protected int getTotalPurchased(){
        return this.productTotalPurchased;
    }
    
    protected int getTotalSold(){
        return this.productTotalPurchased - this.productStock;
    }
    
    protected double getProfit(){
        return Double.parseDouble(twoDecimals.format(this.getDiscountedPrice() - this.productCost));
    }
    
    protected double getAllProfit(){
        return Double.parseDouble(twoDecimals.format(this.getProfit() * (this.getTotalSold())));
    }
}