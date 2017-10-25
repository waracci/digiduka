package com.digiduka.digiduka.models;

import java.util.ArrayList;

/**
 * Created by victor on 10/11/17.
 */

public class Stock {
    private String stockId;
    private String dateCreated;
    private ArrayList<Product> products;
    private int totalCost;
    private String assistantId;

    public Stock(){}

    public Stock(String dateCreated, String userId) {
        this.dateCreated = dateCreated;
        totalCost = 0;
        products = new ArrayList<>();
        this.assistantId = userId;
    }
    public String getStockId() {
        return stockId;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public int getTotalCost() {
        return totalCost;
    }
    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    public void addProducts(Product product){
        boolean productExists = false;
        for (Product product1:products){
            if (product1.getPushId().equals(product.getPushId())){
                int amount = product1.getAmount()+1;
                product1.setAmount(amount);
                productExists = true;
                break;
            }
        }
        if (!productExists){
            Product newProduct = product;
            newProduct.setAmount(1);
            products.add(newProduct);
        }
        computeTotalCost();
    }
    public void removeProduct(Product product){
        for (int z = 0; z < products.size();z++) {
            if (products.get(z).getPushId().equals(product.getPushId())){
                int amount = products.get(z).getAmount()-1;
                if (amount<1){
                    products.remove(z);
                }else{
                    products.get(z).setAmount(amount);
                }

                break;
            }
        }
        computeTotalCost();
    }
    public boolean containsProduct(Product product){
        for (Product product1:products){
            if (product1.getPushId().equals(product.getPushId())){
                return true;
            }
        }
        return false;
    }
    public void computeTotalCost(){
        int totalCost = 0;
        for (Product product:products){
            int cost = product.getAmount()*product.getBuyingPrice();
            totalCost+=cost;
        }
        this.totalCost=totalCost;
    }

    @Override
    public String toString() {
        String result = "";
        result+=stockId+", "+dateCreated+", "+totalCost+", {";
        for (Product product:products){
            result+=product.getNameOfProduct()+", ";
        }
        result+="}";
        return result;
    }
}
