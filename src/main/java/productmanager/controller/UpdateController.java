package productmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import productmanager.Route;
import productmanager.form.formBind;
import productmanager.model.Product;
import productmanager.setvice.ProductService;

@Controller
@RequestMapping("/update")
public class UpdateController {
    @Autowired
    private ProductService productService;

    @GetMapping(name = "update")
    public String doGet(@RequestParam Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "update";
    }

    @PostMapping
    public String doPost(@RequestParam Long id, formBind formBind, Model model){
        Product product = productService.findById(id);
        product.setName(formBind.getName());
        product.setPrice(formBind.getPrice());
        product.setQuantity(formBind.getQuantity());
        model.addAttribute("success", true);
        model.addAttribute("product", product);

        return "update";
    }
}
