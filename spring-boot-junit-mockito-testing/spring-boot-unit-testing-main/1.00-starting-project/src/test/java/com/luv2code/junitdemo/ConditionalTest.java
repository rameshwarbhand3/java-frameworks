package com.luv2code.junitdemo;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {
    @Test
    @Disabled("Don't run until fix JIRA #123 complete")
    public void basicTest(){

    }
    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void testForWindowOnly(){

    }

    @Test
    @EnabledOnOs(OS.LINUX)
    public void testForLinuxOnly(){

    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    public void testForJava17(){

    }

    @Test
    @EnabledIfEnvironmentVariable(named = "Luv2Code_Ev", matches = "Dev")
    public void testOnlyForDevEnvironment(){
        
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "Luv2Code_Sys_Prop", matches = "CI_CD_DEPLOY")
    void testOnlyForSystemProperty(){

    }
}

