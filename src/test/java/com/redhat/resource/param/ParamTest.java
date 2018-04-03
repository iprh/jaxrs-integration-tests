package com.redhat.resource.param;

import com.redhat.resource.param.resource.ParamInterfaceResource;
import com.redhat.resource.param.resource.ParamResource;
import com.redhat.utils.PortProviderUtil;
import com.redhat.utils.TestUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;

/**
 * @tpSubChapter Parameters
 * @tpChapter Integration tests
 * @tpTestCaseDetails Regression test for RESTEASY-423 and RESTEASY-522
 * @tpSince RESTEasy 3.0.16
 */
@RunWith(Arquillian.class)
@RunAsClient
public class ParamTest {

    @Deployment
    public static Archive<?> deploy() throws Exception {
        WebArchive war = TestUtil.prepareArchive(ParamTest.class.getSimpleName());
        war.addClass(ParamInterfaceResource.class);
        return TestUtil.finishContainerPrepare(war, null, ParamResource.class);
    }

    private String generateBaseUrl() {
        return PortProviderUtil.generateBaseUrl(ParamTest.class.getSimpleName());
    }


    /**
     * @tpTestDetails Null matrix parameters should be accepted by the reasteasy client library (RESTEASY-423)
     * @tpSince RESTEasy 3.0.16
     */
    @Test
    public void testNullMatrixParam() throws Exception {
        ResteasyWebTarget target = (ResteasyWebTarget) ClientBuilder.newClient().target(generateBaseUrl());
        ParamInterfaceResource proxy = target.proxy(ParamInterfaceResource.class);
        String rtn = proxy.getMatrix(null);
        Assert.assertEquals("null", rtn);
    }

    /**
     * @tpTestDetails RestEasy Client Framework should not throw null point exception when
     *                the @CookieParam() is null (RESTEASY-522)
     * @tpSince RESTEasy 3.0.16
     */
    @Test
    public void testNullCookieParam() throws Exception {
        ResteasyWebTarget target = (ResteasyWebTarget) ClientBuilder.newClient().target(generateBaseUrl());
        ParamInterfaceResource proxy = target.proxy(ParamInterfaceResource.class);
        String rtn = proxy.getCookie(null);
        Assert.assertEquals("null", rtn);
    }

    /**
     * @tpTestDetails RestEasy Client Framework should not throw null point exception when
     *                the @HeaderParam() is null (RESTEASY-522)
     * @tpSince RESTEasy 3.0.16
     */
    @Test
    public void testNullHeaderParam() throws Exception {
        ResteasyWebTarget target = (ResteasyWebTarget) ClientBuilder.newClient().target(generateBaseUrl());
        ParamInterfaceResource proxy = target.proxy(ParamInterfaceResource.class);
        String rtn = proxy.getHeader(null);
        Assert.assertEquals("null", rtn);
    }

}
