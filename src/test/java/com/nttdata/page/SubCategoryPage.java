package com.nttdata.page;

import org.openqa.selenium.By;

public class SubCategoryPage {
    public static By artfirstProduct = By.xpath("//*[@id='js-product-list']/div[1]/div/article/div/div[1]/a");
    public static By btnAddCart = By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[2]/button");
    public static By inpQuantity = By.id("quantity_wanted");
    public static By lblModalConfirmation = By.id("myModalLabel");
    public static By pPrice = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[1]/div/div[2]/p");
    public static By strongQuantity = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[1]/div/div[2]/span[3]/strong");
    public static By spanTotalAmount = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/p[2]/span[2]");
    public static By btnfinalizePurchase = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/a");
}
