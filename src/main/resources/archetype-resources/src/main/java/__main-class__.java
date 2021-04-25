#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package};

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.platform.Platforms;
import com.pi4j.util.Console;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author luca
 */
public class ${main-class} {

    private static final int PIN_LED = 22; // PIN 15 = BCM 22
    private static final Console console = new Console();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        console.box("Hello Rasbian world !");
        Context pi4j = null;
        try {
            pi4j = Pi4J.newAutoContext();
            new ${main-class}().run(pi4j);
        } catch (InvocationTargetException e) {
            console.println("Error: " + e.getTargetException().getMessage());
        } catch (Exception e) {
            console.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (pi4j != null) {
                pi4j.shutdown();
            }
        }
    }

    private void run(Context pi4j) throws Exception {
        Platforms platforms = pi4j.platforms();

        console.box("Pi4J PLATFORMS");
        console.println();
        platforms.describe().print(System.out);
        console.println();

        var ledConfig = DigitalOutput.newConfigBuilder(pi4j)
                        .id("led")
                        .name("LED Flasher")
                        .address(PIN_LED)
                        .shutdown(DigitalState.LOW)
                        .initial(DigitalState.LOW)
                        .provider("pigpio-digital-output");

        var led = pi4j.create(ledConfig);
        int counter = 0;
        while (counter < 50) {
            if (led.equals(DigitalState.HIGH)) {
                led.low();
                System.out.println("low");
            } else {
                led.high();
                System.out.println("high");
            }
            Thread.sleep(500);
            counter++;
        }
    }

}
