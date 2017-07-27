package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.post.Post;
import ua.com.ecoteh.service.data.PostService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.enity.MockModels.getPost;
import static ua.com.ecoteh.mocks.service.data.MockServices.getPostService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class PostControllerTest {

    private static PostController controller;

    @BeforeClass
    public static void beforeClass() {
        final MainMVFabric fabric = getCacheMVFabric();
        final PostService postService = getPostService();
        controller = new PostController(fabric, postService);
    }

    @Test
    public void whenNewPostPageThenReturnModelAndView() {
        final String viewName = "post/add";
        final String[] keys = { "main_company", "categories", "favicon" };
        final ModelAndView modelAndView = controller.getNewPostPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenAddPostThenReturnPostPage() {
        final Post post = getPost();
        final File file = post.getLogo();
        final String actualRedirect = controller.addPost(
                post.getTitle(), post.getText(),
                post.getDescription(), post.getKeywords(),
                file.getMultipartFile(),
                post.isValidated()
        );
        final String expectedRedirect = "redirect:/post/all";
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddPostByGetMethodThenThrowIllegalMappingException() {
        controller.addPost();
    }

    @Test
    public void whenEditPostPageThenReturnModelAndView() {
        final String viewName = "post/edit";
        final String[] keys = { "main_company", "categories", "favicon", "post" };
        final ModelAndView modelAndView = controller.editPost(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenUpdatePostThenReturnPostPage() {
        final Post post = getPost();
        final File file = post.getLogo();
        final String actualRedirect = controller.updatePost(
                post.getUrl(), post.getTitle(), post.getText(),
                post.getDescription(), post.getKeywords(),
                file.getMultipartFile(),
                post.isValidated()
        );
        final String expectedRedirect = "redirect:/post/" + post.getUrl();
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdatePostByGetMethodThenThrowIllegalMappingException() {
        controller.updatePost();
    }

    @Test
    public void whenDeletePostByUrlThenReturnRedirect() {
        final String expectedRedirect = "redirect:/";
        final String actualRedirect = controller.deletePostByUrl(URL);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteAllPostByUrlThenReturnRedirect() {
        final String expectedRedirect = "redirect:/";
        final String actualRedirect = controller.deleteAllPosts();
        assertEquals(actualRedirect, expectedRedirect);
    }
}
