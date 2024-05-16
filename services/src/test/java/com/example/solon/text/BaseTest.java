package com.example.solon.text;

import com.example.solon.template.App;
import org.junit.runner.RunWith;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

/**
 * @title: BaseTest
 * @author: trifolium.wang
 * @date: 2024/5/16
 */
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(value = App.class, args = {"--server.port=5876", "--debug=true", "--env=test"})
public abstract class BaseTest {
}
