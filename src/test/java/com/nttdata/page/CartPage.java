package com.nttdata.page;

import org.openqa.selenium.By;

public class CartPage {
    public static By lblCart = By.xpath("//*[@id='main']/div/div[1]/div/div[1]/h1");
    public static By spanPrecioUnitario = By.xpath("//*[@id='main']/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span");
    public static By inputCantidad = By.xpath("//*[@id='main']/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input");
    public static By spanPrecioTotal = By.xpath("//*[@id='main']/div/div[2]/div[1]/div[1]/div[2]/div[2]/span[2]");
    public static By spanTotalImpuestos = By.xpath("//*[@id='main']/div/div[2]/div[1]/div[1]/div[2]/div[3]/span[2]");
}
