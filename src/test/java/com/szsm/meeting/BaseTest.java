package com.szsm.meeting;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description: 基础测试类
 * @author: LiuJun
 * @date: 2020/9/2 15:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =PaperlesMeetingSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
}
