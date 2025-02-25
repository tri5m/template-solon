package com.example.solon.text;

import com.example.solon.template.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.solon.test.HttpTester;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;
import org.noear.solon.test.annotation.Rollback;

import java.io.IOException;

/**
 * @title: HttpTestExampleTest
 * @author: trifolium.wang
 * @date: 2024/5/16
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(value = App.class, args = {"--server.port=5876", "--debug=true", "--env=test"})
public class HttpTestExampleTest extends HttpTester {


    @Test
    @Rollback
    public void get() throws IOException {
        String s = path("/api/test/get-user?id=1").get();
        System.out.println(s);
    }
}
