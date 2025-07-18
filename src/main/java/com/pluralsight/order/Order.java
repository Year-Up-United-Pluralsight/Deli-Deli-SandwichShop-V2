package com.pluralsight.order;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order  {

    private final String customerName;
    private final LocalDateTime orderTime;
    private final List<MenuItem> menuItems = new ArrayList<>();
    private Coupon coupon;


    public Order(String customerName, LocalDateTime orderTime) {
        this.customerName = customerName;
        this.orderTime = orderTime;
    }


    public Order(LocalDateTime orderTime){
        this.customerName = getCustomerName();
        this.orderTime = orderTime;
    }




    public void addItem(MenuItem menuItem){

        menuItems.add(menuItem);


    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public LocalDateTime getOrderTime(){

        return orderTime;

    }


    public String getCustomerName(){


        return customerName;
    }

    public double getTotal(){
        double total = 0;

        for(MenuItem menuItem : menuItems){
            total += menuItem.getPrice();

        }


        return total;

    }


    public List<MenuItem> getMenuItems(){


        return menuItems;


    }

    public void clearMenuItem(){

        menuItems.clear();

    }

    // Returns a formatted summary of the order, including items, coupon (if any), and total cost
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Customer: " + customerName + "\nTime: " + orderTime + "\n\n");

        // Append all item descriptions
        for (MenuItem item : menuItems) {
            sb.append(item).append("\n\n");
        }

        // Append coupon details if present
        if (coupon != null) {
            sb.append("Coupon: ")
                    .append(coupon.getCode())
                    .append(" (")
                    .append(String.format("%.0f", coupon.getDiscountPercentage() * 100))
                    .append("% off)\n");
        }

        // Append total cost, with discount if applicable
        if (coupon != null) {
            sb.append(String.format("Total: $%.2f", coupon.applyDiscount(getTotal())));
        } else {
            sb.append(String.format("Total: $%.2f", getTotal()));
        }

        return sb.toString();
    }


}
