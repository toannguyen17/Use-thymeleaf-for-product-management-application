package productmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import productmanager.form.formBind;
import productmanager.model.Product;
import productmanager.setvice.ProductService;

@Controller
@RequestMapping("/delete")
public class DeleteController {
    @Autowired
    private ProductService productService;

    @GetMapping(name = "delete")
    public String doGet(@RequestParam Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "delete";
    }

    @PostMapping
    public String doPost(@RequestParam Long id, RedirectAttributes redi){
        productService.deleteById(id);
//        redi.addAttribute("delete", true);
        redi.addFlashAttribute("delete", true);
        return "redirect:/";
    }
}
