package com.leetcode.y2022.m01;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/******************JUnit 4**********************/
//@SuiteClasses({
//	TestJan13.class,
//	TestJan14.class
//})
//@RunWith(Suite.class)
//public class JanSuiteTest {
//
//}

/******************JUnit 5**********************/
@RunWith(JUnitPlatform.class)
@SelectClasses( { TestJan13.class, TestJan14.class, TestJan17.class } )
public class SuiteTestJan {

}
