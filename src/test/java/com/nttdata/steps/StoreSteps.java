package com.nttdata.steps;

import com.nttdata.page.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Normalizer;
import java.time.Duration;

public class StoreSteps {
    private final WebDriverWait wait;
    private WebDriver driver;

    //constructor
    public StoreSteps(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeUser(String user){
        WebElement userInputElement = driver.findElement(LoginStorePage.userInput);
        userInputElement.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginStorePage.loginButton));
    }

    public void typePassword(String password){

        this.driver.findElement(LoginStorePage.passInput).sendKeys(password);
    }

    public void login(){

        this.driver.findElement(LoginStorePage.loginButton).click();
    }

    public void validateAuthentication() {
        WebElement userLink = wait.until(ExpectedConditions.visibilityOfElementLocated(HomeCategoryPage.userName));
        String linkText = userLink.getText().replace("\uE7FF ", "").trim(); // Eliminar espacios extra
        Assert.assertEquals("El texto del enlace no es 'Cerrar sesión'", "Cerrar sesión", linkText);
    }

    public void categoryClick(String categoria) {
        By categoryXPath = getCategoryXPath(categoria);
        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(categoryXPath));
        categoryElement.click();
    }


    public void subCategoryClick(String subcategoria) {
        By subCategoryXPath = getSubCategoryXPath(subcategoria);
        WebElement subCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(subCategoryXPath));
        subCategoryElement.click();
    }

    private By getCategoryXPath(String categoria) {
        switch (categoria.toLowerCase()) {
            case "clothes":
                return HomeCategoryPage.categoryBtn;
            // Aquí puedes agregar más categorías si es necesario
            default:
                throw new IllegalArgumentException("Categoría no encontrada: " + categoria);
        }
    }

    private By getSubCategoryXPath(String subcategoria) {
        switch (subcategoria.toLowerCase()) {
            case "men":
                return HomeCategoryPage.subCategoryBtn;
            // Aquí puedes agregar más subcategorías si es necesario
            default:
                throw new IllegalArgumentException("Subcategoría no encontrada: " + subcategoria);
        }
    }

    public void selectFirstProduct() {
        //this.driver.findElement(SubCategoryPage.artfirstProduct).click();
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(SubCategoryPage.artfirstProduct));
        productLink.click();
    }

    public void setQuantity(int cantidad) {
        WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(SubCategoryPage.inpQuantity));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", quantityInput);

        quantityInput.sendKeys(String.valueOf(cantidad));
    }

    public void addToCart() {
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(SubCategoryPage.btnAddCart));
        addToCartButton.click();
    }

    public void validateProductConfirmation() {
        WebElement confirmacionMensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(SubCategoryPage.lblModalConfirmation));

        // Extraemos el texto visible y eliminamos el icono (por ejemplo, el texto comienza después del icono)
        String mensajeActual = confirmacionMensaje.getText().replace("", "").trim();

        // El mensaje esperado (sin el icono)
        String mensajeEsperado = "Producto añadido correctamente a su carrito de compra";

        // Comprobamos que el mensaje sea el correcto, sin el icono
        Assert.assertEquals(mensajeEsperado, mensajeActual);
    }

    public void validateTotalAmount() {
        // Obtener el valor precio unitario
        WebElement precioElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SubCategoryPage.pPrice));
        String precioTexto = precioElement.getText().replace("S/ ", "").trim();  // Eliminar el prefijo "S/" y limpiar espacios
        double precioUnitario = Double.parseDouble(precioTexto);

        // Obtener el valor de cantidad
        WebElement cantidadElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SubCategoryPage.strongQuantity));
        String cantidadTexto = cantidadElement.getText().trim();
        int cantidad = Integer.parseInt(cantidadTexto);

        // Calcular el monto esperado
        double montoEsperado = precioUnitario * cantidad;

        // Obtener el valor mostrado en el monto total
        WebElement montoTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SubCategoryPage.spanTotalAmount));
        String montoTotalTexto = montoTotalElement.getText().replace("S/ ", "").trim();
        double montoTotal = Double.parseDouble(montoTotalTexto);

        // Validar que el monto total sea el correcto
        Assert.assertEquals(montoTotal, montoEsperado, 0.01);
    }


    public void finalizePurchase() {
        WebElement botonFinalizarCompra = wait.until(ExpectedConditions.elementToBeClickable(SubCategoryPage.btnfinalizePurchase));
        botonFinalizarCompra.click();
    }

    public void validateTitleCart() {
        WebElement tituloCarrito = wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.lblCart));
        String tituloActual = tituloCarrito.getText().trim();
        String tituloEsperado = "CARRITO";
        Assert.assertEquals(tituloEsperado, tituloActual);
    }

    public void validateCalculatePricesCart() {
        // Extraer valores necesarios de los elementos usando las constantes de CartPage
        WebElement precioUnitarioElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.spanPrecioUnitario));
        WebElement cantidadElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.inputCantidad));
        WebElement precioTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.spanPrecioTotal));
        WebElement precioImpuestosElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.spanTotalImpuestos));

        // Obtener los valores como texto
        String precioUnitarioTexto = precioUnitarioElement.getText().trim().replace("S/", "").trim(); // Elimina el prefijo 'S/'
        String cantidadTexto = cantidadElement.getAttribute("value").trim();
        String precioImpuestosTexto = precioImpuestosElement.getText().trim().replace("S/", "").trim();

        // Convertir los valores a números
        double precioUnitario = Double.parseDouble(precioUnitarioTexto);
        int cantidad = Integer.parseInt(cantidadTexto);
        double precioImpuesto = Double.parseDouble(precioImpuestosTexto);

        // Realizar el cálculo esperado
        double precioTotalEsperado = (precioUnitario * cantidad) + precioImpuesto;

        // Obtener el precio total mostrado
        String precioTotalTexto = precioTotalElement.getText().trim().replace("S/", "").trim();
        double precioTotal = Double.parseDouble(precioTotalTexto);

        // Validar que el cálculo sea correcto
        Assert.assertEquals(precioTotal, precioTotalEsperado, 0.01);
    }

}
