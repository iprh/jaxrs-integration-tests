package com.redhat.resource.basic;

import com.redhat.resource.basic.resource.ScanProxy;
import com.redhat.resource.basic.resource.ScanResource;
import com.redhat.resource.basic.resource.ScanSubresource;
import com.redhat.utils.HttpResponseCodes;
import com.redhat.utils.PortProviderUtil;
import com.redhat.utils.TestUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @tpSubChapter Resource
 * @tpChapter Integration tests
 * @tpTestCaseDetails Regression tests for RESTEASY-263 and RESTEASY-274
 * @tpSince RESTEasy 3.0.16
 */
@RunWith(Arquillian.class)
@RunAsClient
public class ScanTest {
    private static Client client;

    @BeforeClass
    public static void before() throws Exception {
        client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void after() throws Exception {
        client.close();
    }

    @Deployment
    public static Archive<?> deployUriInfoSimpleResource() {
        WebArchive war = TestUtil.prepareArchive(ScanTest.class.getSimpleName());
        war.addClass(ScanProxy.class);
        Map<String, String> contextParams = new HashMap<>();
        contextParams.put("resteasy.scan", "true");
        return TestUtil.finishContainerPrepare(war, contextParams, ScanResource.class, ScanSubresource.class);
    }

    /**
     * @tpTestDetails Test with new client
     * @tpSince RESTEasy 3.0.16
     */
    @Test
    public void testNewClient() throws Exception {
        Response response = client.target(PortProviderUtil.generateURL("/test/doit", ScanTest.class.getSimpleName())).request().get();
    }
}