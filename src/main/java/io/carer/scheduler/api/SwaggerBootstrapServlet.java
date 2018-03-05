package io.carer.scheduler.api;

import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.util.Json;
import io.swagger.util.Yaml;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
public class SwaggerBootstrapServlet extends HttpServlet {

  
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);

        BeanConfig beanConfig = new BeanConfig();
        Json.mapper().registerModule(new JaxbAnnotationModule()); 
        Yaml.mapper().registerModule(new JaxbAnnotationModule());
        
        beanConfig.setTitle("Care Planner");
        beanConfig.setDescription("Long description");
        beanConfig.setTermsOfServiceUrl("https://carer.io/tos");
        beanConfig.setLicense("kenya apps licence");
        beanConfig.setLicenseUrl("http://carer.io/license");
        beanConfig.setContact("0796826191");
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"HTTP", "HTTPS"});
        beanConfig.setHost("95.85.41.9");
//        beanConfig.setFilterClass(swaggerBeanConfigConfigurationService.getFilterClass());
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("io.carer");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
    }
}
