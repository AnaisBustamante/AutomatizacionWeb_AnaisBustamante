

 Feature: Product - Store
   @testStore
   Scenario Outline: Validación del precio de un producto
     Given estoy en la página de la tienda
     And me logueo con mi usuario <usuario> y clave <clave>
     When navego a la categoría <categoria> y subcategoría <subcategoria>
     And agrego <cantidad> unidades del primer producto al carrito
     Then valido en el popup la confirmación del producto agregado
     And valido en el popup que el monto total sea calculado correctamente
     When finalizo la compra
     Then valido el titulo de la pagina del carrito
     And vuelvo a validar el calculo de precios en el carrito

     Examples:
       | usuario                        | clave                  | categoria  | subcategoria | cantidad |
       | "anaisbustamantetorres@gmail.com" | "A0910/*2002"       | "Clothes"  | "Men"        | 2        |
       | "usuario_invalido@example.com"   | "clave_incorrecta"   | "Clothes"  | "Men"        | 2        |
       | "anaisbustamantetorres@gmail.com" | "A0910/*2002"       | "Autos"    | "Men"        | 2        |
