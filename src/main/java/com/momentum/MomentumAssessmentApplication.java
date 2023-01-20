package com.momentum;

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;

import com.momentum.web.filter.FacesUrlRewriteFilter;

@SpringBootApplication
public class MomentumAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomentumAssessmentApplication.class, args);
	}

//	@Bean
//    @Description("Faces Servlet Registration Bean")
//    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
//        return new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
//    }
//	
//	  @Bean
//	    @Description("Faces URL Rewrite Filter Registration Bean")
//	    public FilterRegistrationBean<FacesUrlRewriteFilter> facesUrlRewriteFilterRegistrationBean() {
//	    	FilterRegistrationBean<FacesUrlRewriteFilter> facesUrlRewriteFilterRegistrationBean = new FilterRegistrationBean<FacesUrlRewriteFilter>();
//
//		    facesUrlRewriteFilterRegistrationBean.setFilter(new FacesUrlRewriteFilter(".xhtml"));
//		    facesUrlRewriteFilterRegistrationBean.setAsyncSupported(true);
//
//		    final EnumSet<DispatcherType> dispatcherTypes =
//	            EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC, DispatcherType.INCLUDE);
//		    facesUrlRewriteFilterRegistrationBean.setDispatcherTypes(dispatcherTypes);
//
//		    return facesUrlRewriteFilterRegistrationBean;
//	    }
}
