package com.pluralsight.order;

import java.util.HashSet;
import java.util.Set;

// The Coupon class represents a discount code that can be applied to an order.
public class Coupon {
    private final String code;
    private final double discountPercentage;
    // Tracks which coupons have already been used (shared among all Coupon instances)
    private static final Set<String> usedCoupons = new HashSet<>();
    // Set of valid coupon codes (only these can be used)
    private static final Set<String> validCoupons = Set.of("10OFF", "5OFF");

    // Static method to check if a coupon code is valid and unused
    public static boolean isCouponValid(String code) {
        if (code == null) {
            return false;
        }
        String upperCode = code.toUpperCase();
        return validCoupons.contains(upperCode) && !usedCoupons.contains(upperCode);
    }

    // Constructor validates and registers the coupon
    public Coupon(String code) {
        if (code == null) throw new IllegalArgumentException("Coupon code cannot be null.");
        this.code = code.toUpperCase();

        // Validate against list of valid codes
        if (!validCoupons.contains(this.code)) {
            throw new IllegalArgumentException("Invalid coupon code.");
        }

        // Prevent reuse of the same coupon
        if (usedCoupons.contains(this.code)) {
            throw new IllegalStateException("This coupon code has already been used.");
        }

        // Set discount based on code
        if (this.code.equals("10OFF")) {
            this.discountPercentage = 0.10;
        } else {
            this.discountPercentage = 0.05;
        }

        // Mark coupon as used
        usedCoupons.add(this.code);
    }

    // Applies the discount to the given total amount
    public double applyDiscount(double total) {
        return total - (total * discountPercentage);
    }

    // Getter for the coupon code
    public String getCode() {
        return this.code;
    }

    // Getter for the discount percentage
    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
