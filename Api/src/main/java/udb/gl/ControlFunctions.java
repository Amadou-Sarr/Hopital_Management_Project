package udb.gl;

import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;

public abstract class ControlFunctions {

    @Bean
    public static boolean isEqual(String var_a, String var_b){

        if(var_a.equals(var_b)){
            return true;
        }

        return false;
    }

}
