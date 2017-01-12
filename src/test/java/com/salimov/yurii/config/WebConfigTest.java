package com.salimov.yurii.config;

import com.salimov.yurii.controller.admin.*;
import com.salimov.yurii.controller.client.ClientMainController;
import com.salimov.yurii.controller.seo.SeoController;
import com.salimov.yurii.controller.other.WorkController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.salimov.yurii.controller.advice.AdviceController;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public final class WebConfigTest {

    @Autowired
    private ClientMainController clientMainController;

    @Autowired
    private AdminMainController adminMainController;

    @Autowired
    private AdminUserController adminUserController;

    @Autowired
    private AdviceController adviceController;

    @Autowired
    private SeoController seoController;

    @Autowired
    private WorkController workController;
/*
    @Test
    public void webConfigTest() {
        assertNotNull(new WebConfig());
    }
*/
    @Test
    public void controllerTest() {
        assertNotNull(clientMainController);
        assertNotNull(adminMainController);
        assertNotNull(adminUserController);
        assertNotNull(adviceController);
        assertNotNull(seoController);
        assertNotNull(workController);
    }
/*
    @Test
    public void viewResolverTest() {
        assertNotNull(new WebConfig().viewResolver());
    }*/
}
