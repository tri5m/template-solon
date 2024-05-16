package com.example.solon.text;

import org.junit.Test;
import org.noear.solon.test.annotation.Rollback;

/**
 * @title: ExampleTest
 * @author: trifolium.wang
 * @date: 2024/5/16
 */
public class ExampleTest extends BaseTest{

    @Test
    @Rollback
    public void hello() {
        System.out.println("Hello solon test");
    }
}
