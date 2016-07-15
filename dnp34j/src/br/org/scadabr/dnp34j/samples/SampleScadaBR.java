package br.org.scadabr.dnp34j.samples;

import br.org.scadabr.dnp34j.logging.DNPLogger;
import br.org.scadabr.dnp34j.master.session.config.EthernetParameters;

public class SampleScadaBR {
    private static ScadaBRInterface master;

    public static void main(String[] args) {

        master = new ScadaBRInterface();

        try {
            master.initEthernet(new EthernetParameters("127.0.0.1", 20000), 1, 2, 10);
        }
        catch (Exception e) {
            System.exit(0);
        }

        for (int i = 0; i < 20000; i++) {
            try {
            	DNPLogger.LOGGER.info(" ");
            	DNPLogger.LOGGER.info("Poll: " + i);
                master.doPoll();
                printPoints();
                DNPLogger.LOGGER.info(" ");
                Thread.sleep(1000);
            }
            catch (Exception e) {
            	DNPLogger.LOGGER.info(e.getMessage());
            }
        }

        try {
            master.terminate();
        }
        catch (Exception e) {
        	DNPLogger.LOGGER.error("", e);
        }

    }

    private static void printPoints() throws Exception {
        for (int i = 0; i < 50; i++) {
            if (i % 5 == 0) {
            	DNPLogger.LOGGER.info("");
            	DNPLogger.LOGGER.info(master.getElement((byte) 0, i).getValue() + " / ");
            }
            else
            	DNPLogger.LOGGER.info(master.getElement((byte) 0, i).getValue() + " / ");
        }
    }
}
