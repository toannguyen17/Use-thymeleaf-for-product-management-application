package productmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import productmanager.Route;
import productmanager.form.formBind;
import productmanager.model.Product;
import productmanager.setvice.ProductService;

@Controller
@RequestMapping("/create")
public class CreateController {
    @Autowired
    private ProductService productService;

    @GetMapping(name = "create")
    public String doGet(Model model){
        return "create";
    }

    @PostMapping
    public String doPost(formBind formBind, Model model){
        Product product = new Product(formBind.getName(), formBind.getPrice(), formBind.getQuantity());
        productService.insert(product);
        model.addAttribute("success", true);
        return "create";
    }
}
