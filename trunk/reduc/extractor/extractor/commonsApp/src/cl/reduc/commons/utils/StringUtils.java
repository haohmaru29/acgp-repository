package cl.reduc.commons.utils;
import cl.reduc.commons.mvc.controller.AbstractJpaController;

public class StringUtils extends AbstractJpaController {

    public static boolean contains(String contain, String string) {
        boolean response = false;
        if(string.indexOf(contain) != -1) response = true;
        return response;
    }
}