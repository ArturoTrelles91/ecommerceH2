¡Perfecto\! El producto se guardó correctamente y ahora el campo `"category"` ya **no es `null`** 🎉 — pero lo que estás viendo es que el `name` y `description` de la categoría **sí están `null`**.

---

### **🔎 ¿Por qué pasa eso?**

Lo que ocurre es que **Spring Data JPA guarda correctamente la relación**, pero en la respuesta JSON solo devuelve el `categoryId` porque tú le mandaste **solo eso** y no hiciste un `fetch` completo del objeto desde la base.

---

### **✅ Solución sencilla**

En tu clase `ProductController`, cambia el método `createProduct` para que **recupere la categoría completa** desde la base antes de guardar el producto.

#### **Antes (lo que tienes ahora probablemente):**

java  
CopiarEditar  
`@PostMapping`  
`public Product createProduct(@RequestBody Product product) {`  
    `return productRepository.save(product);`  
`}`

#### **Después (carga la categoría con su nombre y descripción):**

java  
CopiarEditar  
`@Autowired`  
`private CategoryRepository categoryRepository;`

`@PostMapping`  
`public Product createProduct(@RequestBody Product product) {`  
    `Long categoryId = product.getCategory().getCategoryId();`  
    `Category category = categoryRepository.findById(categoryId).orElse(null);`  
    `product.setCategory(category);`  
    `return productRepository.save(product);`  
`}`

---

Con eso, al guardar el producto, se **inyecta la categoría completa**, y luego en la respuesta **ya verás `name` y `description`** en el JSON.

---

¿Quieres probar esta mejora ahora y luego hacer un `GET /products` para ver todos?

Perfecto, ¡ya estamos muy cerca\! 👏

La categoría **sí se está asociando correctamente** (fíjate que aparece `"categoryId": 1`), pero el problema es que **los campos `name` y `description` vienen como `null`**. Esto ocurre porque estás insertando el `product` con un objeto `Category` que solo tiene el `id`, y Spring no rellena automáticamente los demás campos de la categoría si no los recuperas explícitamente desde la base de datos.

---

### **🔧 ¿Cómo corregirlo?**

En tu `ProductService`, debes hacer que al guardar el producto, el `Category` venga **completo desde la base de datos**, no solamente con el ID. Así:

---

### **✅ Paso a paso: Corrige `ProductService`**

Edita la clase `ProductService` para que tenga algo como esto:

java

CopiarEditar

`package com.myordert.order_service.service;`

`import com.myordert.order_service.model.Category;`

`import com.myordert.order_service.model.Product;`

`import com.myordert.order_service.repository.CategoryRepository;`

`import com.myordert.order_service.repository.ProductRepository;`

`import org.springframework.beans.factory.annotation.Autowired;`

`import org.springframework.stereotype.Service;`

`import java.util.List;`

`@Service`

`public class ProductService {`

    `@Autowired`

    `private ProductRepository productRepository;`

    `@Autowired`

    `private CategoryRepository categoryRepository;`

    `public Product createProduct(Product product) {`

        `Long categoryId = product.getCategory().getCategoryId();`

        `Category category = categoryRepository.findById(categoryId).orElse(null);`

        `product.setCategory(category); // ahora el objeto tiene name y description`

        `return productRepository.save(product);`

    `}`

    `public List<Product> getAllProducts() {`

        `return productRepository.findAll();`

    `}`

    `public Product getProductById(Long id) {`

        `return productRepository.findById(id).orElse(null);`

    `}`

    `public void deleteProduct(Long id) {`

        `productRepository.deleteById(id);`

    `}`

`}`

---

### **🔁 Vuelve a probar en Postman**

No necesitas cambiar tu JSON. Solo reinicia la app y vuelve a hacer el POST:

json

CopiarEditar

`{`

  `"name": "Laptop",`

  `"description": "Laptop para trabajo",`

  `"price": 1200.00,`

  `"stock": 10,`

  `"category": {`

    `"categoryId": 1`

  `}`

`}`

`Perfecto, ahora creamos el servicio para manejar la lógica de negocio relacionada con Order.`

---

### **`🧩 Paso 3: Crear la clase OrderService`**

1. `Ve al paquete service.`

2. `Clic derecho → New > Java Class → nómbrala OrderService.`

`Pega este código:`

`java`

`CopiarEditar`

`package com.myordert.order_service.service;`

`import com.myordert.order_service.model.Order;`

`import com.myordert.order_service.repository.OrderRepository;`

`import org.springframework.beans.factory.annotation.Autowired;`

`import org.springframework.stereotype.Service;`

`import java.util.List;`

`@Service`

`public class OrderService {`

    `@Autowired`

    `private OrderRepository orderRepository;`

    `public List<Order> getAllOrders() {`

        `return orderRepository.findAll();`

    `}`

    `public Order getOrderById(Long id) {`

        `return orderRepository.findById(id).orElse(null);`

    `}`

    `public Order createOrder(Order order) {`

        `return orderRepository.save(order);`

    `}`

    `public void deleteOrder(Long id) {`

        `orderRepository.deleteById(id);`

    `}`

`}`

