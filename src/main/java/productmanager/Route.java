package productmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/*
 * Triển khai lớp lấy URI của Request handle mapping theo tên của nó
 * Author Toan nguyen
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Route {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private HttpServletRequest request;

    public String name(String name) {
        Assert.notNull(wac, "No WebApplicationContext.");
        Map<String, RequestMappingInfoHandlerMapping> map = wac.getBeansOfType(RequestMappingInfoHandlerMapping.class);
        List<HandlerMethod> handlerMethods = null;
        for (RequestMappingInfoHandlerMapping mapping : map.values()) {
            handlerMethods = mapping.getHandlerMethodsForMappingName(name);
            if (handlerMethods != null) {
                break;
            }
        }

        if (handlerMethods == null) {
            throw new IllegalArgumentException("Mapping not found: " + name);
        } else if (handlerMethods.size() != 1) {
            throw new IllegalArgumentException("No unique match for mapping " + name + ": " + handlerMethods);
        } else {
            HandlerMethod handlerMethod = handlerMethods.get(0);
            Class<?> controllerType = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            return new MvcUriComponentsBuilder.MethodArgumentBuilder(null, controllerType, method).build();
        }
    }

    /*
     * Kiểm tra Request hiện tại có trùng với tên Route mong muốn
     */
    public boolean is(String name) {
        String uri_route = this.name(name);
        String uri       = request.getRequestURI();
        if (uri_route.equals(uri)){
            return true;
        }else{
            Path path_route = Paths.get(uri_route);
            Path path       = Paths.get(uri);

            uri_route = path_route.toString();
            uri       = path.toString();

            if (uri_route.equals(uri))
                return true;
        }
        return false;
    }
}
