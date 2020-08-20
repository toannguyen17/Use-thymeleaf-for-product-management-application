package productmanager.setvice;

import productmanager.model.Product;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ProductService implements Service<Product> {
    private static Long _id = 0L;
    private static List<Product> data;

    public ProductService(){
        data = new ArrayList<Product>();
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        for (Product p: data){
            if (p.getId() == id){
                product = p;
                break;
            }
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<Product>(data);
    }

    @Override
    public void deleteById(Long id) {
        int length = data.size();
        for (int i = 0; i < length; i++) {
            Product p = data.get(i);
            if (p.getId() == id){
                data.remove(i);
                break;
            }
        }
    }

    @Override
    public void insert(Product element) {
        Long id = ++_id;
        element.setId(id);
        data.add(element);
    }
}
