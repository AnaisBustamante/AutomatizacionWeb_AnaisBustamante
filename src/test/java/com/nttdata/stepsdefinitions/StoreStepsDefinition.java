package com.nttdata.stepsdefinitions;

import com.nttdata.steps.StoreSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class StoreStepsDefinition {
    private WebDriver driver;

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion?back=https%3A%2F%2Fqalab.bensg.com%2Fstore%2Fpe%2Finiciar-sesion%3Fback%3Dhttps%253A%252F%252Fqalab.bensg.com%252Fstore%252Fpe%252F");
        screenShot();
    }


    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String usuario, String clave) {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.typeUser(usuario);
        storeSteps.typePassword(clave);
        screenShot();
        storeSteps.login();
        screenShot();
        storeSteps.validateAuthentication();
        screenShot();
    }

    @When("navego a la categoría {string} y subcategoría {string}")
    public void navegoALaCategoríaYSubcategoría(String categoria, String subcategoria) {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.categoryClick(categoria);
        screenShot();
        storeSteps.subCategoryClick(subcategoria);
        screenShot();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.selectFirstProduct();
        screenShot();
        storeSteps.setQuantity(cantidad);
        screenShot();
        storeSteps.addToCart();
        screenShot();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.validateProductConfirmation ();
        screenShot();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.validateTotalAmount ();
        screenShot();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.finalizePurchase ();
        screenShot();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.validateTitleCart ();
        screenShot();
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.validateCalculatePricesCart (); //
        screenShot();
    }

}
